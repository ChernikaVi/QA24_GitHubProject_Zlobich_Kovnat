package elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Log4j2
public class DropdownForCase extends BaseElement {

    private final static String dropdownLocator = "//label[text()='%s']//parent::div//span";
    private final static String dropdownValue = "//div[text()='%s']";
    private String labelName;

    public DropdownForCase(WebDriver driver, String labelName) {
        super(driver);
        this.labelName = labelName;
    }

    public void chooseDropdownOption(String value) {
        WebElement dropdown = driver.findElement(By.xpath(String.format(dropdownLocator, this.labelName)));
        scrollToElement(dropdown);
        log.info(String.format("clicking dropdown button with label: %s", this.labelName));
        driver.findElement(By.xpath(String.format(dropdownLocator, this.labelName))).click();
        log.info(String.format("clicking dropdown option: %s", value));
        driver.findElement(By.xpath(String.format(dropdownValue, value))).click();
    }
}
