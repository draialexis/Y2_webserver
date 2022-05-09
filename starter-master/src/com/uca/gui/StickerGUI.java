package com.uca.gui;

import com.uca.core.StickerCore;
import com.uca.entity.Color;
import com.uca.entity.Description;
import com.uca.entity.StickerEntity;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class StickerGUI extends _BasicGUI
{

    public static String create(String color, String description)
            throws IOException, TemplateException
    {
        StickerEntity sticker = new StickerEntity();
        sticker.setColor(Color.valueOf(color));
        sticker.setDescription(Description.valueOf(description));
        if (StickerCore.create(sticker) != null)
        {
            infoMsg = "ajout : succ&egrave;s";
        }
        // we assume that the user was only able to access this function because it was authorized
        return readAll(true);
    }
    //todo prevent pre-existing combinations? prevent it in the model too

    //todo (optional) prevent using same color? prevent it in the model too

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

        input.put("colors", Color.values());
        input.put("descriptions", Description.values());
        input.put("stickers", StickerCore.readAll());
        input.put("sticker", StickerCore.readById(id));
        input.put("isAuthorized", isAuthorized);
        return render(template, input, new StringWriter());
    }

    public static String update(long id, String color, String description)
            throws IOException, TemplateException
    {
        StickerEntity sticker = new StickerEntity();
        sticker.setId(id);
        sticker.setColor(Color.valueOf(color));
        sticker.setDescription(Description.valueOf(description));
        if (StickerCore.update(sticker, sticker.getId()) != null)
        {
            infoMsg = "modification : succ&egrave;s";
        }
        // we assume that the user was only able to access this function because it was authorized
        return readById(true, id);
    }

    public static String deleteById(long id) throws TemplateException, IOException
    {
        StickerCore.deleteById(id);
        infoMsg = "suppression : succ&egrave;s";
        // we assume that the user was only able to access this function because it was authorized
        return readAll(true);
    }
}
