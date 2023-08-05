package api_tests;


import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.mapper.ObjectMapperType;
import models.Defect;
import org.testng.annotations.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;


public class DefectApiTests extends BaseApiTest {

    private final static String PROJECT_TITLE = "Defect Api Tests";
    private final static String PROJECT_CODE = "DAT";
    private final static int DEFECT_ID = 1;

    @BeforeTest
    public void createProject() {
        given()
                .body(("{\"title\":\"" + PROJECT_TITLE + "\", \"code\":\"" + PROJECT_CODE + "\"}"))
                .when()
                .log().all()
                .post("/v1/project")
                .then()
                .log().all();
    }


    @AfterTest(alwaysRun = true)
    public void deleteProject() {
        given()
                .body(String.format("{\"code\": \"%s\"}", PROJECT_CODE))
                .when()
                .log().all()
                .delete("/v1/project/" + PROJECT_CODE)
                .then()
                .log().all();
    }


    @Test
    public void createNewDefect() {


        given()

                .body("{\"title\":\"New defect\",\"actual_result\":\"qwerty\",\"severity\":3}")
                .when()
                .log().all()
                .post("/v1/defect/" + PROJECT_CODE)
                .then()
                .log().all()
                .statusCode(200);
    }


    @Test
    public void getSpecificDefect() {
        given()

                .when()
                .log().all()
                .get("/v1/defect/" + PROJECT_CODE + "/" + DEFECT_ID)
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true),
                        "result.id", equalTo(1),
                        "result.title", equalTo("New defect"),
                        "result.actual_result", equalTo("qwerty"));
    }


    @Test
    public void updateDefectFile() {
        Defect defect = Defect.builder()
                .setTitle("Defect new title")
                .setActualResult("qazxsw")
                .build();

        given()

                .body(defect, ObjectMapperType.GSON)
                .when()
                .log().all()
                .patch("/v1/defect/" + PROJECT_CODE + "/" + DEFECT_ID)
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }


}




