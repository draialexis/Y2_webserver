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

        _Initializer.Init();

        //Define routes
        get("/", (req, res) -> IndexGUI.display());

        get("/teachers", (req, res) -> TeacherGUI.readAll());

        get("/teachers/:username", (req, res) -> TeacherGUI.readByUserName(req.params(":username")));

        get("/stickers", (req, res) -> StickerGUI.readAll());

        get("/stickers/:id_sticker", (req, res) -> StickerGUI.readById(Long.parseLong(req.params(":id_sticker"))));
    }
}