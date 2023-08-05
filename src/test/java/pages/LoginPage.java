package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class LoginPage extends BasePage {
    public LoginPage (WebDriver driver) {
        super(driver);
    }

    private By loginFieldLocator = By.xpath("//input[@name='email']");
    private By passwordFieldLocator = By.xpath("//input[@name='password']");
    private By signInButtonLocator = By.cssSelector("[type=submit]");


    private By layoutLocator = By.cssSelector("#layout");
    private By errorMessageTextLocator = By.cssSelector(".VV3w3Z");
    private By errorMessageTextPasswordLocator = By.cssSelector(".ic9QAx");

    @Override
    public LoginPage openPage() {
        driver.get("https://app.qase.io/login");
        return this;
    }

    @Override
    public LoginPage isPageOpened() {
        return this;
    }

    @Step
    public void clickSignInBut(){
        driver.findElement(signInButtonLocator).click();
    }
    @Step
    public void setUsernameValue(String email){
        driver.findElement(loginFieldLocator).sendKeys(email);
    }

    @Step
    public void setPasswordValue(String password){
        driver.findElement(passwordFieldLocator).sendKeys(password);
    }

    @Step
    public void logIn(String email, String password){
        log.info(String.format("logging in with email= %s and password = %s", email, password));
        this.setUsernameValue(email);
        this.setPasswordValue(password);
        this.clickSignInBut();
    }

    @Step
    public String getErrorText() {
        return driver.findElement(this.errorMessageTextLocator).getText();
    }

    @Step
    public String getErrorText2() {
        return driver.findElement(this.errorMessageTextPasswordLocator).getText();
    }
}
