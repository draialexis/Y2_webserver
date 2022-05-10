package com.uca.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader
{
    private static final String      FILEPATH = "config/config.properties";
    private              InputStream inputStream;

    public String getEncodedKey() throws IOException
    {
        String key = null;
        try
        {
            Properties properties = new Properties();

            inputStream = getClass().getClassLoader().getResourceAsStream(FILEPATH);

            if (inputStream != null)
            {
                properties.load(inputStream);
            }
            else
            {
                throw new FileNotFoundException(String.format("%s : no such file was found...", FILEPATH));
            }

            key = properties.getProperty("encoded-key");

        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (inputStream != null)
            {
                inputStream.close();
            }
        }
        return key;
    }
}
