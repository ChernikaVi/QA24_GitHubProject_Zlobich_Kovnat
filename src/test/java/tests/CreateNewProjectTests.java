package tests;

import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.TestDataGenerator;

public class CreateNewProjectTests extends BaseTest {
    @BeforeMethod(alwaysRun = true)
    public void logIn() {
        loginPage.openPage()
                .isPageOpened()
                .logIn(EMAIL, PASSWORD);
        projectsPage.clickCreateNewProjectButton();
    }

    @Test(groups = {"smoke"})
    @Description("Positive test form of creating a project")
    @Link(name = "Create new project Page")
    @Severity(SeverityLevel.CRITICAL)
    public void createNewProjectPositiveTest() {
        Project actualProject = TestDataGenerator.positiveAddProjectGeneration();
        createNewProjectPage.fillingOutProjectForm(actualProject);
        createNewProjectPage.clickOnPrivateRadioButton();
        createNewProjectPage.clickOnProjectButton();
        projectsRepositoryPage.clickSettingsButton();
        Project expectedProject = projectSettingsPage.getProjectInfo();
        Assert.assertEquals(expectedProject, actualProject);
    }

    @Test(groups = {"smoke"})
    @Description("Negative test form of creating a project")
    @Link(name = "Crate new project page")
    @Severity(SeverityLevel.CRITICAL)
    public void createNewProjectNegativeTest() {
        Project actualProject = TestDataGenerator.negativeAddProjectGeneration();
        createNewProjectPage.fillingOutProjectForm(actualProject);
        createNewProjectPage.clickOnPrivateRadioButton();
        createNewProjectPage.clickOnProjectButton();
        Assert.assertTrue(createNewProjectPage.createProjectFormIsDisplayed());
    }
}
