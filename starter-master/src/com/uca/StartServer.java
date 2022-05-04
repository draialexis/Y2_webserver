package com.uca;

import com.uca.Login.LoginController;
import com.uca.ServepageController.ServeTeacherController;

import com.uca.dao._Initializer;
import com.uca.gui.*;


import static spark.Spark.*;

public class StartServer
{
    public static final int PORT = 8081;

    public static void main(String[] args)
    {
        /* This is an Example for you @Alexis for your login page to show how it works.

        String password = "myNewPass123";
        PassWordBasedEncryption PassBasedEnc = null;
        String saltvalue = "n7d9MPQFXxDqzT6onmong3hQt8Nyko";
        String encryptedpassword = "sA0jNGQTrAfMUiqrB++bMKTU55ThdFCl16ZZTIXwD2M=";
        System.out.println("Plain text password = " + password);
        System.out.println("Secure password = " + encryptedpassword);
        System.out.println("Salt value = " + saltvalue);
        Boolean status = PassBasedEnc.verifyUserPassword(password,encryptedpassword,saltvalue);
        if(status == true)
            System.out.println("Password Matched!!");
        else
            System.out.println("Password Mismatched");
         */

    //Configure Spark
        staticFiles.location("/static/");
        port(PORT);

        //TODO Don't allow usernames to start with '+', '-', or a digit

        _Initializer.Init();

        //Define routes
        get("/", (req, res) -> IndexGUI.display());

        get("/teachers", (req, res) -> TeacherGUI.readAll());

        get("/login", (req, res) -> LoginGui.LoginPageGUI());

        post("/login", (req, res) -> LoginController.handleLoginPost(req, res));

        //Functions well
        get("/teachers/id/:id_teacher", (req, res) -> ServeTeacherController.getATeacherById(req, res));

        //Functions well
        get("/teachers/user/:username", (req, res) -> ServeTeacherController.getATeacherByUser_name(req, res));

        get("/stickers", (req, res) -> StickerGUI.readAll());

        get("/stickers/:id_sticker", (req, res) -> StickerGUI.readById(Long.parseLong(req.params(":id_sticker"))));
    }
}