package com.uca.gui;

import com.uca.core.StickerCore;
import com.uca.entity.Color;
import com.uca.entity.Description;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StickerGUI
{
    public static String readAll() throws IOException, TemplateException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("stickers/stickers.ftl");

        input.put("stickers", StickerCore.readAll());
        return _UtilGUI.render(template, input, new StringWriter());
    }

    public static String readById(long id) throws IOException, TemplateException
    {
        Map<String, Object> input    = new HashMap<>();
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("stickers/sticker.ftl");

        input.put("sticker", StickerCore.readById(id));
        return _UtilGUI.render(template, input, new StringWriter());
    }
    public static String dispalyCreatePage()
            throws IOException, TemplateException{
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("stickers/createSticker.ftl");
        Map<String, Object> input    = new HashMap<>();
        Color color[] =  Color.values();
        Description description[]= Description.values();
        input.put("color", color);
        input.put("description", description);
        return _UtilGUI.render(template, input, new StringWriter());
    }

    public static String dispalyModifPage(long Sticker_id)
            throws IOException, TemplateException{
        Template            template = _FreeMarkerInitializer.getContext().getTemplate("stickers/modifieSticker.ftl");
        Map<String, Object> input    = new HashMap<>();
        Color color[] =  Color.values();
        Description description[]= Description.values();
        input.put("color", color);
        input.put("description", description);
        input.put("path_id", Sticker_id);
        return _UtilGUI.render(template, input, new StringWriter());
    }

}
