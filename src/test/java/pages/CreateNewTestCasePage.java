package pages;

import elements.*;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.SharedSteps;
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
    private By cancelButtonLocator = By.xpath("//*[text()='Cancel']");
    private By avatarButtonLocator = By.xpath("//*[@aria-label='Chat']//following-sibling::*");
    private By signOutButtonLocator = By.xpath("//span[text()='Sign out']");
    private By fileInputLocator = By.cssSelector("input[type=file]");
    private By qasePhotoLocator = By.xpath("//*[@class='case-create-block'][text()='Attachments']//following::a");
    private By cancelAllertButtonLocator = By.xpath("//span[text()='Cancel']//ancestor::*[@type='button']");
    private By sharedStepsButtonLocator = By.xpath("//*[text()='Shared Steps']");
    private By addNewStepsButtonLocator = By.xpath("//*[text()='Shared steps']//following::span[text()='Create shared step']");
    private By addStepsButtonLocator = By.xpath("//*[text()=' Add step']");
    private By sharedStepTitleIdLocator = By.cssSelector("#title");
    private By createSharedStepsButtonLocator = By.xpath("//*[@type='submit']");
    private By successfullyCreatedSharedStepsMessage = By.xpath("//*[text()='Shared step was created successfully!']");
    private By repositoryButtonLocator = By.xpath("//*[text()='Repository']");
    private By addSharedStepsInTestCaseButtonLocator = By.xpath("//*[text()=' Add shared step']");
    private By selectSharedStepsButtonLocator = By.xpath("//*[text()='Choose shared step']//following::*[@class='svg-inline--fa fa-caret-down _2ZdXF']");
    private String selectedTitleOfSharedStepsLocator = "//div[@class]//parent::div[text()= '%s']";
    private By addButtonLocator = By.xpath("//*[text()='Add']");
    private String savedValuesForStepsLocator = "(//*[@class]//parent::p)[%s]";



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
    public void clickSharedStepsButton() {
        log.info("clicking shared steps button");
        driver.findElement(sharedStepsButtonLocator).click();
    }

    @Step
    public void clickAddNewSharedStepsButton() {
        log.info("clicking add new shared steps button");
        driver.findElement(addNewStepsButtonLocator).click();
    }

    @Step
    public void clickAddStepsButton() {
        log.info("clicking add steps button");
        driver.findElement(addStepsButtonLocator).click();
    }

    @Step
    public void setTitleOfSharedSteps(SharedSteps sharedSteps) {
        driver.findElement(sharedStepTitleIdLocator).sendKeys(sharedSteps.getTitle());
    }


    @Step
    public void setSteps(SharedSteps sharedSteps) {
        log.info("Filling values into the create new test case page");
        new DataPlaceholderForSteps(driver,  "Step Action").setDataPlaceholderForStepsValue(sharedSteps.getStepAction());
        new DataPlaceholderForSteps(driver,  "Data").setDataPlaceholderForStepsValue(sharedSteps.getData());
        new DataPlaceholderForSteps(driver,  "Expected result").setDataPlaceholderForStepsValue(sharedSteps.getExpectedResult());
    }

    @Step
    public void clickCreateSharedStepsButton() {
        log.info("clicking create shared steps button");
        driver.findElement(createSharedStepsButtonLocator).click();
    }
    @Step
    public boolean successfullyCreatedSharedStepsMessageIsDisplayed() {
        return driver.findElement(successfullyCreatedSharedStepsMessage).isDisplayed();
    }

    @Step
    public void clickRepositoryButton() {
        log.info("clicking repository button");
        driver.findElement(repositoryButtonLocator).click();
    }

    @Step
    public void clickAddSharedStepsInTestCaseButton() {
        log.info("clicking add shared steps in test case button");
        driver.findElement(addSharedStepsInTestCaseButtonLocator).click();
    }

    @Step
    public void clickSelectSharedStepsInTestCaseButton() {
        log.info("clicking select button");
        driver.findElement(selectSharedStepsButtonLocator).click();
    }

    @Step
    public void clickSelectedTitleButton(String titleName){
        log.info(String.format("clicking on selected title: %s", titleName));
        driver.findElement(By.xpath(String.format(selectedTitleOfSharedStepsLocator, titleName))).click();
    }

    @Step
    public void clickAddButton() {
        log.info("clicking add button");
        driver.findElement(addButtonLocator).click();
    }

    public String getSavedValuesForSteps(int numberOfSharedSteps) {
        log.info(String.format("getting value from steps field with number: %s", numberOfSharedSteps));
        return driver.findElement(By.xpath(String.format(savedValuesForStepsLocator, numberOfSharedSteps))).getText();
    }

    public SharedSteps.SharedStepsBuilder getDataForSharedSteps() {
        log.info("getting value from data placeholder for steps field with label name");
        SharedSteps.SharedStepsBuilder sharedSteps = new SharedSteps.SharedStepsBuilder();
        sharedSteps.setStepAction(this.getSavedValuesForSteps(1));
        sharedSteps.setData(this.getSavedValuesForSteps(2));
        sharedSteps.setExpectedResult(this.getSavedValuesForSteps(3));
        return SharedSteps.builder();
    }


    @Step
    public void clickCancelCaseButton() {
        log.info("clicking cancel test case button");
        driver.findElement(cancelButtonLocator).click();
    }

    @Step
    public void clickCancelAllertButton() {
        log.info("clicking cancel allert button");
        driver.findElement(cancelAllertButtonLocator).click();
    }

    @Step
    public void clickAvatarButton() {
        log.info("clicking avatar button");
        driver.findElement(avatarButtonLocator).click();
    }

    @Step
    public void clickSignOutButton() {
        log.info("clicking sign Out button");
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

    @Step("Filling out test case with shared steps")
    public void fillingOutTestCaseFormWithSharedSteps(TestCase testCase) {
        log.info("Filling values into the create new test case page");
        new Input(driver, "Title").setInputValue(testCase.getTitle());
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
