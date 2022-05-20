package com.uca.core;

import com.uca.dao.StickerDAO;
import com.uca.entity.Color;
import com.uca.entity.Description;
import com.uca.entity.StickerEntity;

import java.util.ArrayList;

import static com.uca.util.IDUtil.requireValidId;
import static java.util.Objects.requireNonNull;

public class StickerCore
{
    public static StickerEntity create(StickerEntity obj)
    {
        requireNonNull(obj);
        return new StickerDAO().create(obj);
    }

    public static ArrayList<StickerEntity> readAll()
    {
        return new StickerDAO().readAll();
    }

    public static StickerEntity readById(long id)
    {
        requireValidId(id);
        return new StickerDAO().readById(id);
    }

    public static StickerEntity update(StickerEntity obj, long id)
    {
        requireNonNull(obj);
        requireValidId(id);
        return new StickerDAO().update(obj, id);
    }

    public static void deleteById(long id)
    {
        requireValidId(id);
        new StickerDAO().deleteById(id);
    }

    public static boolean comboExists(Color color, Description description)
    {
        return new StickerDAO().comboExists(color, description);
    }
}
