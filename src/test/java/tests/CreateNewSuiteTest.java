package tests;

import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import models.Suite;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestDataGenerator;

public class CreateNewSuiteTest extends BaseTest{

    @Test(groups = {"regression"})
    @Description("Create new suite")
    @Link(name = "Create suite page")
    @Severity(SeverityLevel.CRITICAL)
    public void createNewSuitTest() {
        Suite suite = TestDataGenerator.dddSuiteGeneration();

        loginPage.openPage()
                .isPageOpened()
                .logIn(EMAIL,PASSWORD);
        projectsPage.isPageOpened();

        projectsPage.clickOnProjectTitle();
        createSuitePage.isPageOpened()
                .clickCreateSuiteButton();
        createSuitePage.fillingOutProjectForm(suite);
        createSuitePage.clickCreateNewSuiteButton();
        Assert.assertTrue(createSuitePage.getSuccessfullyCreatedSuiteMessageIsDisplayed());
    }
    @Test(groups = {"regression"}, dependsOnMethods = "createNewSuitTest")
    @Description("Edit suite")
    @Link(name = "Create suite page")
    @Severity(SeverityLevel.CRITICAL)
    public void editSuitTest() {
        Suite suite = TestDataGenerator.editSuiteGeneration();

        loginPage.openPage()
                .isPageOpened()
                .logIn(EMAIL,PASSWORD);
        projectsPage.isPageOpened();

        projectsPage.clickOnProjectTitle();
        createSuitePage.isPageOpened();
        createSuitePage.clickEditSuiteButtonIcon();
        createSuitePage.fillingOutProjectForm(suite);
        createSuitePage.clickSaveChangesSuiteButton();
        Assert.assertTrue(createSuitePage.editedTitleIsDisplayed());
    }

    @Test(groups = {"regression"})
    @Description("Delete suite")
    @Link(name = "Create suite page")
    @Severity(SeverityLevel.CRITICAL)
    public void deleteSuitTest() {

        loginPage.openPage()
                .isPageOpened()
                .logIn(EMAIL,PASSWORD);
        projectsPage.isPageOpened();

        projectsPage.clickOnProjectTitle();
        createSuitePage.isPageOpened();
        createSuitePage.clickDeleteSuiteButtonIcon();
        createSuitePage.clickDeleteSuiteButton();
        Assert.assertTrue(createSuitePage.successfullyDeletedMessageTextIsDisplayed());
    }
}
