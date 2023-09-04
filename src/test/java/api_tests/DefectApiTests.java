package api_tests;

import controllers.DefectController;
import io.restassured.response.Response;
import models.Defect;
import models.DefectResponse;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import enums.DefectSeverity;

import static org.testng.Assert.*;

public class DefectApiTests extends BaseApiTest {
    public final static String NEW_DEFECT_TITLE = "NEW DEFECT TITLE";
    public final static String DEFECT_TITLE = "New test defect";
    public final static String DEFECT_RESULT = "Actual result";
    protected int defectId;


    @BeforeTest
    public void initDefect() {
        defectController = new DefectController();
        Defect defect = Defect.builder()
                .title(DEFECT_TITLE)
                .severity(DefectSeverity.MAJOR.getIntValue())
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
                .severity(DefectSeverity.MAJOR.getIntValue())
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
        assertNotNull(newDefectId);
    }

    @Test(priority = 2)
    public void getDefect() {
        DefectResponse expectedDefect = DefectResponse.builder()
                .id(defectId)
                .title(DEFECT_TITLE)
                .actual_result(DEFECT_RESULT)
                .severity(DefectSeverity.MAJOR.getStringValue())
                .build();

        Response response = defectController
                .getDefect(PROJECT_CODE, defectId);

        DefectResponse actualDefect = response
                .getBody()
                .jsonPath()
                .getObject("result", DefectResponse.class);

        assertEquals(actualDefect, expectedDefect);
    }

    @Test(priority = 3)
    public void updateDefect() {
        Defect updatedDefect = Defect.builder()
                .title(NEW_DEFECT_TITLE)
                .id(defectId)
                .actual_result(DEFECT_RESULT)
                .severity(DefectSeverity.MAJOR.getIntValue())
                .build();

        DefectResponse expectedUpdatedDefect = DefectResponse.builder()
                .id(defectId)
                .title(NEW_DEFECT_TITLE)
                .actual_result(DEFECT_RESULT)
                .severity(DefectSeverity.MAJOR.getStringValue())
                .build();


        Response getResponse = defectController.updateDefect(PROJECT_CODE, defectId, updatedDefect.getTitle());
        int statusCode = getResponse
                .getStatusCode();
        assertEquals(statusCode, 200);


        Response response = defectController.getDefect(PROJECT_CODE, defectId);
        DefectResponse actualUpdatedDefect = response
                .getBody()
                .jsonPath()
                .getObject("result", DefectResponse.class);

        assertEquals(actualUpdatedDefect, expectedUpdatedDefect);
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