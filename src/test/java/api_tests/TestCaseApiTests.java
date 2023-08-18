package api_tests;

import controllers.TestCaseController;
import io.restassured.response.Response;
import models.Case;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TestCaseApiTests extends BaseApiTest {
    public final static String TEST_CASE_TITLE = "Test Case for Diploma";
    public final static String TEST_CASE_DESCRIPTION = "Test case description";
    public final static String NEW_TEST_CASE_TITLE = "New test case title";
    private int caseId;
    private Case testCase;
    private TestCaseController testCaseController;

    @BeforeTest
    private void initCase() {
        testCaseController = new TestCaseController();
        testCase = Case.builder()
                .title(TEST_CASE_TITLE)
                .description(TEST_CASE_DESCRIPTION)
                .build();
        caseId = testCaseController
                .addTestCase(PROJECT_CODE, testCase)
                .getBody()
                .jsonPath()
                .getInt("result.id");

        testCase.setId(caseId);
    }

    @Test(priority = 1)
    public void addTestCase() {                 //
        Case newTestCase = Case.builder()
                .title(TEST_CASE_TITLE)
                .description(TEST_CASE_DESCRIPTION)
                .build();

        Response response = testCaseController.addTestCase(PROJECT_CODE, newTestCase);
        boolean status = response.getBody()
                .jsonPath()
                .getBoolean("status");
        int id = response.getBody()
                .jsonPath()
                .getInt("result.id");
        assertTrue(status);
        assertNotEquals(id, 0);
    }

    @Test(priority = 2)
    public void getTestCase() {
        Response response = testCaseController.getTestCase(PROJECT_CODE, caseId);
        Case actualTestCase = response
                .getBody()
                .jsonPath()
                .getObject("result", Case.class);
        assertEquals(response.statusCode(), 200);
        assertEquals(actualTestCase, testCase);
    }

    @Test(priority = 3)
    public void updateTestCase() {
        Case newTestCase = Case.builder()
                .title(NEW_TEST_CASE_TITLE)
                .id(caseId)
                .description(TEST_CASE_DESCRIPTION)
                .build();

        testCaseController.updateTestCase(PROJECT_CODE, caseId, newTestCase.getTitle());
        Response getResponse = testCaseController.getTestCase(PROJECT_CODE, caseId);
        Case actualTestCase = getResponse
                .getBody()
                .jsonPath()
                .getObject("result", Case.class);
        assertEquals(actualTestCase, newTestCase);
    }

    @Test(priority = 4)
    public void deleteCaseTest() {
        testCaseController.deleteTestCase(PROJECT_CODE, caseId);
        Response getResponse = testCaseController.getTestCase(PROJECT_CODE, caseId);
        int statusCode = getResponse
                .getStatusCode();
        assertEquals(statusCode, 404);
    }
}

