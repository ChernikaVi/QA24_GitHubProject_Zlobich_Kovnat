package pages;

import io.qameta.allure.Step;
import models.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.TestDataGenerator;

public class ProjectsPage extends BasePage {
    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    private By createNewProjectButtonLocator = By.cssSelector("#createButton");
    private By createProjectFormLocator = By.cssSelector(".ReactModal__Content");
    private By projectTitleLocator = By.xpath("//a[contains(@class,'defect-title')]");
    private By createNewTestCaseButtonLocator = By.cssSelector("#create-case-button");

    @Override
    public ProjectsPage openPage() {
        driver.get("https://app.qase.io");
        return this;
    }

    @Override
    public ProjectsPage isPageOpened() {
        wait.until(ExpectedConditions.elementToBeClickable(createNewProjectButtonLocator));
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

    @Step
    public void clickOnProjectTitle(){
        driver.findElement(projectTitleLocator).click();
    }

    @Step
    public void clickCreateNewTestCaseButton(){
        driver.findElement(createNewTestCaseButtonLocator).click();
    }
}



