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
            status = "ajout : succ&egrave;s";
        }
        return readAll();
    }

    public static String readAll() throws IOException, TemplateException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("stickers/stickers.ftl");

        input.put("stickers", StickerCore.readAll());
        return render(template, input, new StringWriter());
    }

    public static String readById(long id) throws IOException, TemplateException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("stickers/sticker.ftl");

        input.put("sticker", StickerCore.readById(id));
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
            status = "modification : succ&egrave;s";
        }
        return readById(id);
    }

    public static String deleteById(long id) throws TemplateException, IOException
    {
        StickerCore.deleteById(id);
        status = "suppression : succ&egrave;s";
        return readAll();
    }
}
