package tests;

import com.github.javafaker.Faker;

public class TestData {
    static Faker faker = new Faker();
    public final static String FIRST_NAME = faker.address().firstName(),
            LAST_NAME = faker.address().lastName(),
            EMAIL = faker.internet().emailAddress(),
            PASSWORD = faker.internet().password(),
            COOKIE_NAME = "NOPCOMMERCE.AUTH",
            EMAIL_AUTH = "test123@test.ru",
            PASSWORD_AUTH = "Qwerty123";
}