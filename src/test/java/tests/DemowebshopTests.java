package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;
import static tests.TestData.*;

public class DemowebshopTests extends TestBase {

    @Test
    @DisplayName("Registration form API with UI test")
    void registerNewUser() {

        given()
                .contentType("application/x-www-form-urlencoded")
                .cookie("ARRAffinity=92eb765899e80d8de4d490df907547e5cb10de899e8b754a4d5fa1a7122fad69")
                .formParam("FirstName", FIRST_NAME)
                .formParam("LastName", LAST_NAME)
                .formParam("Email", EMAIL)
                .formParam("Password", PASSWORD)
                .formParam("ConfirmPassword", PASSWORD)
                .when()
                .post("/register")
                .then()
                .statusCode(302)
                .extract().cookie(COOKIE_NAME);

        open("/registerresult/1");
        $(".result").shouldHave(Condition.text("Your registration completed"));
    }


    @Test
    @DisplayName("Update user profile API with UI test")
    void updateUserProfile() {

        Cookie authCookie = authorizationPage.authUserTest();

        authorizationPage.openLoginPage();
        WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
        open("");
        authorizationPage.checkUserAuthorization();
        open("/customer/info");
        $("#FirstName").setValue(FIRST_NAME);
        $("#LastName").setValue(LAST_NAME);
        $("[name='save-info-button']").click();
        $("#FirstName").shouldHave(Condition.value(FIRST_NAME));
    }
}