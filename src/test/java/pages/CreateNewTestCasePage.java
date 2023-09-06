package pages;

import elements.*;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j2
public class CreateNewTestCasePage extends BasePage {
    WebDriverWait wait;

    private By addStepButtonLocator = By.xpath("//*[text()=' Add step']");
    private By saveButtonLocator = By.cssSelector("#save-case");
    private By successfullyCreatedTestCaseMessage = By.xpath("//*[text()='Test case was created successfully!']");
    private By addAttachmentButtonLocator = By.xpath("//*[text()='Add attachment']");
    private By uploadNewAttachmentsLocator = By.cssSelector(".attach-new-form");
    private By cancelButtonLocator = By.xpath("//*[text()='Cancel']");
    private By avatarButtonLocator = By.xpath("//*[@aria-label='Chat']//following-sibling::*");
    private By signOutButtonLocator = By.xpath("//span[text()='Sign out']");
    private By fileInputLocator = By.cssSelector("input[type=file]");
    private By qasePhotoLocator = By.xpath("//*[@class='case-create-block'][text()='Attachments']//following::a");
    private By cancelAllertButtonLocator = By.xpath("//span[text()='Cancel']//ancestor::*[@type='button']");


    @Override
    public CreateNewTestCasePage openPage() {
        return null;
    }

    @Override
    public CreateNewTestCasePage isPageOpened() {
        waitForElementClickable(addAttachmentButtonLocator);
        return this;
    }

    public CreateNewTestCasePage(WebDriver driver) {
        super(driver);
    }

    @Step
    public void clickAddStepButton() {
        log.info("clicking add step button");
        scrollToElement(driver.findElement(addStepButtonLocator));
        driver.findElement(addStepButtonLocator).click();
    }

    @Step
    public void clickSaveCaseButton() {
        log.info("clicking save test case button");
        driver.findElement(saveButtonLocator).click();
    }

    @Step
    public void clickCancelCaseButton() {
        log.info("clicking cancel test case button");
        driver.findElement(cancelButtonLocator).click();
    }

    @Step
    public void clickCancelAllertButton() {
        log.info("clicking cancel test case button");
        driver.findElement(cancelAllertButtonLocator).click();
    }

    @Step
    public void clickAvatarButton() {
        log.info("clicking avatar button");
        driver.findElement(avatarButtonLocator).click();
    }

    @Step
    public void clickSignOutButton() {
        log.info("clicking Sign Out button");
        driver.findElement(signOutButtonLocator).click();
    }


    @Step
    public boolean successfullyCreatedTestCaseMessageIsDisplayed() {
        return driver.findElement(successfullyCreatedTestCaseMessage).isDisplayed();
    }


    @Step
    public boolean successfullyUploadedFileIsDisplayed() {
        return driver.findElement(qasePhotoLocator).isDisplayed();
    }

    @Step("Filling out test case")
    public void fillingOutTestCaseForm(TestCase testCase) {
        log.info("Filling values into the create new test case page");
        new Input(driver, "Title").setInputValue(testCase.getTitle());
        new DataPlaceholder(driver, "Description").setDataPlaceholderValue(testCase.getDescription());
        new DataPlaceholder(driver, "Pre-conditions").setDataPlaceholderValue(testCase.getPreConditions());
        new DataPlaceholder(driver, "Post-conditions").setDataPlaceholderValue(testCase.getPostConditions());
        new DropdownForCase(driver, "Status").chooseDropdownOption(testCase.getStatus().getName());
        new DropdownForCase(driver, "Severity").chooseDropdownOption(testCase.getSeverity().getName());
        new DropdownForCase(driver, "Priority").chooseDropdownOption(testCase.getPriority().getName());
        new DropdownForCase(driver, "Type").chooseDropdownOption(testCase.getType().getName());
        new DropdownForCase(driver, "Layer").chooseDropdownOption(testCase.getLayer().getName());
        new DropdownForCase(driver, "Is flaky").chooseDropdownOption(testCase.getIsFlaky().getName());
        new DropdownForCase(driver, "Behavior").chooseDropdownOption(testCase.getBehavior().getName());
        new DropdownForCase(driver, "Automation status").chooseDropdownOption(testCase.getAutomationStatus().getName());
        new DataPlaceholderForSteps(driver,  "Step Action").setDataPlaceholderForStepsValue(testCase.getStepAction());
        new DataPlaceholderForSteps(driver,  "Data").setDataPlaceholderForStepsValue(testCase.getData());
        new DataPlaceholderForSteps(driver,  "Expected result").setDataPlaceholderForStepsValue(testCase.getExpectedResult());
    }
    public void clickAddAttachmentButton(){
        driver.findElement(addAttachmentButtonLocator).click();
    }

    @Step("Uploading file")
    public void uploadFile(String filePath) {
        log.info("Uploading file: '{filePath}");
        driver.findElement(fileInputLocator).sendKeys(filePath);
    }
}
