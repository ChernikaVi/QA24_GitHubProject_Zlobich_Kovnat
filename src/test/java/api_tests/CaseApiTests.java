package api_tests;
import io.restassured.mapper.ObjectMapperType;
import models.Case;
import org.testng.annotations.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CaseApiTests extends BaseApiTest {
    private final static String PROJECT_TITLE = "Qase Api Tests";
    private final static String PROJECT_CODE = "QAT";
    private final static int CASE_ID = 1;

    @BeforeTest
    public void createProject() {
        given()
                .body(("{\"title\":\"" + PROJECT_TITLE + "\", \"code\":\"" + PROJECT_CODE + "\"}"))
                .when()
                .log().all()
                .post("v1/project")
                .then()
                .log().all();
    }


    @AfterTest
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
    public void createCase() {

        given()

                .body(("{\"title\":\"New Case 1\",\"description\":\"Test Case for Diploma\"}"))
                .when()
                .log().all()
                .post("/v1/case/" + PROJECT_CODE)
                .then()
                .log().all();
    }

    @Test
    public void getCase() {
        given()

                .body(String.format("{\"code\": \"%s\"}", PROJECT_CODE))
                .when()
                .log().all()
                .get("/v1/case/" + PROJECT_CODE)
                .then()
                .log().all();
    }

    @Test
    public void updateTestCase() {
        Case case1 = Case.builder().setTitle("New Case Title").setStatus(0).setSeverity(1).setPriority(1).setType(2)
                .setLayer(1).setIsFlaky(0).setBehavior(1).setAutomationStatus(2).build();

        given()

                .body(case1, ObjectMapperType.GSON)
                .when().log().all()
                .patch("/v1/case/" + PROJECT_CODE + "/" + CASE_ID)
                .then().log().all()
                .statusCode(200)
                .body("status", equalTo(true));
    }

}