package tests;

import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import models.Project;
import models.TestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.TestDataGenerator;


public class CreateTestCaseTest extends BaseTest {
    private static final String FILE_NAME = "Qase";
    private String filePath = System.getProperty("user.dir") + "/src/test/resources/qase.png";
    @BeforeMethod(alwaysRun = true)
    public void createNewProject() {
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
        projectsPage.clickOnProjectTitle();
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

    @Test(description = "Check if file can be uploaded in test case", groups = "regression")
    public void fileUploadTest() {
        TestCase testCase = TestDataGenerator.createTestCaseGeneration();
        projectsPage.isPageOpened();
        projectsPage.clickOnProjectTitle();
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
}