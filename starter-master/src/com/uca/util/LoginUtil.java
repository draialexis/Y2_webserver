package com.uca.util;

import com.uca.core.TeacherCore;
import com.uca.entity.TeacherEntity;
import com.uca.gui.InfoMsg;
import com.uca.gui.LoginGUI;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;

import java.io.IOException;

import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;
import static spark.Spark.halt;

public class LoginUtil
{
    public static String pathSaved = null;

    public static String handleLoginPost(Request req, Response res) throws TemplateException, IOException
    {
        if (!authenticate(req.queryParams("username"), req.queryParams("userpwd")))
        {
            return LoginGUI.display(InfoMsg.ECHEC_AUTHENTIFICATION);
        }
        // else, login success
        req.session().attribute("currentUser", req.queryParams("username"));
        if (pathSaved != null)
        {
            res.redirect(pathSaved);
            // if they requested to go somewhere without being logged in...
            // once logged in they will be redirected there
        }
        res.redirect("/");
        // if logged in without a query we redirect them to the root
        return null;
    }

    public static boolean authenticate(String username, String password)
    {
        if (username.isEmpty() || password.isEmpty())
        { // tried to log in without filling in the form
            return false;
        }
        TeacherEntity user = TeacherCore.readByUserName(username);
        if (user == null)
        { // means the user doesn't exist
            return false;
        }
        return Encryptor.verifyUserPassword(password, user.getUserPwd(), user.getUserSalt());
    }

    public static void ensureUserIsLoggedIn(Request req, Response res)
    {
        if (!isLoggedIn(req))
        {
            pathSaved = req.pathInfo(); // saves the path to redirect right away after login
            res.redirect("/login");
            halt(HTTP_UNAUTHORIZED);
        }
    }

    public static boolean isLoggedIn(Request req)
    {
        return req.session().attribute("currentUser") != null;
    }
}
