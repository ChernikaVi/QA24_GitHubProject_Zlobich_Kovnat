package tests;

import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import models.SharedSteps;
import models.TestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.TestDataGenerator;


public class CreateTestCaseTest extends BaseTest {
    private static final String FILE_NAME = "Qase";
    private String filePath = System.getProperty("user.dir") + "/src/test/resources/qase.png";
    @BeforeMethod(alwaysRun = true)
    public void logIn() {
        loginPage.openPage()
                .isPageOpened()
                .logIn(EMAIL, PASSWORD);
    }

    @Test(groups = {"smoke"})
    @Description("Test Log In Form")
    @Link(name = "Log In Page")
    @Severity(SeverityLevel.CRITICAL)
    public void positiveCreateNewTestCaseTest() {
        TestCase testCase = TestDataGenerator.createTestCaseGeneration();
        projectsPage.isPageOpened();
        projectsPage.clickOnProjectTitle(PROJECT_TITLE);
        projectsPage.clickCreateNewTestCaseButton();
        createNewTestCasePage.clickAddStepButton();
        createNewTestCasePage.fillingOutTestCaseForm(testCase);
        createNewTestCasePage.clickSaveCaseButton();
        Assert.assertTrue(createNewTestCasePage.successfullyCreatedTestCaseMessageIsDisplayed());
        testCaseInfo.clickTestCaseButton(testCase.getTitle());
        testCaseInfo.clickTestCaseEditButton();
        TestCase expectedProject = testCaseInfo.getTestCaseDetails();
        Assert.assertEquals(expectedProject, testCase);
    }

    @Test(description = "Check if file can be uploaded in test case", groups = "regression")
    public void fileUploadTest() {
        TestCase testCase = TestDataGenerator.createTestCaseGeneration();
        projectsPage.isPageOpened();
        projectsPage.clickOnProjectTitle(PROJECT_TITLE);
        projectsPage.clickCreateNewTestCaseButton();
        createNewTestCasePage.clickAddStepButton();
        createNewTestCasePage.fillingOutTestCaseForm(testCase);
        createNewTestCasePage.clickAddAttachmentButton();
        createNewTestCasePage.uploadFile(filePath);
        Assert.assertTrue(createNewTestCasePage.successfullyUploadedFileIsDisplayed());
        createNewTestCasePage.clickCancelCaseButton();
        createNewTestCasePage.clickCancelAllertButton();
        createNewTestCasePage.clickSaveCaseButton();
        Assert.assertTrue(createNewTestCasePage.successfullyCreatedTestCaseMessageIsDisplayed());
        createNewTestCasePage.clickAvatarButton();
        createNewTestCasePage.clickSignOutButton();
    }

    @Test(description = "Creating test case with shared steps", groups = "regression")
    public void testCaseWithSharedSteps() {
        TestCase testCaseWithSharedSteps = TestDataGenerator.createNewTestCaseWithSharedStepsGeneration();
        SharedSteps sharedStepsData = TestDataGenerator.addSharedStepsGeneration();
        projectsPage.isPageOpened();
        projectsPage.clickOnProjectTitle(PROJECT_TITLE);
        createNewTestCasePage.clickSharedStepsButton();
        createNewTestCasePage.clickAddNewSharedStepsButton();
        createNewTestCasePage.clickAddStepsButton();
        createNewTestCasePage.setTitleOfSharedSteps(sharedStepsData);
        createNewTestCasePage.setSteps(sharedStepsData);
        createNewTestCasePage.clickCreateSharedStepsButton();
        Assert.assertTrue(createNewTestCasePage.successfullyCreatedSharedStepsMessageIsDisplayed());
        createNewTestCasePage.clickRepositoryButton();

        projectsPage.clickCreateNewTestCaseButton();
        createNewTestCasePage.fillingOutTestCaseFormWithSharedSteps(testCaseWithSharedSteps);
        createNewTestCasePage.clickAddSharedStepsInTestCaseButton();
        createNewTestCasePage.clickSelectSharedStepsInTestCaseButton();
        createNewTestCasePage.clickSelectedTitleButton(sharedStepsData.getTitle());
        createNewTestCasePage.clickAddButton();
        createNewTestCasePage.clickSaveCaseButton();
        testCaseInfo.clickTestCaseButton(testCaseWithSharedSteps.getTitle());
        testCaseInfo.clickTestCaseEditButton();
        createNewTestCasePage.getDataForSharedSteps();
    }
}