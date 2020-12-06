package com.company.chernoivan;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileWriter;
import java.util.Base64;

public class AES_ECB {


    public static void encrypt(String input, String key) {
        byte[] crypted;
        try {
            FileWriter out = new FileWriter("out.txt");
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            crypted = cipher.doFinal(input.getBytes());
            java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();
            out.write(encoder.encodeToString(crypted));
            out.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public static void decrypt(String input, String key) {
        byte[] output;
        try {
            FileWriter out = new FileWriter("out.txt");

            java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            output = cipher.doFinal(decoder.decode(input));
            String finalString = new String(output);
            out.write(finalString);
            out.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
