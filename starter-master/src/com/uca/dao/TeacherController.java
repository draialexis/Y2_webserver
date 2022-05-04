package com.uca.dao;

import com.uca.entity.TeacherEntity;
import com.uca.core.TeacherCore;
import com.uca.dao.PassWordBasedEncryption;

public class TeacherController {

    public static boolean authenticate(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return false;
        }
        TeacherEntity user = TeacherCore.readByUserName(username);
        if (user == null) {
            return false;
        }
        return PassWordBasedEncryption.verifyUserPassword(password, user.getUserSPwd(), user.getUserHPwd());
    }


}