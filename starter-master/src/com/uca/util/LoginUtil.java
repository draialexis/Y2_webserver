package com.uca.util;

import com.uca.core.TeacherCore;
import com.uca.entity.TeacherEntity;
import com.uca.gui.InfoMsg;
import com.uca.gui.LoginGUI;
import freemarker.template.TemplateException;
import io.jsonwebtoken.ExpiredJwtException;
import spark.Request;
import spark.Response;

import java.io.IOException;

import static com.uca.util.StringUtil.isValidShortString;

public class LoginUtil
{
    private static final int SEC_IN_MIN     = 60;
    private static final int SEC_IN_HOUR    = SEC_IN_MIN * 60;
    private static final int COOKIE_MAX_AGE = SEC_IN_HOUR;

    private static String getSavedPath(Request req)
    {
        return req.cookie("pathinfo");
    }

    private static void setSavedPath(Response res, String savedPath)
    {
        res.cookie("/login", "pathinfo", savedPath, COOKIE_MAX_AGE, false);
    }

    private static String getToken(Request req)
    {
        return req.cookie("token");
    }

    private static void setToken(Response res, String token)
    {
        res.cookie("/", "token", token, COOKIE_MAX_AGE, false);
    }

    public static String getUserName(Request req)
    {
        return req.cookie("username");
    }

    private static void setUserName(Response res, String userName)
    {
        res.cookie("/", "username", userName, COOKIE_MAX_AGE, false);
    }

    private static void disconnect(Response res)
    {
        res.removeCookie("username");
        res.removeCookie("token");
        res.removeCookie("pathinfo");
    }

    private static void handleTimeout(Request req, Response res)
    {
        disconnect(res);
        setSavedPath(res, req.pathInfo());
        res.redirect("/login/timeout");
    }

    public static String handleLogout(Response res) throws TemplateException, IOException
    {
        disconnect(res);
        return LoginGUI.display(InfoMsg.DECONNEXION_SUCCES);
    }

    public static String handleLoginPost(Request req, Response res) throws TemplateException, IOException
    {
        String savedPath = getSavedPath(req);
        res.removeCookie("/login", "pathinfo");
        String userName = req.queryParams("username");
        if (!authenticate(userName, req.queryParams("userpwd")))
        {
            return LoginGUI.display(InfoMsg.ECHEC_AUTHENTIFICATION);
        }

        setToken(res, JWTLoginUtil.makeToken(req.queryParams("username")));
        setUserName(res, userName);
        // if user was redirected here, they are sent back to their original destination -- else, to index
        res.redirect(savedPath == null ? "/" : savedPath);
        return null;
    }

    private static boolean authenticate(String userName, String userPwd)
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

    public static void isLoggedInOrElseRedirect(Request req, Response res) throws IOException
    {
        if (!isLoggedIn(req, res))
        {
            setSavedPath(res, req.pathInfo()); // saves the path to redirect right away after login
            res.redirect("/login/redirected");
        }
    }

    public static boolean isLoggedIn(Request req, Response res) throws IOException
    {
        String token    = getToken(req);
        String userName = getUserName(req);
        if (token != null && userName != null)
        {
            try
            {
                return JWTLoginUtil.checkToken(token).get("sub", String.class).equals(userName);
            } catch (ExpiredJwtException e)
            {
                handleTimeout(req, res);
            }
        }
        return false;
    }
}
