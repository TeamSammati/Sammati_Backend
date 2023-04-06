package site.sammati.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class EncyDecryService {
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static String encryptData(String inputStr, Integer shiftKey) {
        inputStr = inputStr.toLowerCase();

        String encryptStr = "";

        for (int i = 0; i < inputStr.length(); i++)
        {
            int pos = ALPHABET.indexOf(inputStr.charAt(i));

            int encryptPos = (shiftKey + pos) % 26;
            char encryptChar = ALPHABET.charAt(encryptPos);

            encryptStr += encryptChar;
        }
        return encryptStr;
    }

    public static String decryptData(String inputStr, int shiftKey) {


        if(inputStr==null)
            return "-99";

        inputStr = inputStr.toLowerCase();

        String decryptStr = "";

        for (int i = 0; i < inputStr.length(); i++)
        {
            int pos = ALPHABET.indexOf(inputStr.charAt(i));

            int decryptPos = (pos - shiftKey) % 26;

            if (decryptPos < 0){
                decryptPos = ALPHABET.length() + decryptPos;
            }
            char decryptChar = ALPHABET.charAt(decryptPos);

            decryptStr += decryptChar;
        }
        return decryptStr;
    }
}
