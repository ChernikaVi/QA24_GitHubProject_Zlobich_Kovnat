package tests;

import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest{
    private String expectedErrorText = "These credentials do not match our records.";
    private String expectedErrorPasswordText = "Security notice: The password entered has been found in a public data leak. " +
            "Please reset your password to ensure the safety of your account";


    @Test(groups = {"smoke"})
    @Description("Тестирование кнопки формы Log In")
    @Link(name = "Log In Page", url = "https://app.qase.io/login")
    @Severity(SeverityLevel.CRITICAL)
    public void positiveLogInTest() {
        loginPage.openPage()
                .isPageOpened()
                .logIn(USERNAME, PASSWORD);
        Assert.assertTrue(loginPage.isLayoutDisplayed());
    }

    @DataProvider
    public Object[][] negativeLoginTestData() {
        return new Object[][]{
                {"", PASSWORD, "This field is required"},
                {USERNAME, "", "This field is required"}
        };
    }

    @Test(groups = {"smoke"})
    @Description("Invalid username test")
    @Severity(SeverityLevel.CRITICAL)
    public void negativeLoginTestWrongUsername() {
        loginPage.openPage()
                .isPageOpened()
                .logIn(ERROR_USERNAME, PASSWORD);
        Assert.assertEquals(loginPage.getErrorText(), expectedErrorText);
    }

    @Test(groups = {"smoke"})
    @Description("Invalid password test")
    @Severity(SeverityLevel.CRITICAL)
    public void negativeLoginTestWrongPassword() {
        loginPage.openPage()
                .isPageOpened()
                .logIn(USERNAME, ERROR_PASSWORD);
        Assert.assertEquals(loginPage.getErrorPasswordText(), expectedErrorPasswordText);
    }
}

