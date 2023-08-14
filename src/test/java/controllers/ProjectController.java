package controllers;

import io.restassured.response.Response;
import models.QaseProject;
import io.restassured.mapper.ObjectMapperType;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;


public class ProjectController extends BaseController {
    public ProjectController() {

        super();
    }

    public void addProject(QaseProject project) {
        given()
                .body(project, ObjectMapperType.GSON)
                .when()
                .log().all()
                .post("/v1/project")
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract().response();

    }
    public Response getProject(String projectCode) {
        return given()
                .when()
                .log().all()
                .get("/v1/project/" + projectCode)
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract().response();
    }

    public Response deleteProject(String projectCode) {
        return given()
                .log().all()
                .when()
                .delete("/v1/project/"+projectCode)
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract().response();
    }
}
