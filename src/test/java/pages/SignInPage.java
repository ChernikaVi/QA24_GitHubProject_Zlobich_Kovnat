package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class SignInPage extends BasePage {
    public SignInPage(WebDriver driver) {
        super(driver);
    }

    private By signInLinkLocator = By.xpath("//a[@href='/login']");
    private By loginFieldId = By.cssSelector("#login_field");

    @Override
    public SignInPage openPage() {
        driver.get("https://github.com");
        return this;
    }

    @Override
    public SignInPage isPageOpened() {
        wait.until(ExpectedConditions.elementToBeClickable(signInLinkLocator));
        return this;
    }

    @Step
    public void clickSignInButton() {
        driver.findElement(signInLinkLocator).click();
    }

    @Step
    public boolean isLoginFieldDisplayed() {
        return driver.findElement(loginFieldId).isDisplayed();
    }
}
