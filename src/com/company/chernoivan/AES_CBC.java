package com.company.chernoivan;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileWriter;

public class AES_CBC {

    static FileWriter out = null;

    public static void encrypt(String input, String key) {
        byte[] crypted = null;
        try {
            out = new FileWriter("out.txt", false);
            IvParameterSpec ivSpec = new IvParameterSpec(javax.xml.bind.DatatypeConverter.parseHexBinary(key));

            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey, ivSpec);
            crypted = cipher.doFinal(input.getBytes());
            java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();
            out.write(encoder.encodeToString(crypted));
            out.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public static void decrypt(String input, String key) {
        byte[] output = null;
        try {
            out = new FileWriter("out.txt", false);
            IvParameterSpec ivSpec = new IvParameterSpec(javax.xml.bind.DatatypeConverter.parseHexBinary(key));
            java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey, ivSpec);
            output = cipher.doFinal(decoder.decode(input));
            String finalString = new String(output);
            out.write(finalString);
            out.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
