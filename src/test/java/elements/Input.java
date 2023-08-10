package elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Log4j2
public class Input extends BaseElement {
    private String labelName;
    private final static String inputLocator = "//label[text()='%s']/parent::div//following-sibling::div//input";

    public Input(WebDriver driver, String labelName) {
        super(driver);
        this.labelName = labelName;
    }

    public void setInputValue(String inputValue) {
        WebElement input = driver.findElement(By.xpath(String.format(inputLocator, this.labelName)));
        scrollToElement(input);
        input.clear();
        log.info(String.format("entering the input value: %s", inputValue));
        driver.findElement(By.xpath(String.format(inputLocator, this.labelName))).sendKeys(inputValue);
    }
}
