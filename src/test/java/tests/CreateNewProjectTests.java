package tests;

import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import models.Project;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestDataGenerator;

public class CreateNewProjectTests extends BaseTest{
    @Test(groups = {"smoke"})
    @Description("Тестирование формы создания репозитория")
    @Link(name = "Log In Page", url = "")
    @Severity(SeverityLevel.CRITICAL)
    public void createNewProjectTests() {
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
}
