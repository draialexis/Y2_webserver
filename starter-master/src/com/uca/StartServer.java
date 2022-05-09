package com.uca;

import com.uca.core.StickerCore;
import com.uca.entity.Color;
import com.uca.entity.Description;
import com.uca.entity.StickerEntity;
import com.uca.util.LoginUtil;
import com.uca.dao._Initializer;
import com.uca.gui.*;

import java.util.HashMap;

import static com.uca.util.RequestUtil.*;
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_OK;
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

        //Index
        get("/", (req, res) -> IndexGUI.display(LoginUtil.isLoggedIn(req)));

        //Login
        get("/login", (req, res) -> LoginGUI.display("merci de vous identifier"));

        post("/login", LoginUtil::handleLoginPost);

        //Signup
        get("/signup", (req, res) -> {
            LoginUtil.ensureUserIsLoggedIn(req, res);
            return SignUpGUI.display();
        });

        //CR** teachers
        post("/signup",
             (req, res) -> {
                 LoginUtil.ensureUserIsLoggedIn(req, res);
                 HashMap<String, String> params = getParamFromReqBody(req.body());
                 return TeacherGUI.create(getParamUTF8(params, "firstname"),
                                          getParamUTF8(params, "lastname"),
                                          getParamUTF8(params, "username"),
                                          getParamUTF8(params, "userpwd"),
                                          getParamUTF8(params, "userpwd-validation"));
             });

        get("/teachers", (req, res) -> TeacherGUI.readAll());

        get("/teachers/:id_teacher", (req, res) -> {
            LoginUtil.ensureUserIsLoggedIn(req, res);
            return TeacherGUI.readById(Long.parseLong(req.params(":id_teacher")));
        });

        //CRUD students
        post("/students",
             (req, res) -> {
                 LoginUtil.ensureUserIsLoggedIn(req, res);
                 HashMap<String, String> params = getParamFromReqBody(req.body());
                 return StudentGUI.create(getParamUTF8(params, "lastname"),
                                          getParamUTF8(params, "firstname"));

             });

        get("/students", (req, res) -> {
            LoginUtil.ensureUserIsLoggedIn(req, res);
            return StudentGUI.readAll();
        });

        get("/students/:id_student", (req, res) -> {
            LoginUtil.ensureUserIsLoggedIn(req, res);
            return StudentGUI.readById(Long.parseLong(req.params(":id_student")));
        });

        post("/students/:id_student",
             (req, res) -> {
                 LoginUtil.ensureUserIsLoggedIn(req, res);
                 HashMap<String, String> params = getParamFromReqBody(req.body());
                 return StudentGUI.update(Long.parseLong(req.params(":id_student")),
                                          getParamUTF8(params, "lastname"),
                                          getParamUTF8(params, "firstname"));
             });

        post("/students/delete/:id_student", (req, res) -> {
            LoginUtil.ensureUserIsLoggedIn(req, res);
            return StudentGUI.deleteById(Long.parseLong(req.params(":id_student")));
        });

        //CRUD stickers
        post("/stickers", (req, res) -> {
            LoginUtil.ensureUserIsLoggedIn(req, res);
            HashMap<String, String> params = getParamFromReqBody(req.body());
            return StickerGUI.create(getParamUTF8(params, "color"),
                                     getParamUTF8(params, "description"));

        });

        get("/stickers", (req, res) -> StickerGUI.readAll());

        get("/stickers/:id_sticker", (req, res) -> StickerGUI.readById(Long.parseLong(req.params(":id_sticker"))));

        post("/stickers/:id_sticker",
             (req, res) -> {
                 LoginUtil.ensureUserIsLoggedIn(req, res);
                 HashMap<String, String> params = getParamFromReqBody(req.body());
                 return StudentGUI.update(Long.parseLong(req.params(":id_sticker")),
                                          getParamUTF8(params, "color"),
                                          getParamUTF8(params, "description"));
             });

        post("/stickers/delete/:id_sticker", (req, res) -> {
            LoginUtil.ensureUserIsLoggedIn(req, res);
            return StickerGUI.deleteById(Long.parseLong(req.params(":id_sticker")));
        });

        //CR*D awards
        post("/awards", (req, res) -> {
            LoginUtil.ensureUserIsLoggedIn(req, res);
            HashMap<String, String> params = getParamFromReqBody(req.body());
            try
            {
                String view = AwardGUI.create(
                        getParamUTF8(params, "motive"),
                        req.session().attribute("currentUser"),
                        Long.parseLong(getParamUTF8(params, "student-id")),
                        Long.parseLong(getParamUTF8(params, "sticker-id")));
                res.status(HTTP_OK);
                return view;
            } catch (Exception e)
            {
                e.printStackTrace();
                res.status(HTTP_BAD_REQUEST); // TODO improve?
                return null;
            }
        });

        get("/awards", (req, res) -> AwardGUI.readAll(LoginUtil.isLoggedIn(req)));

        get("/awards/student/:id_student",
            (req, res) -> AwardGUI.readByStudentId(LoginUtil.isLoggedIn(req),
                                                   Long.parseLong(req.params(":id_student"))));

        get("/awards/id/:id_award",
            (req, res) -> AwardGUI.readById(LoginUtil.isLoggedIn(req), Long.parseLong(req.params(":id_award"))));

        post("/awards/delete/:id_award", (req, res) -> {
            LoginUtil.ensureUserIsLoggedIn(req, res);
            return AwardGUI.deleteById(Long.parseLong(req.params(":id_award")));
        });
    }
}