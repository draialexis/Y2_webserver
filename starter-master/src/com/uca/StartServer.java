package com.uca;

import com.uca.dao._Initializer;
import com.uca.gui.*;
import com.uca.util.LoginUtil;
import com.uca.util.PropertiesReader;
import freemarker.template.TemplateException;
import spark.Response;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Objects;

import static com.uca.util.RequestUtil.getParamFromReqBody;
import static com.uca.util.RequestUtil.getParamUTF8;
import static java.net.HttpURLConnection.*;
import static spark.Spark.*;

public class StartServer
{

    private static final int portInUse;

    static
    {
        try
        {
            portInUse = Integer.parseInt(new PropertiesReader().getProperty("port"));
        } catch (IOException e)
        {
            e.printStackTrace();
            throw new RuntimeException("could not read port number in configs");
        }
    }

    public static void main(String[] args)
    {
        //Configure Spark
        staticFiles.location("/static/");
        port(portInUse);

        _Initializer.Init();

        before("/hidden/*", LoginUtil::isLoggedInOrElseRedirect);

        exception(Exception.class, (e, req, res) -> {
            int code = 0;
            Objects.requireNonNull(e);
            Class<? extends Exception> eClass = e.getClass();
            if (
                    eClass == NoSuchElementException.class
                    || eClass == IllegalArgumentException.class // for invalid IDs...
                    || eClass == NumberFormatException.class // for invalid IDs...
            )
            {
                code = HTTP_NOT_FOUND;
            }
            if (eClass == OperationNotSupportedException.class)
            {
                code = HTTP_BAD_METHOD;
            }
            if (code > 0)
            {
                res.status(code);
            }
            else
            {
                res.status(HTTP_INTERNAL_ERROR);
            }
        });

        //===============Auth & Index===============
        get("/", (req, res) -> IndexGUI.display());

        get("/login", (req, res) -> LoginGUI.display(null));

        get("/login/timeout", (req, res) -> LoginGUI.display(InfoMsg.SESSION_TERMINEE_AUTHENTIFICATION_NECESSAIRE));

        get("/login/redirect", (req, res) -> LoginGUI.display(InfoMsg.AUTHENTIFICATION_NECESSAIRE));

        get("/hidden/signup", (req, res) -> SignUpGUI.display());

        post("/login", (req, res) -> {

            return LoginUtil.handleLoginPost(req, res);

        });

        get("/logout", (req, res) -> {

            return LoginUtil.handleLogout(res);

        });

        //===============CR** teachers===============
        post("/hidden/signup",
             (req, res) -> {
                 HashMap<String, String> params = getParamFromReqBody(req.body());
                 return TeacherGUI.create(getParamUTF8(params, "firstname"),
                                          getParamUTF8(params, "lastname"),
                                          getParamUTF8(params, "username"),
                                          getParamUTF8(params, "userpwd"),
                                          getParamUTF8(params, "userpwd-validation"));
             });

        get("/hidden/teachers", (req, res) -> TeacherGUI.readAll());

        get("/hidden/teachers/:id_teacher",
            (req, res) -> TeacherGUI.readById(Long.parseLong(req.params(":id_teacher"))));

        //===============CRUD students===============
        post("/hidden/students", (req, res) -> {
            HashMap<String, String> params = getParamFromReqBody(req.body());
            return StudentGUI.create(getParamUTF8(params, "lastname"),
                                     getParamUTF8(params, "firstname"));
        });

        get("/hidden/students", (req, res) -> StudentGUI.readAll());

        get("/hidden/students/:id_student",
            (req, res) -> StudentGUI.readById(Long.parseLong(req.params(":id_student"))));

        post("/hidden/students/:id_student",
             (req, res) -> {
                 HashMap<String, String> params = getParamFromReqBody(req.body());
                 return StudentGUI.update(Long.parseLong(req.params(":id_student")),
                                          getParamUTF8(params, "lastname"),
                                          getParamUTF8(params, "firstname"));
             });

        post("/hidden/students/delete/:id_student",
             (req, res) -> StudentGUI.deleteById(Long.parseLong(req.params(":id_student"))));

        //        // NOT ALLOWED
        //        get("/hidden/students/delete/:id_student",
        //            (req, res) -> manageExceptions(
        //                    new OperationNotSupportedException(InfoMsg.INADEQUATE_HTTP_VERB.name()),
        //                    res)
        //        );

        //===============CRUD stickers===============
        post("/hidden/stickers", (req, res) -> {
            HashMap<String, String> params = getParamFromReqBody(req.body());
            return StickerGUI.create(getParamUTF8(params, "color"),
                                     getParamUTF8(params, "description"));
        });

        get("/stickers", (req, res) -> {
            return StickerGUI.readAll(LoginUtil.isLoggedIn(req, res));
        });

        get("/stickers/:id_sticker",
            (req, res) -> StickerGUI.readById(LoginUtil.isLoggedIn(req, res),
                                              Long.parseLong(req.params(":id_sticker"))));

        post("/hidden/stickers/:id_sticker",
             (req, res) -> {
                 HashMap<String, String> params = getParamFromReqBody(req.body());
                 return StickerGUI.update(Long.parseLong(req.params(":id_sticker")),
                                          getParamUTF8(params, "color"),
                                          getParamUTF8(params, "description"));
             });

        post("/hidden/stickers/delete/:id_sticker",
             (req, res) -> StickerGUI.deleteById(Long.parseLong(req.params(":id_sticker"))));

        // NOT ALLOWED
        //        get("/hidden/stickers/delete/:id_sticker",
        //            (req, res) -> manageExceptions(
        //                    new OperationNotSupportedException(InfoMsg.INADEQUATE_HTTP_VERB.name()),
        //                    res)
        //        );

        //===============CR*D awards===============
        post("/hidden/awards", (req, res) -> {
            HashMap<String, String> params = getParamFromReqBody(req.body());
            return AwardGUI.create(
                    getParamUTF8(params, "motive"),
                    LoginUtil.getUserName(req),
                    Long.parseLong(getParamUTF8(params, "student-id")),
                    Long.parseLong(getParamUTF8(params, "sticker-id")));
        });

        // NOT SUPPORTED
        //        get("/hidden/awards",
        //            (req, res) -> manageExceptions(
        //                    new OperationNotSupportedException(InfoMsg.WRONG_URL__NOT_HIDDEN.name()),
        //                    res)
        //        );
        //TODO implement

        get("/awards", (req, res) -> AwardGUI.readAll(LoginUtil.isLoggedIn(req, res)));

        get("/awards/student/:id_student",
            (req, res) -> AwardGUI.readByStudentId(LoginUtil.isLoggedIn(req, res),
                                                   Long.parseLong(req.params(":id_student"))));

        get("/awards/id/:id_award",
            (req, res) -> AwardGUI.readById(LoginUtil.isLoggedIn(req, res), Long.parseLong(req.params(":id_award"))));

        post("/hidden/awards/delete/:id_award",
             (req, res) -> AwardGUI.deleteById(Long.parseLong(req.params(":id_award"))));

        // NOT ALLOWED
        //        get("/hidden/awards/delete/:id_award",
        //            (req, res) -> manageExceptions(
        //                    new OperationNotSupportedException(InfoMsg.INADEQUATE_HTTP_VERB.name()),
        //                    res)
        //        );
    }
}
