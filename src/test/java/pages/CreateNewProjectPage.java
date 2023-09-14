package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.PropertyReader;

@Log4j2
public class CreateNewProjectPage extends BasePage {
    public CreateNewProjectPage(WebDriver driver) {
        super(driver);
    }

    private By projectName = By.cssSelector("#project-name");
    private By projectCode = By.cssSelector("#project-code");
    private By description = By.cssSelector("#description-area");
    private By privateRadioButton = By.xpath("//*[@value='private']");
    private By publicRadioButton = By.xpath("//*[@value='public']");

    private By createNewProjectButtonLocator = By.cssSelector("#createButton");
    private By createProjectButtonLocator = By.xpath("//*[@type='submit']");
    private By createProjectForm = By.xpath("//*[contains(@role,'dialog')]");
    private By allProjectsButton = By.xpath("//a[@href='/projects' and contains(text(), 'Projects')]");


    @Override
    public CreateNewProjectPage openPage() {
        driver.get(PropertyReader.getProperty("base_url"));
        return this;
    }

    @Override
    public CreateNewProjectPage isPageOpened() {
        waitForElementClickable(createNewProjectButtonLocator);
        return this;
    }

    @Step("Filling out project")
    public void fillingOutProjectForm(Project project) {
        log.info(String.format("Filling out project form = %s", project));
        driver.findElement(projectName).sendKeys(project.getName());
        driver.findElement(projectCode).sendKeys(project.getCode());
        driver.findElement(description).sendKeys(project.getDescription());
    }

    @Step
    public void clickOnProjectButton() {
        driver.findElement(createProjectButtonLocator).click();
    }

    @Step
    public void clickOnPublicRadioButton() {
        driver.findElement(publicRadioButton).click();
    }

    @Step
    public void clickOnPrivateRadioButton() {
        driver.findElement(privateRadioButton).click();
    }

    @Step
    public boolean createProjectFormIsDisplayed() {
        return driver.findElement(createProjectForm).isDisplayed();
    }

    @Step
    public void clickAllProjectsButton() {
        driver.findElement(allProjectsButton).click();
    }
}