package test.java;

import com.jayway.restassured.specification.ResponseSpecification;
import io.qameta.allure.Description;
import main.java.model.ExpectedResponseFactory;
import main.java.model.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class GetUserTest extends Base{

    @Test
    @Description("Получение существующего пользователя")
    public void getExistUserOkTest1() {
        getCorrectUser(existUser);
    }

    @Test
    @Description("Попытка получение несуществующего пользователя")
    public void tryGetNotExistUserErrorTest2() {
        execute(RandomStringUtils.randomAlphabetic(15), ExpectedResponseFactory.getNotFoundResponseWithBody());
    }

    @Test
    @Description("Попытка получение пользователя без указания username")
    public void tryGetUserWithoutTypingUserNameErrorTest3() {
        execute("", ExpectedResponseFactory.getMethodNotAllowed());
    }

    @Test
    @Description("Попытка получение пользователя методом post")
    public void tryGetUseByMethodPostErrorTest4() {
        given().relaxedHTTPSValidation()
                .spec(RequestBuilderFactory.getBaseRequestSpecification())
                .when()
                .post("/")
                .then()
                .spec(ExpectedResponseFactory.getMethodNotAllowed());
    }

    private void execute(String userName, ResponseSpecification responseSpecification) {
        given().relaxedHTTPSValidation()
                .spec(RequestBuilderFactory.getBaseRequestSpecification())
                .when()
                .get("/" + userName)
                .then()
                .spec(responseSpecification);
    }
}
