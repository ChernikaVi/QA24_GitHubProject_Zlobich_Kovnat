package pages;

import elements.DataPlaceholder;
import elements.Input;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Suite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Log4j2
public class CreateSuitePage extends BasePage {

    private By suiteTitle = By.cssSelector("#title");
    private By suiteDescription = By.cssSelector("#description");
    private By suitePreconditions = By.cssSelector("#preconditions");
    private By createNewSuiteButtonLocator = By.xpath("//*[@type='submit']");
    private By editSuiteIconLocator = By.cssSelector(".fa-pencil");
    private By deleteSuiteIconLocator = By.cssSelector(".fa-trash");
    private By saveEditedSuiteButtonLocator = By.xpath("//*[text()='Save']");
    private By deleteSuiteButtonLocator = By.xpath("//*[text()='Delete']");
    private By allSuites = By.cssSelector("#suitecases-container");


    private By createNewProjectButtonLocator = By.cssSelector("#createButton");
    private By projectTitleLocator = By.xpath("//a[contains(@class,'defect-title')][text()='%s']");
    private By createSuiteButtonLocator = By.cssSelector("#create-suite-button");
    private By successfullyCreatedSuiteMessage = By.xpath("//*[text()='Suite was successfully created.']");
    private By editedTitle = By.xpath("//*[@data-suite-body-id]");
    private By successfullyDeletedMessageText = By.xpath("//*[text()='Suite was successfully deleted.']");
    private By badgeLocator = By.cssSelector("#HW_badge");



    public CreateSuitePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CreateSuitePage openPage() {
        driver.get("https://app.qase.io");
        return this;
    }

    @Override
    public CreateSuitePage isPageOpened() {
        waitForElementClickable(createSuiteButtonLocator);
        return this;
    }

    @Step
    public void clickCreateSuiteButton() {
        driver.findElement(createSuiteButtonLocator).click();
    }

    @Step("Filling out suite")
    public void fillingOutSuiteForm(Suite suite) {
        log.info(String.format("Filling out suite form = %s", suite));
        new Input(driver, "Suite name").setInputValue(suite.getSuiteTitle());
        new DataPlaceholder(driver, "Description").setDataPlaceholderValue(suite.getSuiteDescription());
        new DataPlaceholder(driver, "Preconditions").setDataPlaceholderValue(suite.getPreconditions());
    }

    @Step
    public void clickCreateNewSuiteButton() {
        driver.findElement(createNewSuiteButtonLocator).click();
    }

    @Step
    public boolean getSuccessfullyCreatedSuiteMessageIsDisplayed() {
        return driver.findElement(successfullyCreatedSuiteMessage).isDisplayed();
    }

    @Step
    public void clickEditSuiteButtonIcon() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(badgeLocator));
        driver.findElement(editSuiteIconLocator).click();
    }

    @Step
    public void clickSaveChangesSuiteButton() {
        driver.findElement(saveEditedSuiteButtonLocator).click();
    }

    @Step
    public boolean editedTitleIsDisplayed() {
        return driver.findElement(editedTitle).isDisplayed();
    }

    @Step
    public void clickDeleteSuiteButtonIcon() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(badgeLocator));
        driver.findElement(deleteSuiteIconLocator).click();
    }

    @Step
    public void clickDeleteSuiteButton() {
        driver.findElement(deleteSuiteButtonLocator).click();
    }

    @Step
    public boolean successfullyDeletedMessageTextIsDisplayed() {
        return driver.findElement(successfullyDeletedMessageText).isDisplayed();
    }

    @Step("Checking the existence and visibility of the suite with title '{suiteTitle}'")
    public boolean isSuiteExistAndDisplayed(String suiteTitle) {
        List<WebElement> suitesList = driver.findElements(allSuites);
        for (WebElement suite : suitesList) {
            if (suite.getText().equals(suiteTitle)) {
                return false;
            }
        }
        return true;
    }
}
