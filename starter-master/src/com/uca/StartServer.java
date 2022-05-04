package com.uca;

import com.uca.dao._Initializer;
import com.uca.gui.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class StartServer
{
    public static final int PORT = 8081;

    private static HashMap<String, String> getParamFromReqBody(String body)
    {
        HashMap<String, String> params = new HashMap<>();
        for (String s : body.split("&"))
        {
            String[] kv = s.split("=");
            params.put(kv[0], kv[1]);
        }
        return params;
    }

    private static String getParamUTF8(Map<String, String> params, String param)
    {
        String encoded = params.get(param);
        return URLDecoder.decode(encoded, StandardCharsets.UTF_8);
    }

    public static void main(String[] args)
    {
        //Configure Spark
        staticFiles.location("/static/");
        port(PORT);

        _Initializer.Init();

        //Define routes
        get("/", (req, res) -> IndexGUI.display());

        get("/teachers", (req, res) -> TeacherGUI.readAll());

        //TODO make this exclusive to loggedin
        get("/teachers/id/:id_teacher", (req, res) -> TeacherGUI.readById(Long.parseLong(req.params(":id_teacher"))));

        //TODO make this exclusive to loggedin
        get("/teachers/user/:username", (req, res) -> TeacherGUI.readByUserName(req.params(":username")));

        //TODO make this exclusive to loggedin
        get("/signup", (req, res) -> SignUpGUI.display());

        //TODO make this exclusive to loggedin
        post("/signup",
             (req, res) -> {
                 HashMap<String, String> params = getParamFromReqBody(req.body());
                 return SignUpGUI.signUp(getParamUTF8(params, "firstname"),
                                         getParamUTF8(params, "lastname"),
                                         getParamUTF8(params, "username"),
                                         getParamUTF8(params, "userpwd"));
             });

        get("/stickers", (req, res) -> StickerGUI.readAll());

        get("/stickers/:id_sticker", (req, res) -> StickerGUI.readById(Long.parseLong(req.params(":id_sticker"))));
    }
}