package com.uca.core;

import com.uca.dao.StickerDAO;
import com.uca.entity.StickerEntity;

import java.util.ArrayList;

public class StickerCore
{
    public static ArrayList<StickerEntity> readAll() {
        return new StickerDAO().readAll();
    }
}
