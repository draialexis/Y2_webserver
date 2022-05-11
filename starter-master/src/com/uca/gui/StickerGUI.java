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
import java.util.NoSuchElementException;

import static com.uca.util.IDUtil.isValidId;
import static com.uca.util.StringUtil.isValidShortString;

public class StickerGUI extends _BasicGUI
{

    public static String create(String colorString, String descriptionString)
            throws IOException, TemplateException
    {
        if (!isValidShortString(colorString) || !isValidShortString(descriptionString))
        {
            infoMsg = InfoMsg.CHAMPS_NON_POSTABLES;
        }
        else
        {
            Color       color       = Color.valueOf(colorString);
            Description description = Description.valueOf(descriptionString);
            if (StickerCore.comboExists(color, description))
            {
                infoMsg = InfoMsg.COMBINAISON_EXISTE_DEJA;
            }
            else
            {
                StickerEntity sticker = new StickerEntity();
                sticker.setColor(color);
                sticker.setDescription(description);
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

        ArrayList<StickerEntity> stickers = StickerCore.readAll();
        if (stickers.isEmpty())
        {
            throw new NoSuchElementException(InfoMsg.RESSOURCE_N_EXISTE_PAS.name());
        }
        input.put("stickers", stickers);
        input.put("colors", Color.values());
        input.put("descriptions", Description.values());
        input.put("isAuthorized", isAuthorized);
        return render(template, input, new StringWriter());
    }

    public static String readById(boolean isAuthorized, long id)
            throws IOException, TemplateException, NoSuchElementException, IllegalArgumentException
    {
        if (!isValidId(id))
        {
            throw new IllegalArgumentException(InfoMsg.ID_INVALIDE.name());
        }
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("stickers/sticker.ftl");

        StickerEntity sticker = StickerCore.readById(id);

        if (sticker == null)
        {
            throw new NoSuchElementException(InfoMsg.RESSOURCE_N_EXISTE_PAS.name());
        }

        input.put("sticker", sticker);
        if (isAuthorized)
        {
            input.put("colors", Color.values());
            input.put("descriptions", Description.values());
        }
        input.put("isAuthorized", isAuthorized);
        return render(template, input, new StringWriter());
    }

    public static String update(long id, String color, String description)
            throws IOException, TemplateException
    {
        if (!isValidShortString(color) || !isValidShortString(description))
        {
            infoMsg = InfoMsg.CHAMPS_NON_POSTABLES;
        }
        else
        {
            if (!isValidId(id))
            {
                infoMsg = InfoMsg.ID_INVALIDE;
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
