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
    private static final int    SEC_IN_MIN           = 60;
    private static final int    SEC_IN_HOUR          = SEC_IN_MIN * 60;
    private static final int    COOKIE_MAX_AGE       = SEC_IN_HOUR;
    private static final String COOKIE_PATH_NAME     = "pathinfo";
    private static final String COOKIE_TOKEN_NAME    = "token";
    private static final String COOKIE_USERNAME_NAME = "username";

    private static String getSavedPath(Request req)
    {
        return req.cookie(COOKIE_PATH_NAME);
    }

    private static void setSavedPath(Response res, String savedPath)
    {
        res.cookie("/login", COOKIE_PATH_NAME, savedPath, COOKIE_MAX_AGE, false);
    }

    private static String getToken(Request req)
    {
        return req.cookie(COOKIE_TOKEN_NAME);
    }

    private static void setToken(Response res, String token)
    {
        res.cookie("/", COOKIE_TOKEN_NAME, token, COOKIE_MAX_AGE, false);
        // "secure" is set to false because of localhost testing
    }

    public static String getUserName(Request req)
    {
        return req.cookie(COOKIE_USERNAME_NAME);
    }

    private static void setUserName(Response res, String userName)
    {
        res.cookie("/", COOKIE_USERNAME_NAME, userName, COOKIE_MAX_AGE, false);
    }

    private static void disconnect(Response res)
    {
        res.removeCookie(COOKIE_USERNAME_NAME);
        res.removeCookie(COOKIE_TOKEN_NAME);
        res.removeCookie(COOKIE_PATH_NAME);
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
        res.removeCookie("/login", COOKIE_PATH_NAME);
        String userName = req.queryParams(COOKIE_USERNAME_NAME);
        if (!authenticate(userName, req.queryParams("userpwd")))
        {
            return LoginGUI.display(InfoMsg.ECHEC_AUTHENTIFICATION);
        }

        setToken(res, JWTLoginUtil.makeToken(req.queryParams(COOKIE_USERNAME_NAME)));
        setUserName(res, userName);
        // if user was redirected here, they are sent back to their original destination -- else, to index
        res.redirect(savedPath == null ? "/" : savedPath);

        return null; // never reached...
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
            res.redirect("/login/redirect");
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
