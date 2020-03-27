package main.java.model.user;

import main.java.utils.ProjectRandomUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class UserFactory {

    public static User getOptionCorrectUser() {
        return new User().setId(RandomUtils.nextInt(1, 1000))
                .setUserName(RandomStringUtils.randomAlphabetic(15, 20))
                .setFirstName(RandomUtils.nextBoolean() ? RandomStringUtils.randomAlphabetic(15, 20) : "")
                .setLastName(RandomUtils.nextBoolean() ? RandomStringUtils.randomAlphabetic(15, 20) : "")
                .setEmail(RandomUtils.nextBoolean() ? ProjectRandomUtils.generateCorrectRandomEmail(5) : "")
                .setPassword(RandomUtils.nextBoolean() ? ProjectRandomUtils.generateCorrectRandomPassword() : "")
                .setPhone(RandomUtils.nextBoolean() ? ProjectRandomUtils.generateRandomPhoneNumber() : "")
                .setUserStatus(RandomUtils.nextInt(0,5));
    }

    public static User getExistUser() {
        return new User().setId(13)
                .setUserName("kamyshev1993")
                .setFirstName("andrey")
                .setLastName("kamyshev")
                .setEmail("kamyshev@mail.ru")
                .setPassword("qw21")
                .setPhone("89517890722")
                .setUserStatus(2);
    }
}
