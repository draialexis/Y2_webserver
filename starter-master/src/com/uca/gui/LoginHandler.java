package com.uca.gui;

import com.uca.core.TeacherCore;
import com.uca.entity.TeacherEntity;
import com.uca.util.Encryptor;
import com.uca.util.JWTLoginUtil;
import freemarker.template.TemplateException;
import io.jsonwebtoken.ExpiredJwtException;
import spark.Request;
import spark.Response;

import java.io.IOException;

import static com.uca.util.StringUtil.isValidShortString;

public class LoginHandler
{
    private static String pathSaved = null;
    private static String token     = null;
    private static String userName  = null;

    public static final int UNHASHED_PWD_SIZE_MAX = 16;
    public static final int UNHASHED_PWD_SIZE_MIN = 4;
    public static final int SALT_SIZE             = 32;

    public static String getUserName()
    {
        return userName;
    }

    public static String handleLoginPost(Request req, Response res) throws TemplateException, IOException
    {
        userName = req.queryParams("username");
        if (!authenticate(userName, req.queryParams("userpwd")))
        {
            userName = null;
            return LoginGUI.display(InfoMsg.ECHEC_AUTHENTIFICATION);
        }

        token = JWTLoginUtil.makeToken(userName);
        // if user was redirected here, they are sent back to their original destination -- else, to index
        res.redirect(pathSaved != null ? pathSaved : "/");
        return null;
    }

    public static String handleLogout() throws TemplateException, IOException
    {
        disconnect();
        return LoginGUI.display(InfoMsg.DECONNEXION_SUCCES);
    }

    public static void handleTimeout(Response res)
    {
        disconnect();
        res.redirect("/login/timeout");
    }

    public static void disconnect()
    {
        userName = null;
        token = null;
    }

    public static boolean authenticate(String userName, String userPwd)
    {
        if (!isValidShortString(userName) || !isValidShortString(userName))
        { // tried to log in without filling in the form
            return false;
        }
        TeacherEntity user = TeacherCore.readByUserName(userName);
        if (user == null)
        { // means the user doesn't exist
            return false;
        }
        return Encryptor.verifyUserPassword(userPwd, user.getUserPwd(), user.getUserSalt());
    }

    public static void ensureUserIsLoggedIn(Request req, Response res) throws IOException
    {
        if (!isLoggedIn(res))
        {
            pathSaved = req.pathInfo(); // saves the path to redirect right away after login
            res.redirect("/login/redirected");
        }
    }

    public static boolean isLoggedIn(Response res) throws IOException
    {
        if (token != null && userName != null)
        {
            try
            {
                return JWTLoginUtil.checkToken(token).get("sub", String.class).equals(userName);
            } catch (ExpiredJwtException e)
            {
                handleTimeout(res);
            }
        }
        return false;
    }
}
