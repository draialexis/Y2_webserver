package com.uca.gui;

import com.uca.core.StickerCore;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;


public class StickerGUI
{
    public static String readAll() throws IOException, TemplateException
    {
        Map<String, Object> input = new HashMap<>();
        input.put("stickers", StickerCore.readAll());
        Template template = _FreeMarkerInitializer.getContext().getTemplate("stickers/stickers.ftl");
        return _UtilGUI.inAndOut(template, input, new StringWriter());
    }

    public static String readById(long id) throws IOException, TemplateException
    {
        Map<String, Object> input = new HashMap<>();
        input.put("sticker", StickerCore.readById(id));
        Template template = _FreeMarkerInitializer.getContext().getTemplate("stickers/sticker.ftl");
        return _UtilGUI.inAndOut(template, input, new StringWriter());
    }
}
