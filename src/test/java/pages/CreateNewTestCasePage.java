package pages;

import elements.*;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

@Log4j2
public class CreateNewTestCasePage extends BaseElement {

    private By addStepButtonLocator = By.xpath("//*[text()=' Add step']");
    private By saveButtonLocator = By.cssSelector("#save-case");
    private By successfullyCreatedTestCaseMessage = By.xpath("//*[text()='Test case was created successfully!']");
    private By uploadAttachmentsLocator = By.xpath("//*[text()='Add attachment']");
    private By uploadNewAttachmentsLocator = By.xpath("//*[text()='Drop files here to upload']");


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
    public boolean successfullyCreatedTestCaseMessageIsDisplayed() {
        return driver.findElement(successfullyCreatedTestCaseMessage).isDisplayed();
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
        new DataPlaceholderForSteps(driver, 1, "Step Action").setDataPlaceholderForStepsValue(testCase.getStepAction());
        new DataPlaceholderForSteps(driver, 1, "Data").setDataPlaceholderForStepsValue(testCase.getData());
        new DataPlaceholderForSteps(driver, 1, "Expected result").setDataPlaceholderForStepsValue(testCase.getExpectedResult());
    }
/*    @Step
    public void fileUpload() {
        Actions actions = new Actions(driver);
        driver.findElement(uploadAttachmentsLocator).click();
        driver.findElement(uploadNewAttachmentsLocator);
        driver.findElement(uploadNewAttachmentsLocator).sendKeys(System.getProperty("users") + "/src/test/FileUpload.png");
        driver.switchTo().defaultContent();
    }*/
}
