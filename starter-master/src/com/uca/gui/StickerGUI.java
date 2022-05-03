package com.uca.gui;

import com.uca.core.StickerCore;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class StickerGUI extends _GenericGUI
{
    public static String readAll() throws IOException, TemplateException
    {
        Map<String, Object> input  = new HashMap<>();
        Writer              output = new StringWriter();
        input.put("stickers", StickerCore.readAll());
        Template template = configuration.getTemplate("stickers/stickers.ftl");
        return inAndOut(template, input, output);
    }

    public static String readById(long id) throws IOException, TemplateException
    {
        Map<String, Object> input  = new HashMap<>();
        Writer              output = new StringWriter();
        input.put("sticker", StickerCore.readById(id));
        Template template = configuration.getTemplate("stickers/sticker.ftl");
        return inAndOut(template, input, output);
    }
}
