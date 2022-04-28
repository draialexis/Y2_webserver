package com.uca;

import com.uca.dao._Initializer;
import com.uca.gui.*;

import static spark.Spark.*;

/* URIs
 *
 *
 *
 */

public class StartServer
{

    public static void main(String[] args)
    {
        //Configure Spark
        staticFiles.location("/static/");
        port(8081);

        _Initializer.Init();

        //Defining our routes

        get("/teachers", (req, res) -> {
            return TeacherGUI.getAllTeachers();
        });

        get("/stickers", (req, res) -> {
            return StickerGUI.getAllStickers();
        });
    }
}