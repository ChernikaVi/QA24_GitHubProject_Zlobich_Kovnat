package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Locale;

@Log4j2
public class CreateNewProjectPage extends BasePage {
    public CreateNewProjectPage(WebDriver driver) {
        super(driver);
    }

    private static final By PROJECT_NAME = By.cssSelector("#project-name");
    private static final By PROJECT_CODE = By.cssSelector("#project-code");
    private static final By DESCRIPTION = By.cssSelector("#description-area");
    private static final By privateRadioButton = By.xpath("//*[@value='private']");
    private static final By publicRadioButton = By.xpath("//*[@value='public']");

    private static final By createNewProjectButtonLocator = By.cssSelector("#createButton");
    private static final By createProjectButtonLocator = By.xpath("//*[@type='submit']");


    @Override
    public CreateNewProjectPage openPage() {
        driver.get("https://app.qase.io/projects");
        return this;
    }

    @Override
    public CreateNewProjectPage isPageOpened() {
        wait.until(ExpectedConditions.elementToBeClickable(createNewProjectButtonLocator));
        return this;
    }

    @Step("Filling out project")
    public void fillingOutProjectForm(Project project) {
        log.info(String.format("Filling out project form = %s", project));
        driver.findElement(PROJECT_NAME).sendKeys(project.getName());
        driver.findElement(PROJECT_CODE).sendKeys(project.getCode());
        driver.findElement(DESCRIPTION).sendKeys(project.getDescription());
    }

    @Step
    public void clickOnProjectButton(){
        driver.findElement(createProjectButtonLocator).click();
    }

    @Step
    public void clickOnPublicRadioButton(){
        driver.findElement(publicRadioButton).click();
    }

    @Step
    public void clickOnPrivateRadioButton(){
        driver.findElement(privateRadioButton).click();
    }
}