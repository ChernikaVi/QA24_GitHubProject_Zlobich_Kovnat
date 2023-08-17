package api_tests;

import controllers.DefectController;
import io.restassured.response.Response;
import models.Defect;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DefectApiTests extends BaseApiTest {

    public final static int DEFECT_ID = 1;
    public final static String NEW_DEFECT_TITLE = "NEW DEFECT TITLE";
    public final static String DEFECT_TITLE = "New test defect";


    @Test(priority = 1)
    public void addDefect() {

        Defect defect = Defect.builder()
                .title(DEFECT_TITLE)
                .code(PROJECT_CODE)
                .id(DEFECT_ID)
                .severity(3)
                .actual_result("Actual result")
                .build();

        Response response = new DefectController().addDefect(PROJECT_CODE, defect);
        assertEquals(response.statusCode(), 200);
        int actualDefectId = response
                .getBody()
                .jsonPath()
                .getInt("result.id");
        assertEquals(actualDefectId, DEFECT_ID);

    }

    @Test(priority = 2)
    public void getDefects() {
        int statusCode = new DefectController().getDefects(PROJECT_CODE).statusCode();
        assertEquals(statusCode, 200);
    }

    @Test(priority = 3)
    public void updateDefect() {
        int statusCode = new DefectController().updateDefect(PROJECT_CODE, DEFECT_ID, NEW_DEFECT_TITLE).statusCode();
        assertEquals(statusCode, 200);
    }
    @Test(priority = 4)
    public void deleteDefect() {
        int statusCode = new DefectController().deleteDefect(PROJECT_CODE, DEFECT_ID).statusCode();
        Assert.assertEquals(statusCode, 200);
    }
}

