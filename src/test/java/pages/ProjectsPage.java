package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.PropertyReader;

@Log4j2
public class ProjectsPage extends BasePage {
    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    private By createNewProjectButtonLocator = By.cssSelector("#createButton");
    private By createProjectFormLocator = By.cssSelector(".ReactModal__Content");
    private String projectTitleLocator = "//a[contains(@class,'defect-title')][text()='%s']";
    private By createNewTestCaseButtonLocator = By.cssSelector("#create-case-button");

    @Override
    public ProjectsPage openPage() {
        driver.get(PropertyReader.getProperty("base_url"));
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
    public void clickOnProjectTitle(String titleName){
       log.info(String.format("clicking on projects title with name: %s", titleName));
       driver.findElement(By.xpath(String.format(projectTitleLocator, titleName))).click();
    }

    @Step
    public void clickCreateNewTestCaseButton(){
        driver.findElement(createNewTestCaseButtonLocator).click();
    }
}



