package com.uca.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

import static com.uca.util.StringUtil.required;
import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import static spark.Spark.halt;

public class PropertiesReader
{
    private static final String      FILEPATH = "config/config.properties";
    private              InputStream inputStream;

    public String getProperty(String propertyName) throws IOException
    {
        String property = null;
        required(propertyName);
        try
        {
            Properties properties = new Properties();

            this.inputStream = this.getClass().getClassLoader().getResourceAsStream(FILEPATH);

            if (this.inputStream == null)
            {
                throw new FileNotFoundException(String.format("%s : no such file was found...", FILEPATH));
            }
            else
            {
                properties.load(this.inputStream);
                property = properties.getProperty(propertyName);
                if (property == null)
                {
                    throw new NoSuchElementException(String.format("could not find %s in %s", propertyName, FILEPATH));
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            halt(HTTP_INTERNAL_ERROR);
        } finally
        {
            if (this.inputStream != null)
            {
                this.inputStream.close();
            }
        }
        return property;
    }
}
