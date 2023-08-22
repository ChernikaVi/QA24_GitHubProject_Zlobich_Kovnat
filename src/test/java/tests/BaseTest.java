package tests;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.*;
import utils.InvokedListener;
import utils.PropertyReader;

import java.time.Duration;

@Listeners({InvokedListener.class})
public abstract class BaseTest {
    protected static final String BASE_URL = PropertyReader.getProperty("base_url");
    protected static final String EMAIL = PropertyReader.getProperty("email");
    protected static final String PASSWORD = PropertyReader.getProperty("password");

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected LoginPage loginPage;
    protected ProjectsPage projectsPage;
    protected CreateNewProjectPage createNewProjectPage;
    protected ProjectSettingsPage projectSettingsPage;
    protected ProjectsRepositoryPage projectsRepositoryPage;
    protected CreateSuitePage createSuitePage;
    protected CreateNewTestCasePage createNewTestCasePage;
    protected TestCaseInfo testCaseInfo;

    @Parameters({"browserName"})
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("chrome") String browserName, ITestContext context) throws Exception {
        if (browserName.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.setBrowserVersion("114");
            driver = new ChromeDriver();
        } else if (browserName.equals("firefox")) {
            driver = new FirefoxDriver();
        } else {
            throw new Exception("Unsupported browser");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        context.setAttribute("driver", driver);
        loginPage = new LoginPage(driver);
        projectsPage = new ProjectsPage(driver);
        createNewProjectPage = new CreateNewProjectPage(driver);
        projectSettingsPage = new ProjectSettingsPage(driver);
        projectsRepositoryPage = new ProjectsRepositoryPage(driver);
        createSuitePage = new CreateSuitePage(driver);
        createNewTestCasePage = new CreateNewTestCasePage(driver);
        testCaseInfo = new TestCaseInfo(driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @BeforeMethod(alwaysRun = true)
    public void navigate() {
        driver.get(BASE_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void clearCookies() {
        driver.manage().deleteAllCookies();
        ((JavascriptExecutor) driver).executeScript(String.format("window.localStorage.clear();"));
        ((JavascriptExecutor) driver).executeScript(String.format("window.sessionStorage.clear();"));
    }
}
