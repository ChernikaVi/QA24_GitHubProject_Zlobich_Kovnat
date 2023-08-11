package controllers;

import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Case;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class TestCaseController extends BaseController {
    public TestCaseController() {

        super();
    }

    public void addTestCase(String projectCode, Case testCase) {
        given()
                .body(testCase, ObjectMapperType.GSON)
                .when()
                .log().all()
                .post("/v1/case/" + projectCode)
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract().response();
    }

    public Response getTestCase(String projectCode) {
        return given()
                .when()
                .log().all()
                .get("/v1/case/" + projectCode)
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract().response();
    }


    public Response updateTestCase(String projectCode, int caseId, String newTitle) {
        return given()
                .when()
                .body(String.format("{\"title\":\"%s\"}", newTitle))
                .patch("v1/case/" + projectCode + "/" + caseId)
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract().response();
    }

    public Response deleteTestCase(String projectCode, int caseId) {
        return given()
                .log().all()
                .when()
                .delete("/v1/case/" + projectCode + "/" + caseId)
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract().response();
    }

}