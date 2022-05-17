package com.uca;

import com.uca.dao._Initializer;
import com.uca.gui.*;
import com.uca.util.LoginUtil;
import com.uca.util.PropertiesReader;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;

import static com.uca.util.RequestUtil.getParamFromReqBody;
import static com.uca.util.RequestUtil.getParamUTF8;
import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import static java.net.HttpURLConnection.HTTP_BAD_METHOD;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
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

            e.printStackTrace();

            Class<? extends Exception> eClass = e.getClass();
            if (
                    eClass == NoSuchElementException.class
                    || eClass == IllegalArgumentException.class // for invalid IDs...
                    || eClass == NumberFormatException.class // for invalid IDs...
            )
            {
                res.status(HTTP_NOT_FOUND);
            }
            else
            {
                if (eClass == OperationNotSupportedException.class)
                {
                    res.status(HTTP_BAD_METHOD);
                }
                else
                {
                    res.status(HTTP_INTERNAL_ERROR);
                }
            }
        });

        //===============Auth & Index===============
        // index page
        get("/", (req, res) -> IndexGUI.display());

        // login page
        get("/login", (req, res) -> LoginGUI.display(null));
        get("/login/timeout", (req, res) -> LoginGUI.display(InfoMsg.SESSION_TERMINEE_AUTHENTIFICATION_NECESSAIRE));
        get("/login/redirect", (req, res) -> LoginGUI.display(InfoMsg.AUTHENTIFICATION_NECESSAIRE));

        // log in / create session
        post("/login", LoginUtil::handleLoginPost);

        // log out
        get("/logout", (req, res) -> LoginUtil.handleLogout(res));

        // signup page
        get("/hidden/signup", (req, res) -> SignUpGUI.display());

        //===============CR** teachers===============
        // create
        post("/hidden/teachers", (req, res) -> {
            HashMap<String, String> params = getParamFromReqBody(req.body());
            return TeacherGUI.create(getParamUTF8(params, "lastname"),
                                     getParamUTF8(params, "firstname"),
                                     getParamUTF8(params, "username"),
                                     getParamUTF8(params, "userpwd"),
                                     getParamUTF8(params, "userpwd-validation"));
        });

        // read all
        get("/hidden/teachers", (req, res) -> TeacherGUI.readAll());

        // read one
        get("/hidden/teachers/:id_teacher",
            (req, res) -> TeacherGUI.readById(Long.parseLong(req.params(":id_teacher"))));

        //===============CRUD students===============
        // create
        post("/hidden/students", (req, res) -> {
            HashMap<String, String> params = getParamFromReqBody(req.body());
            return StudentGUI.create(getParamUTF8(params, "lastname"),
                                     getParamUTF8(params, "firstname"));
        });

        // read all
        get("/hidden/students", (req, res) -> StudentGUI.readAll());

        // read one
        get("/hidden/students/:id_student",
            (req, res) -> StudentGUI.readById(Long.parseLong(req.params(":id_student"))));

        // TODO remove and replace with PUT below
        post("/hidden/students/:id_student", (req, res) -> {
            HashMap<String, String> params = getParamFromReqBody(req.body());
            return StudentGUI.update(Long.parseLong(req.params(":id_student")),
                                     getParamUTF8(params, "lastname"),
                                     getParamUTF8(params, "firstname"));
        });

        // update
        put("/hidden/students/:id_student", (req, res) -> {
            HashMap<String, String> params = getParamFromReqBody(req.body());
            return StudentGUI.update(Long.parseLong(req.params(":id_student")),
                                     getParamUTF8(params, "lastname"),
                                     getParamUTF8(params, "firstname"));
        });

        // TODO remove and replace with DELETE below
        post("/hidden/students/delete/:id_student",
             (req, res) -> StudentGUI.deleteById(Long.parseLong(req.params(":id_student"))));

        // delete
        delete("/hidden/students/:id_student",
               (req, res) -> StudentGUI.deleteById(Long.parseLong(req.params(":id_student"))));

        //===============CRUD stickers===============
        // create
        post("/hidden/stickers", (req, res) -> {
            HashMap<String, String> params = getParamFromReqBody(req.body());
            return StickerGUI.create(getParamUTF8(params, "color"),
                                     getParamUTF8(params, "description"));
        });

        // read all
        get("/stickers", (req, res) -> StickerGUI.readAll(LoginUtil.isLoggedIn(req, res)));

        // read one
        get("/stickers/:id_sticker",
            (req, res) -> StickerGUI.readById(LoginUtil.isLoggedIn(req, res),
                                              Long.parseLong(req.params(":id_sticker"))));

        // TODO remove and replace with PUT below
        post("/hidden/stickers/:id_sticker", (req, res) -> {
            HashMap<String, String> params = getParamFromReqBody(req.body());
            return StickerGUI.update(Long.parseLong(req.params(":id_sticker")),
                                     getParamUTF8(params, "color"),
                                     getParamUTF8(params, "description"));
        });

        // update
        put("/hidden/stickers/:id_sticker", (req, res) -> {
            HashMap<String, String> params = getParamFromReqBody(req.body());
            return StickerGUI.update(Long.parseLong(req.params(":id_sticker")),
                                     getParamUTF8(params, "color"),
                                     getParamUTF8(params, "description"));
        });

        // TODO remove and replace with DELETE below
        post("/hidden/stickers/delete/:id_sticker",
             (req, res) -> StickerGUI.deleteById(Long.parseLong(req.params(":id_sticker"))));

        // delete
        delete("/hidden/stickers/:id_sticker",
               (req, res) -> StickerGUI.deleteById(Long.parseLong(req.params(":id_sticker"))));

        //===============CR*D awards===============
        // create
        post("/hidden/awards", (req, res) -> {
            HashMap<String, String> params = getParamFromReqBody(req.body());
            return AwardGUI.create(
                    getParamUTF8(params, "motive"),
                    LoginUtil.getUserName(req),
                    Long.parseLong(getParamUTF8(params, "student-id")),
                    Long.parseLong(getParamUTF8(params, "sticker-id")));
        });

        // read all
        get("/awards", (req, res) -> AwardGUI.readAll(LoginUtil.isLoggedIn(req, res)));

        // read many by student id
        get("/awards/student/:id_student",
            (req, res) -> AwardGUI.readByStudentId(LoginUtil.isLoggedIn(req, res),
                                                   Long.parseLong(req.params(":id_student"))));

        // read one
        get("/awards/id/:id_award",
            (req, res) -> AwardGUI.readById(LoginUtil.isLoggedIn(req, res), Long.parseLong(req.params(":id_award"))));

        // TODO remove and replace with DELETE below
        post("/hidden/awards/delete/:id_award",
             (req, res) -> AwardGUI.deleteById(Long.parseLong(req.params(":id_award"))));

        // delete
        delete("/hidden/awards/:id_award",
               (req, res) -> AwardGUI.deleteById(Long.parseLong(req.params(":id_award"))));
    }
}
