package tests;

import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateNewProjectTests extends BaseTest{
    @Test(groups = {"smoke"})
    @Description("Тестирование формы создания репозитория")
    @Link(name = "Log In Page", url = "")
    @Severity(SeverityLevel.CRITICAL)
    public void createNewProjectTests() {
        Project project = new Project();
        project.setName("My first project");
        project.setCode("5555");
        project.setDescription("There are my test cases");

        loginPage.openPage()
                .isPageOpened()
                .logIn(USERNAME,PASSWORD);
        Assert.assertTrue(projectsPage.isCreateProjectButtonDisplayed());

        projectsPage.isPageOpened()
                .clickCreateNewProjectButton();
        Assert.assertTrue(projectsPage.isCreateProjectFormDisplayed());

        createNewProjectPage.isPageOpened()
                .fillingOutProjectForm(project);
        createNewProjectPage.clickOnPrivateRadioButton();
        createNewProjectPage.clickOnProjectButton();
    }
}
