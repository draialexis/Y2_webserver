package com.uca.core;

import com.uca.dao.StickerDAO;
import com.uca.entity.StickerEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class StickerCore
{
    public static ArrayList<StickerEntity> readAll()
    {
        return new StickerDAO().readAll();
    }

    public static StickerEntity readById(long id)
    {
        return new StickerDAO().readById(id);
    }
    public static StickerEntity create(StickerEntity obj) {return new StickerDAO().create(obj);}
    public static long findLastId() throws SQLException {return new StickerDAO().findLastId();}
    public static StickerEntity update(StickerEntity obj, long id) {return new StickerDAO().update(obj, id);}
    public static void deleteById(long id) {new StickerDAO().deleteById(id);}
}
