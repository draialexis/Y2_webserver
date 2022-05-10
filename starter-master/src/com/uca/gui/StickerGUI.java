package com.uca.gui;

import com.uca.core.StickerCore;
import com.uca.entity.Color;
import com.uca.entity.Description;
import com.uca.entity.StickerEntity;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.uca.util.IDUtil.isValidId;
import static com.uca.util.StringUtil.isValidString;

public class StickerGUI extends _BasicGUI
{

    public static String create(String color, String description)
            throws IOException, TemplateException
    {
        if (!isValidString(color) || !isValidString(description))
        {
            infoMsg = InfoMsg.CHAMPS_VIDES_INTERDITS;
        }
        else
        {
            ArrayList<StickerEntity> allStickers = StickerCore.readAll();
            StickerEntity            sticker     = new StickerEntity();
            sticker.setColor(Color.valueOf(color));
            sticker.setDescription(Description.valueOf(description));
            if (allStickers.contains(sticker))
            //  TODO    if(StickerCore().comboExists(sticker))
            {
                infoMsg = InfoMsg.COMBINAISON_EXISTE_DEJA;
            }
            else
            {
                infoMsg = StickerCore.create(sticker) != null ? InfoMsg.AJOUT_SUCCES : InfoMsg.AJOUT_ECHEC;
            }
        }
        // we assume that the user was only able to access this function because it was authorized
        return readAll(true);
    }

    public static String readAll(boolean isAuthorized) throws IOException, TemplateException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("stickers/stickers.ftl");

        input.put("colors", Color.values());
        input.put("descriptions", Description.values());
        input.put("stickers", StickerCore.readAll());
        input.put("isAuthorized", isAuthorized);
        return render(template, input, new StringWriter());
    }

    public static String readById(boolean isAuthorized, long id) throws IOException, TemplateException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("stickers/sticker.ftl");

        if (!isValidId(id))
        {
            infoMsg = InfoMsg.ID_INVALIDE;
        }
        else
        {
            input.put("sticker", StickerCore.readById(id));
        }
        input.put("colors", Color.values());
        input.put("descriptions", Description.values());
        input.put("stickers", StickerCore.readAll());
        input.put("isAuthorized", isAuthorized);
        return render(template, input, new StringWriter());
    }

    public static String update(long id, String color, String description)
            throws IOException, TemplateException
    {
        if (!isValidString(color) || !isValidString(description))
        {
            infoMsg = InfoMsg.CHAMPS_VIDES_INTERDITS;
        }
        else
        {
            if (!isValidId(id))
            {
                infoMsg = InfoMsg.ID_INVALIDE;
                return readAll(true);
            }
            else
            {
                StickerEntity sticker = new StickerEntity();
                sticker.setId(id);
                sticker.setColor(Color.valueOf(color));
                sticker.setDescription(Description.valueOf(description));
                infoMsg = StickerCore.update(sticker, id) != null ? InfoMsg.MODIFICATION_SUCCES
                                                                  : InfoMsg.MODIFICATION_ECHEC;
            }
        }
        // we assume that the user was only able to access this function because it was authorized
        return readById(true, id);

    }

    public static String deleteById(long id) throws TemplateException, IOException
    {
        if (!isValidId(id))
        {
            infoMsg = InfoMsg.ID_INVALIDE;
        }
        else
        {
            StickerCore.deleteById(id);
        }
        // we assume that the user was only able to access this function because it was authorized
        return readAll(true);
    }
}
