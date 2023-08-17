package pages;

import enums.*;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class TestCaseInfo extends BasePage {

    private final static String INPUT_LOCATOR = "//label[text()='%s']/parent::div//following-sibling::div//input";
    private final static String DATA_PLACEHOLDER_LOCATOR = "//label[text()='%s']//parent::div//following-sibling::div//p[@class]";
    private final static String DROPDOWN_OPTION_LOCATOR = "//label[text()='%s']//parent::div//div[@class='_ZTmUa']";
    private final static String DATA_PLACEHOLDER_FOR_STEPS_LOCATOR = "//div[@title='%d']//parent::div/parent::div/following-sibling::div//p[text()='%s']//parent::div/parent::div/parent::div/following-sibling::div//p";


    private By testCaseLocator = By.xpath("//*[@data-suite-body-id]//descendant::div[@class='WVGvc_ wq7uNh']");
    private By editTestCaseButtonLocator = By.cssSelector(".fa-pencil");

    public TestCaseInfo(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage openPage() {
        return null;
    }

    @Override
    public BasePage isPageOpened() {
        return null;
    }

    @Step
    public void clickTestCaseButton() {
        driver.findElement(testCaseLocator).click();
    }

    @Step
    public void clickTestCaseEditButton() {
        driver.findElement(editTestCaseButtonLocator).click();
    }

    public String getInputValue(String labelName) {
        log.info(String.format("getting value from input field with label name: %s", labelName));
        return driver.findElement(By.xpath(String.format(INPUT_LOCATOR, labelName))).getAttribute("value");
    }

    public String getDataPlaceholderValue(String labelName) {
        log.info(String.format("getting value from data placeholder field with label name: %s", labelName));
        return driver.findElement(By.xpath(String.format(DATA_PLACEHOLDER_LOCATOR, labelName))).getText();
    }

    public String getDropdownOptionValue(String labelName) {
        log.info(String.format("getting value from dropdown field with label name: %s", labelName));
        return driver.findElement(By.xpath(String.format(DROPDOWN_OPTION_LOCATOR, labelName))).getText();
    }

    public String getDataPlaceholderForStepsValue(int numberOfSteps, String labelName) {
        log.info(String.format("getting value from data placeholder for steps field with label name: %s", labelName));
        return driver.findElement(By.xpath(String.format(DATA_PLACEHOLDER_FOR_STEPS_LOCATOR, numberOfSteps, labelName))).getText();
    }

    public TestCase getTestCaseDetails() {
        log.info("getting fields' values from test case edit page");
        TestCase.TestCaseBuilder testCase = new TestCase.TestCaseBuilder();
        testCase.setTitle(new TestCaseInfo(driver).getInputValue("Title"));
        testCase.setDescription(new TestCaseInfo(driver).getDataPlaceholderValue("Description"));
        testCase.setPreConditions(new TestCaseInfo(driver).getDataPlaceholderValue("Pre-conditions"));
        testCase.setPostConditions(new TestCaseInfo(driver).getDataPlaceholderValue("Post-conditions"));
        testCase.setStepAction(new TestCaseInfo(driver).getDataPlaceholderForStepsValue(1, "Step Action"));
        testCase.setData(new TestCaseInfo(driver).getDataPlaceholderForStepsValue(1, "Data"));
        testCase.setExpectedResult(new TestCaseInfo(driver).getDataPlaceholderForStepsValue(1, "Expected result"));
        testCase.setStatus(Status.fromString(getDropdownOptionValue("Status")));
        testCase.setSeverity(Severity.fromString(getDropdownOptionValue("Severity")));
        testCase.setPriority(Priority.fromString(getDropdownOptionValue("Priority")));
        testCase.setType(Type.fromString(getDropdownOptionValue("Type")));
        testCase.setLayer(Layer.fromString(getDropdownOptionValue("Layer")));
        testCase.setIsFlaky(IsFlaky.fromString(getDropdownOptionValue("Is flaky")));
        testCase.setBehavior(Behavior.fromString(getDropdownOptionValue("Behavior")));
        testCase.setAutomationStatus(AutomationStatus.fromString(getDropdownOptionValue("Automation status")));
        return testCase.build();
    }
}
