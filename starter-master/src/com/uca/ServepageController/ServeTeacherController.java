package com.uca.ServepageController;

import com.uca.Login.LoginController;
import com.uca.gui.TeacherGUI;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;

import java.io.IOException;

import static com.uca.util.Request.clientAcceptsHtml;

public class ServeTeacherController {
    public static String getATeacherById(Request request, Response response) throws TemplateException, IOException {
        LoginController.ensureUserIsLoggedIn(request, response);
        if (clientAcceptsHtml(request)) {
            return TeacherGUI.readById(Long.parseLong(request.params(":id_teacher")));
        }
        return null;
    }

    public static String getATeacherByUser_name(Request request, Response response) throws TemplateException, IOException {
        LoginController.ensureUserIsLoggedIn(request, response);
        if (clientAcceptsHtml(request)) {
            return TeacherGUI.readByUserName(request.params(":username"));
        }
        return null;
    }

}