package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.PropertyReader;

public class ProjectsRepositoryPage extends BasePage{
    public ProjectsRepositoryPage(WebDriver driver) {
        super(driver);
    }

    private By createSuiteButtonLocator = By.cssSelector("#create-suite-button");
    private By createCaseButtonLocator = By.cssSelector("#create-case-button");
    private By settingsButtonLocator = By.xpath("//*[@aria-label='Settings']");


    @Override
    public ProjectsRepositoryPage openPage() {
        driver.get(PropertyReader.getProperty("base_url"));
        return this;
    }

    @Override
    public ProjectsRepositoryPage isPageOpened() {
        waitForElementClickable(settingsButtonLocator);
        return this;
    }

    @Step
    public void clickSettingsButton() {
        driver.findElement(settingsButtonLocator).click();
    }
}
