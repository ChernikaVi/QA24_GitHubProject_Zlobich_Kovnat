package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProjectsPage extends BasePage {
    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    private By createNewProjectButtonLocator = By.cssSelector("#createButton");
    private By searchForProjectsLocator = By.cssSelector("//*[@name='title']");
    private By createProjectFormLocator = By.cssSelector(".ReactModal__Content");

    @Override
    public ProjectsPage openPage() {
        driver.get("https://app.qase.io/projects");
        return this;
    }

    @Override
    public ProjectsPage isPageOpened() {
        return this;
    }

    @Step
    public void clickCreateNewProjectButton() {
        driver.findElement(createNewProjectButtonLocator).click();
    }

    @Step
    public boolean isCreateProjectButtonDisplayed() {
        return driver.findElement(createNewProjectButtonLocator).isDisplayed();
    }

    @Step
    public boolean isCreateProjectFormDisplayed() {
        return driver.findElement(createProjectFormLocator).isDisplayed();
    }
}



