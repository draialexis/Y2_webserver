package com.uca.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

import static com.uca.util.DateUtil.MS_IN_HOUR;

// inspired by https://developer.okta.com/blog/2018/10/31/jwts-with-java
// and artheriom    ** mouth noises / bad audio -- warning ** (https://www.youtube.com/channel/UC2UoE_uuP7MiiwvaWbrxx0A)
public class JWTLoginUtil
{
    private static final PropertiesReader propReader = new PropertiesReader();

    public static String makeToken(String subject) throws IOException
    {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long               MilliNow           = System.currentTimeMillis();
        Date               now                = new Date(MilliNow);
        Date               exp                = new Date(MilliNow + MS_IN_HOUR);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(propReader.getProperty("encoded-key"));
        Key    signingKey        = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(UUID.randomUUID().toString())
                                 .setIssuedAt(now)
                                 .setSubject(subject)
                                 .setExpiration(exp)
                                 .signWith(signingKey);

        return builder.compact();
    }

    public static Claims checkToken(String jwt) throws IOException
    {
        return Jwts.parserBuilder()
                   .setSigningKey(DatatypeConverter.parseBase64Binary(propReader.getProperty("encoded-key")))
                   .build()
                   .parseClaimsJws(jwt).getBody();
    }
}
