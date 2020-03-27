package main.java.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ProjectRandomUtils {

    public static String generateCorrectRandomEmail(int userNamePartLength) {
        String allowedChars = "abcdefghijklmnopqrstuvwxyz";
        String emailPrefix = RandomStringUtils.random(userNamePartLength, allowedChars);
        return emailPrefix + "@email.ru";
    }

    public static String generateCorrectRandomPassword() {
        String resultString = RandomStringUtils.randomAlphabetic(4) + RandomStringUtils.randomNumeric(4);
        return mixString(resultString);
    }

    public static String generateRandomPhoneNumber() {
        int num1, num2, num3;
        Random generator = new Random();
        num1 = generator.nextInt(788) + 100;
        num2 = generator.nextInt(643) + 100;
        num3 = generator.nextInt(8999) + 1000;
        return + num1 + "-" + num2 + "-" + num3;
    }

    public static String mixString(String text) {
        List<String> stringList = Arrays.asList(text.split("(?=[\\w\\W])"));
        Collections.shuffle(stringList);
        StringBuilder stringBuilder = new StringBuilder();
        stringList.stream().forEach(stringBuilder::append);
        return stringBuilder.toString();
    }
}
