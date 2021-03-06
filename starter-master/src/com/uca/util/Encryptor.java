package com.uca.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

import static com.uca.util.StringUtil.isValidShortString;
import static com.uca.util.StringUtil.requiredShortString;

public class Encryptor
{
    private static final Random RANDOM       = new SecureRandom();
    private static final String CHARS        = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int    CHARS_LENGTH = CHARS.length();
    private static final int    ITERATIONS   = 10000;
    private static final int    KEY_LENGTH   = 256;

    public static String generateSalt(int size)
    {
        if (size < 1)
        {
            throw new IllegalArgumentException("salt must have a strictly positive length");
        }
        StringBuilder salt = new StringBuilder(size);
        for (int i = 0; i < size; i++)
        {
            salt.append(CHARS.charAt(RANDOM.nextInt(CHARS_LENGTH)));
        }
        return requiredShortString(String.valueOf(salt));
    }

    public static boolean verifyUserPassword(String providedPassword, String securedPassword, String salt)
    {
        isValidShortString(providedPassword);
        isValidShortString(securedPassword);
        isValidShortString(salt);
        return securedPassword.equals(generateSecurePassword(providedPassword, salt));
    }

    public static String generateSecurePassword(String password, String salt)
    {
        isValidShortString(password);
        isValidShortString(salt);
        return Base64.getEncoder().encodeToString(generateHash(password.toCharArray(), salt.getBytes()));
    }

    public static byte[] generateHash(char[] password, byte[] salt)
    {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try
        {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e)
        {
            throw new AssertionError("hashing error : " + e.getMessage(), e);
        } finally
        {
            spec.clearPassword();
        }
    }
}

