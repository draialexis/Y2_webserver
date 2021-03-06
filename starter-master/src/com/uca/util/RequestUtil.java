package com.uca.util;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static com.uca.util.StringUtil.requiredString;
import static java.util.Objects.requireNonNull;

// thanks to https://stackoverflow.com/questions/29312048/how-to-get-data-from-form-with-spark-java
public class RequestUtil
{
    public static HashMap<String, String> getParamFromReqBody(String body)
    {
        requiredString(body);
        HashMap<String, String> params = new HashMap<>();
        for (String s : body.split("&"))
        {
            String[] kv = s.split("=");
            params.put(kv[0], kv[1]);
        }
        return params;
    }

    public static String getParamUTF8(Map<String, String> params, String param)
    {
        requireNonNull(params);
        requiredString(param);
        String encoded = params.get(param);
        return URLDecoder.decode(encoded, StandardCharsets.UTF_8);
    }
}
