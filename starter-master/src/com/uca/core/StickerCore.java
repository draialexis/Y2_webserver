package com.uca.core;

import com.uca.dao.StickerDAO;
import com.uca.entity.StickerEntity;
import com.uca.util.IDUtil;

import java.util.ArrayList;
import java.util.Objects;

public class StickerCore
{
    public static StickerEntity create(StickerEntity obj)
    {
        Objects.requireNonNull(obj);
        return new StickerDAO().create(obj);
    }

    public static ArrayList<StickerEntity> readAll()
    {
        return new StickerDAO().readAll();
    }

    public static StickerEntity readById(long id)
    {
        IDUtil.requireValid(id);
        return new StickerDAO().readById(id);
    }

    public static StickerEntity update(StickerEntity obj, long id)
    {
        Objects.requireNonNull(obj);
        IDUtil.requireValid(id);
        return new StickerDAO().update(obj, id);
    }

    public static void deleteById(long id)
    {
        IDUtil.requireValid(id);
        new StickerDAO().deleteById(id);
    }
}
