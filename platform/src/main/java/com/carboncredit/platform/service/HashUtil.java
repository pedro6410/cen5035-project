package com.carboncredit.platform.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    public static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes());


            StringBuilder hex = new StringBuilder();
            for (byte b : hash) {
                String hexChar = Integer.toHexString(0xff & b);
                if (hexChar.length() == 1) hex.append('0');
                hex.append(hexChar);
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not supported", e);
        }
    }
}
