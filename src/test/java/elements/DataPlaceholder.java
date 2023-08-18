package elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Log4j2
public class DataPlaceholder extends BaseElement {

    private String dataPlaceholderLocator = "//label[text()='%s']//parent::div//following-sibling::div//p[@class]";
    private String labelName;

    public DataPlaceholder(WebDriver driver, String labelName) {
        super(driver);
        this.labelName = labelName;
    }

    public void setDataPlaceholderValue(String value) {
        WebElement dataPlaceholder = (driver.findElement(By.xpath(String.format(dataPlaceholderLocator, this.labelName))));
        scrollToElement(dataPlaceholder);
        log.info(String.format("setting data placeholder value: %s", value));
        dataPlaceholder.sendKeys(value);
    }
}
