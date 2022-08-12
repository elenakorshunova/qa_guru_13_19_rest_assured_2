package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static tests.TestData.*;

public class AuthorizationPage {
    @Step("Open login page")
    public void openLoginPage() {
        open("/login");
    }

    @Step("Authorization API test")
    public Cookie authUserTest() {
        String authCookieValue = given()
                .filter(withCustomTemplates())
                .contentType("application/x-www-form-urlencoded")
                .formParam("Email", EMAIL_AUTH)
                .formParam("Password", PASSWORD_AUTH)
                .when()
                .post("/login")
                .then()
                .statusCode(302)
                .extract()
                .cookie(COOKIE_NAME);

        return new Cookie(COOKIE_NAME, authCookieValue);
    }

    @Step("Check authorization UI test")
    public void checkUserAuthorization() {
        $(".account").shouldHave(Condition.text(EMAIL_AUTH));
    }
}