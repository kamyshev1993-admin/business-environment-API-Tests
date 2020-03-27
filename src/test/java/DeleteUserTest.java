package test.java;

import com.jayway.restassured.specification.ResponseSpecification;
import io.qameta.allure.Description;
import main.java.model.ExpectedResponseFactory;
import main.java.model.RequestBuilderFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class DeleteUserTest extends Base {

    @Test(groups = {"with creating random user before", "with checking random user after"})
    @Description("Удаление существующего пользователя")
    public void deleteExistUserOkTest1() {
        execute(randomUser.getUserName(), ExpectedResponseFactory.getSuccessResponse(randomUser.getUserName()));
    }

    @Test
    @Description("Попытка удаления несуществующего пользователя")
    public void tryDeleteNotExistUserErrorTest2() {
        execute(RandomStringUtils.randomAlphabetic(15), ExpectedResponseFactory.getNotFoundResponse());
    }

    @Test
    @Description("Попытка удаления пользователя без указания username")
    public void tryDeleteUserWithoutTypingUserNameErrorTest3() {
        execute("", ExpectedResponseFactory.getMethodNotAllowed());
    }

    @Test(groups = "with creating random user before")
    @Description("Попытка удаления пользователя методом post")
    public void tryDeleteUserByMethodPostErrorTest4() {
        given().relaxedHTTPSValidation()
                .spec(RequestBuilderFactory.getBaseRequestSpecification())
                .when()
                .post("/" + randomUser.getUserName())
                .then()
                .spec(ExpectedResponseFactory.getMethodNotAllowed());
    }

    private void execute(String userName, ResponseSpecification responseSpecification) {
        given().relaxedHTTPSValidation()
                .spec(RequestBuilderFactory.getBaseRequestSpecification())
                .when()
                .delete("/" + userName)
                .then()
                .spec(responseSpecification);
    }
}
