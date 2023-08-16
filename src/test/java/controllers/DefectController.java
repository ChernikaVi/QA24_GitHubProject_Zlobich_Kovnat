package controllers;

import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Defect;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class DefectController extends BaseController {
    public DefectController() {

        super();
    }

    public Response addDefect(String projectCode, Defect defect) {
        return  given()
                .body(defect, ObjectMapperType.GSON)
                .when()
                .log().all()
                .post("/v1/defect/" + projectCode)
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract().response();
    }

    public Response getDefects(String projectCode) {
        return given()
                .when()
                .log().all()
                .get("/v1/defect/" + projectCode)
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract().response();
    }

    public Response updateDefect(String projectCode, int defectId, String newTitle) {
        return given()
                .when()
                .body(String.format("{\"title\":\"%s\"}", newTitle))
                .patch("v1/defect/" + projectCode + "/" + defectId)
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract().response();
    }

    public Response deleteDefect(String projectCode, int caseId) {
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



