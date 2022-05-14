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

    private static int code;

    private static int useAndResetCode()
    {
        int out = code;
        code = 0;
        return out;
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
        if (eClass == OperationNotSupportedException.class)
        {
            code = HTTP_BAD_METHOD;
        }
        if (eClass == SQLException.class
            || eClass == TemplateException.class
            || eClass == IOException.class)
        {
            code = HTTP_INTERNAL_ERROR;
        }
        if (code > 0)
        {
            res.status(code);
            return ErrorGUI.display(useAndResetCode(), e.toString());
        }
        return ErrorGUI.displayUnknown(e.toString());
    }

    public static void main(String[] args)
    {
        //Configure Spark
        staticFiles.location("/static/");
        port(portInUse);

        _Initializer.Init();

        before("/hidden/*", LoginUtil::isLoggedInOrElseRedirect);

        //===============Auth & Index===============
        get("/", (req, res) -> IndexGUI.display());

        get("/login", (req, res) -> LoginGUI.display(null));

        get("/login/timeout", (req, res) -> LoginGUI.display(InfoMsg.SESSION_TERMINEE_AUTHENTIFICATION_NECESSAIRE));

        get("/login/redirect", (req, res) -> LoginGUI.display(InfoMsg.AUTHENTIFICATION_NECESSAIRE));

        get("/hidden/signup", (req, res) -> SignUpGUI.display());

        post("/login", (req, res) -> {
            try
            {
                return LoginUtil.handleLoginPost(req, res);
            } catch (Exception e)
            {
                return manageExceptions(e, res);
            }
        });

        get("/logout", (req, res) -> {
            try
            {
                return LoginUtil.handleLogout(res);
            } catch (Exception e)
            {
                return manageExceptions(e, res);
            }
        });

        //===============CR** teachers===============
        post("/hidden/signup",
             (req, res) -> {
                 try
                 {
                     HashMap<String, String> params = getParamFromReqBody(req.body());
                     return TeacherGUI.create(getParamUTF8(params, "firstname"),
                                              getParamUTF8(params, "lastname"),
                                              getParamUTF8(params, "username"),
                                              getParamUTF8(params, "userpwd"),
                                              getParamUTF8(params, "userpwd-validation"));
                 } catch (Exception e)
                 {
                     return manageExceptions(e, res);
                 }
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
            try
            {
                HashMap<String, String> params = getParamFromReqBody(req.body());
                return StudentGUI.create(getParamUTF8(params, "lastname"),
                                         getParamUTF8(params, "firstname"));
            } catch (Exception e)
            {
                return manageExceptions(e, res);
            }

        });

        get("/hidden/students", (req, res) -> {
            try
            {
                return StudentGUI.readAll();
            } catch (Exception e)
            {
                return manageExceptions(e, res);
            }
        });

        get("/hidden/students/:id_student",
            (req, res) -> {
                try
                {
                    return StudentGUI.readById(Long.parseLong(req.params(":id_student")));
                } catch (Exception e)
                {
                    return manageExceptions(e, res);
                }
            });

        post("/hidden/students/:id_student",
             (req, res) -> {
                 try
                 {
                     HashMap<String, String> params = getParamFromReqBody(req.body());
                     return StudentGUI.update(Long.parseLong(req.params(":id_student")),
                                              getParamUTF8(params, "lastname"),
                                              getParamUTF8(params, "firstname"));
                 } catch (Exception e)
                 {
                     return manageExceptions(e, res);
                 }

             });

        post("/hidden/students/delete/:id_student",
             (req, res) -> {
                 try
                 {
                     return StudentGUI.deleteById(Long.parseLong(req.params(":id_student")));
                 } catch (Exception e)
                 {
                     return manageExceptions(e, res);
                 }
             });

        // NOT ALLOWED
        get("/hidden/students/delete/:id_student",
            (req, res) -> manageExceptions(
                    new OperationNotSupportedException(InfoMsg.INADEQUATE_HTTP_VERB.name()),
                    res)
        );

        //===============CRUD stickers===============
        post("/hidden/stickers", (req, res) -> {
            try
            {
                HashMap<String, String> params = getParamFromReqBody(req.body());
                return StickerGUI.create(getParamUTF8(params, "color"),
                                         getParamUTF8(params, "description"));
            } catch (Exception e)
            {
                return manageExceptions(e, res);
            }

        });

        get("/stickers", (req, res) -> {
            try
            {
                return StickerGUI.readAll(LoginUtil.isLoggedIn(req, res));
            } catch (Exception e)
            {
                return manageExceptions(e, res);
            }
        });

        get("/stickers/:id_sticker",
            (req, res) -> {
                try
                {
                    return StickerGUI.readById(LoginUtil.isLoggedIn(req, res),
                                               Long.parseLong(req.params(":id_sticker")));
                } catch (Exception e)
                {
                    return manageExceptions(e, res);
                }
            });

        post("/hidden/stickers/:id_sticker",
             (req, res) -> {
                 try
                 {
                     HashMap<String, String> params = getParamFromReqBody(req.body());
                     return StickerGUI.update(Long.parseLong(req.params(":id_sticker")),
                                              getParamUTF8(params, "color"),
                                              getParamUTF8(params, "description"));
                 } catch (Exception e)
                 {
                     return manageExceptions(e, res);
                 }
             });

        post("/hidden/stickers/delete/:id_sticker",
             (req, res) -> {
                 try
                 {
                     return StickerGUI.deleteById(Long.parseLong(req.params(":id_sticker")));
                 } catch (Exception e)
                 {
                     return manageExceptions(e, res);
                 }
             });

        // NOT ALLOWED
        get("/hidden/stickers/delete/:id_sticker",
            (req, res) -> manageExceptions(
                    new OperationNotSupportedException(InfoMsg.INADEQUATE_HTTP_VERB.name()),
                    res)
        );

        //===============CR*D awards===============
        post("/hidden/awards", (req, res) -> {
            try
            {
                HashMap<String, String> params = getParamFromReqBody(req.body());
                return AwardGUI.create(
                        getParamUTF8(params, "motive"),
                        LoginUtil.getUserName(req),
                        Long.parseLong(getParamUTF8(params, "student-id")),
                        Long.parseLong(getParamUTF8(params, "sticker-id")));
            } catch (Exception e)
            {
                return manageExceptions(e, res);
            }
        });

        // NOT SUPPORTED
        get("/hidden/awards",
            (req, res) -> manageExceptions(
                    new OperationNotSupportedException(InfoMsg.WRONG_URL__NOT_HIDDEN.name()),
                    res)
        );

        get("/awards", (req, res) -> {
            try
            {
                return AwardGUI.readAll(LoginUtil.isLoggedIn(req, res));
            } catch (Exception e)
            {
                return manageExceptions(e, res);
            }
        });

        get("/awards/student/:id_student",
            (req, res) -> {
                try
                {
                    return AwardGUI.readByStudentId(LoginUtil.isLoggedIn(req, res),
                                                    Long.parseLong(req.params(":id_student")));
                } catch (Exception e)
                {
                    return manageExceptions(e, res);
                }
            });

        get("/awards/id/:id_award",
            (req, res) -> {
                try
                {
                    return AwardGUI.readById(LoginUtil.isLoggedIn(req, res), Long.parseLong(req.params(":id_award")));
                } catch (Exception e)
                {
                    return manageExceptions(e, res);
                }
            });

        post("/hidden/awards/delete/:id_award",
             (req, res) -> {
                 try
                 {
                     return AwardGUI.deleteById(Long.parseLong(req.params(":id_award")));
                 } catch (Exception e)
                 {
                     return manageExceptions(e, res);
                 }
             });

        // NOT ALLOWED
        get("/hidden/awards/delete/:id_award",
            (req, res) -> manageExceptions(
                    new OperationNotSupportedException(InfoMsg.INADEQUATE_HTTP_VERB.name()),
                    res)
        );
    }
}
