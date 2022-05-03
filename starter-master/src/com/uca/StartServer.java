package com.uca;

import com.uca.dao._Initializer;
import com.uca.gui.*;

import static spark.Spark.*;

public class StartServer
{
    public static final int PORT = 8081;

    public static void main(String[] args)
    {
        //Configure Spark
        staticFiles.location("/static/");
        port(PORT);

        //TODO Don't allow usernames to start with '+', '-', or a digit

        _Initializer.Init();

        //Define routes
        get("/", (req, res) -> IndexGUI.display());

        get("/teachers", (req, res) -> TeacherGUI.readAll());

        //TODO make this exclusive to loggedin
        get("/teachers/id/:id_teacher", (req, res) -> TeacherGUI.readById(Long.parseLong(req.params(":id_teacher"))));

        //TODO make this exclusive to loggedin
        get("/teachers/user/:username", (req, res) -> TeacherGUI.readByUserName(req.params(":username")));

        get("/stickers", (req, res) -> StickerGUI.readAll());

        get("/stickers/:id_sticker", (req, res) -> StickerGUI.readById(Long.parseLong(req.params(":id_sticker"))));
    }
}