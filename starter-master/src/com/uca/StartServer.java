package com.uca;

import com.uca.login.LoginController;
import com.uca.dao._Initializer;
import com.uca.gui.*;

import java.util.HashMap;

import static com.uca.util.Request.*;
import static spark.Spark.*;

public class StartServer
{
    public static final int PORT = 8081;

    public static void main(String[] args)
    {
        //Configure Spark
        staticFiles.location("/static/");
        port(PORT);

        _Initializer.Init();

        get("/", (req, res) -> IndexGUI.display());

        get("/teachers", (req, res) -> TeacherGUI.readAll());

        get("/login", (req, res) -> LoginGUI.display("merci de vous identifier"));

        post("/login", LoginController::handleLoginPost);

        get("/teachers/id/:id_teacher", (req, res) ->
        {
            LoginController.ensureUserIsLoggedIn(req, res);
            if (clientAcceptsHtml(req))
            {
                return TeacherGUI.readById(Long.parseLong(req.params(":id_teacher")));
            }
            return null;
        });

        get("/teachers/user/:username", (req, res) -> {
            LoginController.ensureUserIsLoggedIn(req, res);
            if (clientAcceptsHtml(req))
            {
                return TeacherGUI.readByUserName(req.params(":username"));
            }
            return null;
        });

        get("/signup", (req, res) -> {
            LoginController.ensureUserIsLoggedIn(req, res);
            if (clientAcceptsHtml(req))
            {
                return SignUpGUI.display();
            }
            return null;
        });

        post("/signup",
             (req, res) -> {
                 LoginController.ensureUserIsLoggedIn(req, res);
                 if (clientAcceptsHtml(req))
                 {
                     HashMap<String, String> params = getParamFromReqBody(req.body());
                     return TeacherGUI.create(getParamUTF8(params, "firstname"),
                                             getParamUTF8(params, "lastname"),
                                             getParamUTF8(params, "username"),
                                             getParamUTF8(params, "userpwd"));
                 }
                 return null;
             });

        get("/stickers", (req, res) -> StickerGUI.readAll());

        get("/stickers/:id_sticker", (req, res) -> StickerGUI.readById(Long.parseLong(req.params(":id_sticker"))));
    }
}