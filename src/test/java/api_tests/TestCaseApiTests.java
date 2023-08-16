package api_tests;

import controllers.TestCaseController;
import io.restassured.response.Response;
import models.Case;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestCaseApiTests extends BaseApiTest {


    public final static String PROJECT_CODE = "QAT";
    public final static int CASE_ID = 1;
    public final static String TEST_CASE_TITLE = "Test Case for Diploma";
    public final static String TEST_CASE_DESCRIPTION = "Test case description";
    public final static String NEW_TEST_CASE_TITLE = "New test case title";

    @Test(priority = 1)
    public void addTestCase() {

        Case testCase = Case.builder()
                .title(TEST_CASE_TITLE)
                .code(PROJECT_CODE)
                .id(CASE_ID)
                .description(TEST_CASE_DESCRIPTION)
                .build();

        Response response = new TestCaseController().addTestCase(PROJECT_CODE, testCase);
        assertEquals(response.statusCode(), 200);
        int actualCaseId = response
                .getBody()
                .jsonPath()
                .getInt("result.id");
        assertEquals(actualCaseId, CASE_ID);
    }

    @Test(priority = 2)
    public void getTestCase() {
        Response response = new TestCaseController().getTestCase(PROJECT_CODE);
        assertEquals(response.statusCode(), 200);

        String actualTestCaseDescription = response
                .getBody()
                .jsonPath()
                .getString("result.entities.description")
                .replaceAll("\\[|\\]", "");
        assertEquals(actualTestCaseDescription, TEST_CASE_DESCRIPTION);
    }

    @Test(priority = 3)
    public void updateTestCase() {
        Response response = new TestCaseController().updateTestCase(PROJECT_CODE, CASE_ID, NEW_TEST_CASE_TITLE);
        assertEquals(response.statusCode(), 200);

        Response getResponse = new TestCaseController().getTestCase(PROJECT_CODE);
        String actualTestCaseTitle = getResponse
                .getBody()
                .jsonPath()
                .getString("result.entities.title")
                .replaceAll("\\[|\\]", "");
        assertEquals(actualTestCaseTitle, NEW_TEST_CASE_TITLE);
    }

    @Test(priority = 4)
    public void deleteCaseTest() {
        Response response = new TestCaseController().deleteTestCase(PROJECT_CODE, CASE_ID);
        Assert.assertEquals(response.statusCode(), 200);

        Response getResponse = new TestCaseController().getTestCase(PROJECT_CODE);
        int actualTestCasesValue = getResponse
                .getBody()
                .jsonPath()
                .getInt("result.total");
        assertEquals(actualTestCasesValue, 0);
    }
}

