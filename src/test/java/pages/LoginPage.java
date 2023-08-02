package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class LoginPage extends BasePage{
    public LoginPage (WebDriver driver) {
        super(driver);
    }

    private String loginFieldId = "//input[@name='email']";
    private String passwordFieldId = "//input[@name='password']";
    private By signInButtonLocator = By.cssSelector("[type=submit]");

    private By layoutLocator = By.cssSelector("#layout");
    private By errorMessageTextLocator = By.cssSelector(".ic9QAx");
    private By errorMessageTextPasswordLocator = By.cssSelector(".ic9QAx");


    @Override
    public LoginPage openPage() {
        driver.get("https://app.qase.io/login");
        return this;
    }

    @Override
    public LoginPage isPageOpened() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButtonLocator));
        return this;
    }

    @Step
    public void setUsernameValue(String username){
        driver.findElement(By.xpath(loginFieldId)).sendKeys(username);
    }

    @Step
    public void setPasswordValue(String password){
        driver.findElement(By.xpath(passwordFieldId)).sendKeys(password);
    }

    @Step
    public void clickSignInBut(){
        driver.findElement(signInButtonLocator).click();
    }

    @Step
    public void logIn(String email, String password){
        log.info(String.format("logging in with email= %s and password = %s", email, password));
        this.setUsernameValue(email);
        this.setPasswordValue(password);
        this.clickSignInBut();
    }

    @Step
    public boolean isLayoutDisplayed() {
        return driver.findElement(layoutLocator).isDisplayed();
    }

    @Step
    public String getErrorText() {
        return driver.findElement(this.errorMessageTextLocator).getText();
    }

    @Step
    public String getErrorPasswordText() {
        return driver.findElement(this.errorMessageTextPasswordLocator).getText();
    }
}
