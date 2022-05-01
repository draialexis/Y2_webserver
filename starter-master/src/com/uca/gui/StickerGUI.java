package com.uca.gui;

import com.uca.core.StickerCore;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class StickerGUI
{
    public static String readAll() throws IOException, TemplateException
    {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("stickers", StickerCore.readAll());

        Writer   output   = new StringWriter();
        Template template = configuration.getTemplate("stickers/stickers.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String readById(Long id) throws IOException, TemplateException
    {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("sticker", StickerCore.readById(id));

        Writer   output   = new StringWriter();
        Template template = configuration.getTemplate("stickers/sticker.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }
}
