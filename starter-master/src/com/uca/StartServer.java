package com.uca;

import com.uca.dao._Initializer;
import com.uca.gui.*;
import freemarker.template.TemplateException;
import spark.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Objects;

import static com.uca.util.RequestUtil.getParamFromReqBody;
import static com.uca.util.RequestUtil.getParamUTF8;
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static spark.Spark.*;

public class StartServer
{
    private static final int PORT      = 8081;
    private static final int NULL_CODE = 0;

    private static int code = NULL_CODE;

    private static int useAndResetCode()
    {
        int out = code;
        nullifyCode();
        return out;
    }

    private static void nullifyCode()
    {
        code = NULL_CODE;
    }

    private static String manageExceptions(Exception e, Response res) throws TemplateException, IOException
    {
        Objects.requireNonNull(e);
        Class<? extends Exception> eClass = e.getClass();
        if (eClass == IllegalArgumentException.class
            || eClass == NumberFormatException.class)
        {
            code = HTTP_BAD_REQUEST;
        }
        if (eClass == NoSuchElementException.class)
        {
            code = HTTP_NOT_FOUND;
        }
        if (code > 0)
        {
            res.status(code);
            return ErrorGUI.display(useAndResetCode(), e.toString());
        }
        return ErrorGUI.displayUnknown();
    }

    public static void main(String[] args)
    {
        //Configure Spark
        staticFiles.location("/static/");
        port(PORT);

        _Initializer.Init();

        before("/hidden/*", LoginHandler::ensureUserIsLoggedIn);

        internalServerError("<html><body>" +
                            "<h1>500</h1>" +
                            "<h2>Uh oh... erreur interne du serveur, cela ne devrait pas arriver</h2>" +
                            "</body></html>");

        //===============Auth & Index===============
        get("/", (req, res) -> IndexGUI.display());

        get("/login", (req, res) -> LoginGUI.display(null));

        get("/login/timeout", (req, res) -> LoginGUI.display(InfoMsg.SESSION_TERMINEE_AUTHENTIFICATION_NECESSAIRE));

        get("/login/redirected", (req, res) -> LoginGUI.display(InfoMsg.AUTHENTIFICATION_NECESSAIRE));

        post("/login", LoginHandler::handleLoginPost);

        get("/logout", (req, res) -> LoginHandler.handleLogout());

        get("/hidden/signup", (req, res) -> SignUpGUI.display());

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

        get("/hidden/teachers", (req, res) -> {
            try
            {
                return TeacherGUI.readAll();
            } catch (Exception e)
            {
                return manageExceptions(e, res);
            }
        });

        get("/hidden/teachers/:id_teacher",
            (req, res) -> {
                try
                {
                    return TeacherGUI.readById(Long.parseLong(req.params(":id_teacher")));
                } catch (Exception e)
                {
                    return manageExceptions(e, res);
                }
            });

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

        //===============CRUD stickers===============
        post("/hidden/stickers", (req, res) -> {
            HashMap<String, String> params = getParamFromReqBody(req.body());
            return StickerGUI.create(getParamUTF8(params, "color"),
                                     getParamUTF8(params, "description"));

        });

        get("/stickers", (req, res) -> StickerGUI.readAll(LoginHandler.isLoggedIn(res)));

        get("/stickers/:id_sticker",
            (req, res) -> {
                try
                {
                    return StickerGUI.readById(LoginHandler.isLoggedIn(res), Long.parseLong(req.params(":id_sticker")));
                } catch (Exception e)
                {
                    return manageExceptions(e, res);
                }
            });

        post("/hidden/stickers/:id_sticker",
             (req, res) -> {
                 HashMap<String, String> params = getParamFromReqBody(req.body());
                 return StickerGUI.update(Long.parseLong(req.params(":id_sticker")),
                                          getParamUTF8(params, "color"),
                                          getParamUTF8(params, "description"));
             });

        post("/hidden/stickers/delete/:id_sticker",
             (req, res) -> StickerGUI.deleteById(Long.parseLong(req.params(":id_sticker"))));

        //===============CR*D awards===============
        post("/hidden/awards", (req, res) -> {
            HashMap<String, String> params = getParamFromReqBody(req.body());
            return AwardGUI.create(
                    getParamUTF8(params, "motive"),
                    LoginHandler.getUserName(),
                    Long.parseLong(getParamUTF8(params, "student-id")),
                    Long.parseLong(getParamUTF8(params, "sticker-id")));
        });

        get("/awards", (req, res) -> AwardGUI.readAll(LoginHandler.isLoggedIn(res)));

        get("/awards/student/:id_student",
            (req, res) -> AwardGUI.readByStudentId(LoginHandler.isLoggedIn(res),
                                                   Long.parseLong(req.params(":id_student"))));

        get("/awards/id/:id_award",
            (req, res) -> AwardGUI.readById(LoginHandler.isLoggedIn(res), Long.parseLong(req.params(":id_award"))));

        post("/hidden/awards/delete/:id_award",
             (req, res) -> AwardGUI.deleteById(Long.parseLong(req.params(":id_award"))));
    }
}
