package com.company.chernoivan;

import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        SHA sha = new SHA();

        String fileString = new String();

        Scanner in = new Scanner(System.in);
        System.out.print("Введите ваш ключ пользователя: ");
        String userPass = in.nextLine();
        String sessionKey = sha.PassSHA(userPass);
        System.out.print("Введите каталог и названия файла для шифрования: ");
        String data = in.nextLine();

        try(FileReader reader = new FileReader(data))
        {
            int c;
            while((c=reader.read())!=-1){
                fileString += (char)c;
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        System.out.println("Входной текст: " + fileString);

        System.out.println("Выберите тип шифрования/дешифрования: ");
        System.out.println("1. Шифрование AES ECB ");
        System.out.println("2. Дешифрование AES ECB ");
        System.out.println("3. Шифрование AES CBC ");
        System.out.println("4. Дешифрование AES CBC ");
        System.out.println("5. Шифрование AES CFB ");
        System.out.println("6. Дешифрование AES CFB ");

        int cryptoType = Integer.parseInt(in.nextLine());


        switch(cryptoType) {
            case  1 :
                AES_ECB.encrypt(fileString, sessionKey);
                break;
            case 2:
                AES_ECB.decrypt(fileString, sessionKey);
                break;
            case 3:
                AES_CBC.encrypt(fileString, sessionKey);
                break;
            case 4:
                AES_CBC.decrypt(fileString, sessionKey);
                break;
            case 5:
                AES_CFB.encrypt(fileString, sessionKey);
                break;
            case 6:
                AES_CFB.decrypt(fileString, sessionKey);
                break;
        }
    }
}
