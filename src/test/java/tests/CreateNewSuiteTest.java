package tests;

import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import models.Project;
import models.Suite;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.TestDataGenerator;

public class CreateNewSuiteTest extends BaseTest {
    @BeforeMethod(alwaysRun = true)
    public void createNewProject() {
        Project project = TestDataGenerator.positiveAddProjectGeneration();
        Suite suite = TestDataGenerator.addSuiteGeneration();
        loginPage.openPage()
                .isPageOpened()
                .logIn(EMAIL, PASSWORD);
        projectsPage.clickCreateNewProjectButton();
        createNewProjectPage.fillingOutProjectForm(project);
        createNewProjectPage.clickOnPrivateRadioButton();
        createNewProjectPage.clickOnProjectButton();
        createNewProjectPage.clickAllProjectsButton();
    }

    @Test(groups = {"regression"})
    @Description("Create new suite")
    @Link(name = "Create suite page")
    @Severity(SeverityLevel.CRITICAL)
    public void createNewSuitTest() {
        Suite suite = TestDataGenerator.addSuiteGeneration();
        projectsPage.isPageOpened();
        projectsPage.clickOnProjectTitle();
        createSuitePage.clickCreateSuiteButton();
        createSuitePage.fillingOutSuiteForm(suite);
        createSuitePage.clickCreateNewSuiteButton();
        Assert.assertTrue(createSuitePage.getSuccessfullyCreatedSuiteMessageIsDisplayed());
        Assert.assertTrue(createSuitePage.isSuiteDisplayed(TestDataGenerator.addSuiteGeneration().getSuiteTitle()));
    }

    @Test(groups = {"regression"})
    @Description("Edit suite")
    @Link(name = "Create suite page")
    @Severity(SeverityLevel.CRITICAL)
    public void editSuitTest() {
        Suite suite = TestDataGenerator.editSuiteGeneration();
        projectsPage.isPageOpened();
        projectsPage.clickOnProjectTitle();
        createSuitePage.clickCreateSuiteButton();
        createSuitePage.fillingOutSuiteForm(suite);
        createSuitePage.clickCreateNewSuiteButton();
        createSuitePage.clickEditSuiteButtonIcon();
        createSuitePage.fillingOutSuiteForm(suite);
        createSuitePage.clickSaveChangesSuiteButton();
        Assert.assertTrue(createSuitePage.editedTitleIsDisplayed());
    }

    @Test(groups = {"regression"})
    @Description("Delete suite")
    @Link(name = "Create suite page")
    @Severity(SeverityLevel.CRITICAL)
    public void deleteSuitTest() {
        projectsPage.isPageOpened();
        projectsPage.clickOnProjectTitle();
        createSuitePage.clickDeleteSuiteButtonIcon();
        createSuitePage.clickDeleteSuiteButton();
        Assert.assertTrue(createSuitePage.successfullyDeletedMessageTextIsDisplayed());
        Assert.assertTrue(createSuitePage.isSuiteExist(TestDataGenerator.addSuiteGeneration().getSuiteTitle()));
    }
}