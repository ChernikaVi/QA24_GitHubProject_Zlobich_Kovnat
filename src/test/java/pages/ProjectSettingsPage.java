package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class ProjectSettingsPage extends BasePage{
    public ProjectSettingsPage(WebDriver driver) {
        super(driver);
    }
    private By deleteProjectButtonLocator = By.xpath("//*[text()=' Delete project']");

    @Override
    public ProjectSettingsPage openPage() {
        driver.get("https://app.qase.io");
        return this;
    }

    @Override
    public ProjectSettingsPage isPageOpened() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteProjectButtonLocator));
        return this;
    }

    public Project getProjectInfo() {
        log.info(String.format("Checking of project info"));
        Project project = Project.builder()
                .setName(driver.findElement(By.cssSelector("#project-name")).getAttribute("value"))
                .setDescription(driver.findElement(By.cssSelector("#description-area")).getText()).build();
        return project;
    }
}
