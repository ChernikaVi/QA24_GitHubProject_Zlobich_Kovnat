package api_tests;

import controllers.DefectController;
import io.restassured.response.Response;
import models.Defect;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DefectApiTests extends BaseApiTest {
    public final static String NEW_DEFECT_TITLE = "NEW DEFECT TITLE";
    public final static String DEFECT_TITLE = "New test defect";
    public final static String DEFECT_RESULT = "Actual result";
    public final static int DEFECT_SEVERITY = 3;
    private int defectId;
    private DefectController defectController;

    @BeforeTest
    public void initDefect() {
        defectController = new DefectController();
        Defect defect = Defect.builder()
                .title(DEFECT_TITLE)
                .severity(DEFECT_SEVERITY)
                .actual_result(DEFECT_RESULT)
                .build();

        Response response = defectController.addDefect(PROJECT_CODE, defect);
        defectId = response
                .getBody()
                .jsonPath()
                .getInt("result.id");
        defect.setId(defectId);
    }

    @Test(priority = 1)
    public void addDefect() {
        Defect newDefect = Defect.builder()
                .title(DEFECT_TITLE)
                .severity(DEFECT_SEVERITY)
                .actual_result(DEFECT_RESULT)
                .build();

        Response defectResponse = defectController
                .addDefect(PROJECT_CODE, newDefect);

        int newDefectId = defectResponse
                .getBody()
                .jsonPath()
                .getInt("result.id");

        boolean status = defectResponse.getBody()
                .jsonPath()
                .getBoolean("status");
        assertTrue(status);
        assertNotEquals(newDefectId, 0);
    }

    @Test(priority = 2)
    public void getDefect() {
        Response response = defectController.getDefect(PROJECT_CODE, defectId);

        String actualResult = response
                .getBody()
                .jsonPath()
                .getString("result.actual_result");
        String actualTitle = response
                .getBody()
                .jsonPath()
                .getString("result.title");
        int actualId = response
                .getBody()
                .jsonPath()
                .getInt("result.id");

        assertEquals(response.statusCode(), 200);
        assertEquals(actualResult, DEFECT_RESULT);
        assertEquals(actualTitle, DEFECT_TITLE);
        assertEquals(actualId, defectId);
    }

    @Test(priority = 3)
    public void updateDefect() {
        Defect newDefect = Defect.builder()
                .title(NEW_DEFECT_TITLE)
                .id(defectId)
                .severity(DEFECT_SEVERITY)
                .build();

        defectController.updateDefect(PROJECT_CODE, defectId, newDefect.getTitle());
        Response getResponse = defectController.getDefect(PROJECT_CODE, defectId);

        String actualResult = getResponse
                .getBody()
                .jsonPath()
                .getString("result.actual_result");
        String actualTitle = getResponse
                .getBody()
                .jsonPath()
                .getString("result.title");
        int actualId = getResponse
                .getBody()
                .jsonPath()
                .getInt("result.id");

        assertEquals(actualResult, DEFECT_RESULT);
        assertEquals(actualTitle, newDefect.getTitle());
        assertEquals(actualId, defectId);
    }

    @Test(priority = 4)
    public void deleteDefect() {
        defectController.deleteDefect(PROJECT_CODE, defectId);
        Response getResponse = defectController.getDefect(PROJECT_CODE, defectId);
        int statusCode = getResponse
                .getStatusCode();
        assertEquals(statusCode, 404);
    }
}