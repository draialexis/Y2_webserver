package com.uca;

import com.uca.core.StickerCore;
import com.uca.core.StudentCore;
import com.uca.entity.Color;
import com.uca.entity.Description;
import com.uca.entity.StickerEntity;
import com.uca.entity.StudentEntity;
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

        get("/students", (req, res) ->{
            LoginController.ensureUserIsLoggedIn(req, res);
            if (clientAcceptsHtml(req))
            {
                return StudentGUI.readAll();
            }
            return null;
        });

        get("/students/:id_student", (req, res) -> {
            LoginController.ensureUserIsLoggedIn(req, res);
            if (clientAcceptsHtml(req))
            {
                return StudentGUI.readById(Long.parseLong(req.params(":id_student")));
            }
            return null;
        });
        post("/students", (req, res) ->
        {
            LoginController.ensureUserIsLoggedIn(req, res);
            if (clientAcceptsHtml(req))
            {
                return StudentGUI.displayCreate();
            }
            return null;
        });

        post("/students/create",
                (req, res) -> {
                    LoginController.ensureUserIsLoggedIn(req, res);
                    if (clientAcceptsHtml(req))
                    {
                        HashMap<String, String> params = getParamFromReqBody(req.body());
                        StudentGUI.create(getParamUTF8(params, "lastname"),
                                getParamUTF8(params, "firstName"));
                        res.redirect("/students");
                    }
                    return null;
                });
        post("/students/:id_student",
                (req, res) ->{
                    LoginController.ensureUserIsLoggedIn(req, res);
                    if (clientAcceptsHtml(req))
                    {
                        StudentGUI.displayModifPage(Long.parseLong(req.params(":id_student")));
                        return StudentGUI.displayModifPage(Long.parseLong(req.params(":id_student")));
                    }
                    return null;
                });
        post("/students/modif/:id_student",
                (req, res) ->{
                    LoginController.ensureUserIsLoggedIn(req, res);
                    if (clientAcceptsHtml(req))
                    {
                        HashMap<String, String> params = getParamFromReqBody(req.body());
                        StudentEntity student = new StudentEntity();
                        student.setFirstName(getParamUTF8(params, "firstName"));
                        student.setLastName(getParamUTF8(params, "lastname"));
                        student.setId(Long.parseLong(req.params(":id_student")));
                        StudentCore.update(student, student.getId());
                        res.redirect("/students");
                    }
                    return null;
                });
        post("/students/delete/:id_student", (req, res) -> {
            LoginController.ensureUserIsLoggedIn(req, res);
            if (clientAcceptsHtml(req))
            {
                StudentCore.deletbyId(Long.parseLong(req.params(":id_student")));
                res.redirect("/students");
            }
            return null;
        });
        get("/login", (req, res) -> LoginGUI.display("merci de vous identifier"));

        post("/login", LoginController::handleLoginPost);

        get("/teachers/id/:id_teacher", (req, res) -> {
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
        post("/stickers", (req, res) -> {
            LoginController.ensureUserIsLoggedIn(req, res);
            if (clientAcceptsHtml(req)) {
                return StickerGUI.dispalyCreatePage();
            }
            return null;
        });

        post("/stickers/create", (req, res) -> {
            LoginController.ensureUserIsLoggedIn(req, res);
            if (clientAcceptsHtml(req)) {
                HashMap<String, String> params = getParamFromReqBody(req.body());
                StickerEntity sticker  = new StickerEntity();
                sticker.setColor(Color.valueOf(getParamUTF8(params, "color")));
                sticker.setDescription(Description.valueOf(getParamUTF8(params, "description")));
                sticker.setId(StickerCore.findLastId() + 1);
                StickerCore.create(sticker);
                res.redirect("/stickers");
            }
            return null;
        });

        post("/stickers/:id_sticker", (req, res) -> {
            LoginController.ensureUserIsLoggedIn(req, res);
            if (clientAcceptsHtml(req)) {
                return StickerGUI.dispalyModifPage(Long.parseLong(req.params(":id_sticker")));
            }
            return null;
        });

        post("/stickers/modif/:id_sticker", (req, res) -> {
            LoginController.ensureUserIsLoggedIn(req, res);
            if (clientAcceptsHtml(req)) {
                HashMap<String, String> params = getParamFromReqBody(req.body());
                StickerEntity sticker  = new StickerEntity();
                sticker.setColor(Color.valueOf(getParamUTF8(params, "color")));
                sticker.setDescription(Description.valueOf(getParamUTF8(params, "description")));
                sticker.setId(Long.parseLong(req.params(":id_sticker"))); // set the id of the new sticker the last one this way we don't need to auto increment
                StickerCore.update(sticker, sticker.getId());
                res.redirect("/stickers");
            }
            return null;
        });

        post("/stickers/delete/:id_sticker", (req, res) -> {
            LoginController.ensureUserIsLoggedIn(req, res);
            if (clientAcceptsHtml(req)) {
                StickerCore.deleteById(Long.parseLong(req.params(":id_sticker")));
                res.redirect("/stickers");
            }
            return null;
        });
    }
}