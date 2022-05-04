package com.uca.Login;


import com.uca.gui.LoginGui;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import java.io.IOException;


import static com.uca.dao.TeacherController.authenticate;
import static com.uca.util.Request.*;

public class LoginController {

    public static String pathSaved;
    public static String handleLoginPost(Request req, Response res) throws TemplateException, IOException {
        if (!authenticate(getQueryUsername(req), getQueryPassword(req))) {
            return LoginGui.LoginFailedGUI();
        }
        req.session().attribute("currentUser", getQueryUsername(req));
        if (pathSaved != null)
            res.redirect(pathSaved); // if he requested to go somewhere without being logged in once logged he will be redirected where he wished
        res.redirect("/"); // so that when the user is logged in without a query we redirect him to the root
        return null;
    }

    public static void ensureUserIsLoggedIn(Request request, Response response) {
        if (request.session().attribute("currentUser") == null) {
            pathSaved = request.pathInfo(); // saves the path to redirect right away after login
            response.redirect("/login");
        }
    }
}
