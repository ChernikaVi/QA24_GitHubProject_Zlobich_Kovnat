package tests;

import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestDataGenerator;

public class CreateNewProjectTests extends BaseTest{
    @Test(groups = {"smoke"})
    @Description("Positive test form of creating a project")
    @Link(name = "Create new project Page")
    @Severity(SeverityLevel.CRITICAL)
    public void createNewProjectPositiveTest() {
        Project actualProject = TestDataGenerator.positiveAddProjectGeneration();

        loginPage.openPage()
                .isPageOpened()
                .logIn(EMAIL,PASSWORD);
        Assert.assertTrue(projectsPage.isCreateProjectButtonDisplayed());

        projectsPage.clickCreateNewProjectButton();
        Assert.assertTrue(projectsPage.isCreateProjectFormDisplayed());

        createNewProjectPage.isPageOpened()
                .fillingOutProjectForm(actualProject);
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

        loginPage.openPage()
                .isPageOpened()
                .logIn(EMAIL,PASSWORD);
        Assert.assertTrue(projectsPage.isCreateProjectButtonDisplayed());

        projectsPage.clickCreateNewProjectButton();
        Assert.assertTrue(projectsPage.isCreateProjectFormDisplayed());

        createNewProjectPage.isPageOpened()
                .fillingOutProjectForm(actualProject);
        createNewProjectPage.clickOnPrivateRadioButton();
        createNewProjectPage.clickOnProjectButton();
        projectsRepositoryPage.clickSettingsButton();
        Project expectedProject = TestDataGenerator.positiveAddProjectGeneration();
        Assert.assertNotSame(expectedProject, actualProject);
    }
}
