package tests;

import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import models.TestCase;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestDataGenerator;


public class CreateTestCaseTest extends BaseTest{
    @Test(groups = {"smoke"})
    @Description("Test Log In Form")
    @Link(name = "Log In Page")
    @Severity(SeverityLevel.CRITICAL)
    public void positiveCreateNewTestCaseTest() {
        TestCase testCase = TestDataGenerator.createTestCaseGeneration();
        projectsPage.isPageOpened();
        projectsPage.clickOnProjectTitle();
        createSuitePage.isPageOpened();
        projectsPage.clickCreateNewTestCaseButton();
        createNewTestCasePage.clickAddStepButton();
        createNewTestCasePage.fillingOutTestCaseForm(testCase);
        createNewTestCasePage.clickSaveCaseButton();
        Assert.assertTrue(createNewTestCasePage.successfullyCreatedTestCaseMessageIsDisplayed());
        testCaseInfo.clickTestCaseButton();
        testCaseInfo.clickTestCaseEditButton();
        TestCase expectedProject = testCaseInfo.getTestCaseDetails();
        Assert.assertEquals(expectedProject, testCase);
    }
}
