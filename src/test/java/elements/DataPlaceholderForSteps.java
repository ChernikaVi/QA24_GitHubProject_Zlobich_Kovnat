package elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Log4j2
public class DataPlaceholderForSteps extends BaseElement {

    private String dataPlaceholderForStepsLocator = "//p[@data-placeholder='%s']";
    private int title;
    private String itemName;

    public DataPlaceholderForSteps(WebDriver driver, String itemName) {
        super(driver);
        this.itemName = itemName;
    }

    public void setDataPlaceholderForStepsValue(String value) {
        WebElement dataPlaceholder = driver.findElement(By.xpath(String.format(dataPlaceholderForStepsLocator, this.itemName)));
        scrollToElement(dataPlaceholder);
        log.info(String.format("sending the following value: %s", value));
        dataPlaceholder.sendKeys(value);
    }
}
