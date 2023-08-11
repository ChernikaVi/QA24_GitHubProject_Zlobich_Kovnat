package api_tests;

import controllers.ProjectController;
import controllers.TestCaseController;
import models.Case;
import models.QaseProject;
import org.testng.Assert;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class QaseApiTests extends BaseApiTest {

    public final static String PROJECT_TITLE = "Qase Api Tests";
    public final static String PROJECT_CODE = "QAT";
    public final static int CASE_ID = 1;
    public final static String NEW_TESTCASE_TITLE = "New test case title";

    @BeforeTest
    public void addProject() {

        QaseProject project = QaseProject.builder()
                .title(PROJECT_TITLE)
                .code(PROJECT_CODE)
                .description("Api tests for Diploma")
                .build();
        new ProjectController().addProject(project);

    }

    @AfterTest
    public void deleteProject() {

        int statusCode = new ProjectController().deleteProject(PROJECT_CODE).statusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test(priority = 2)
    public void getProject() {
        int statusCode = new ProjectController().getProject(PROJECT_CODE).statusCode();
        assertEquals(statusCode, 200);
    }

    @Test(priority = 1)
    public void addTestCase() {

        Case testCase = Case.builder()
                .title("Test Case for Diploma")
                .code(PROJECT_CODE)
                .id(CASE_ID)
                .description("New test case")
                .build();
        new TestCaseController().addTestCase(PROJECT_CODE, testCase);


    }

    @Test(priority = 3)
    public void getTestCase() {
        int statusCode = new TestCaseController().getTestCase(PROJECT_CODE).statusCode();
        assertEquals(statusCode, 200);
    }

    @Test(priority = 4)
    public void updateTestCase() {
        int statusCode = new TestCaseController().updateTestCase(PROJECT_CODE, CASE_ID, NEW_TESTCASE_TITLE).statusCode();
        assertEquals(statusCode, 200);
    }

    @Test(priority = 5)
    public void deleteCaseTest() {
        int statusCode = new TestCaseController().deleteTestCase(PROJECT_CODE, CASE_ID).statusCode();
        Assert.assertEquals(statusCode, 200);
    }
}