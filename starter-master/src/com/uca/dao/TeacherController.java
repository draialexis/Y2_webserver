package com.uca.dao;

import com.uca.entity.TeacherEntity;
import com.uca.core.TeacherCore;

public class TeacherController {

    public static boolean authenticate(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) { // tried to log in without putting in the password
            return false;
        }
        TeacherEntity user = TeacherCore.readByUserName(username);
        if (user == null) { // means the user doesn't exist
            return false;
        }
        return PassWordBasedEncryption.verifyUserPassword(password, user.getUserHPwd(), user.getUserSPwd()); // verifies if the password
    }


}