package com.uca;

import com.uca.dao._Initializer;
import com.uca.gui.*;

import static spark.Spark.*;

public class StartServer
{

    public static void main(String[] args)
    {
        //Configure Spark
        staticFiles.location("/static/");
        port(8081);

        _Initializer.Init();

        //Define routes

        get("/teachers", (req, res) -> TeacherGUI.readAll());

        get("/stickers", (req, res) -> StickerGUI.readAll());
    }
}