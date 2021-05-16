package ru.gis.app.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CipherUtil {
    public static String hashData(byte[] dataToEncrypt) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] result = messageDigest.digest(dataToEncrypt);
        int iterations = (int) ((Math.random() * 6) + 5);
        for (int i = 0; i < iterations - 1; i++) {
            result = messageDigest.digest(dataToEncrypt);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (byte elem : result) {
            String correctSym = Integer.toHexString(0xff & elem);
            correctSym = (correctSym.length() == 1) ? "0" + correctSym : correctSym;
            stringBuilder.append(correctSym);
        }
        stringBuilder.append(iterations);
        return stringBuilder.toString();
    }

    public static String hashDataSetIterations(byte[] dataToEncrypt, int iterationsCount) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] result = messageDigest.digest(dataToEncrypt);
        for (int i = 0; i < iterationsCount - 1; i++) {
            result = messageDigest.digest(dataToEncrypt);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (byte elem : result) {
            String correctSym = Integer.toHexString(0xff & elem);
            correctSym = (correctSym.length() == 1) ? "0" + correctSym : correctSym;
            stringBuilder.append(correctSym);
        }
        stringBuilder.append(iterationsCount);
        return stringBuilder.toString();
    }
}
