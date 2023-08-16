package api_tests;


import controllers.ProjectController;
import controllers.TestCaseController;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.QaseProject;
import org.apache.http.protocol.HTTP;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.PropertyReader;

import static io.restassured.RestAssured.given;

public class BaseApiTest {
    private static final String TOKEN = PropertyReader.getProperty("token");
    private static final String BASE_URL = PropertyReader.getProperty("base_api_url");
    public final static String PROJECT_TITLE = "Qase Api Tests";
    public final static String PROJECT_CODE = "QAT";
    public final static String PROJECT_DESCRIPTION = "Api tests for Diploma";


    @BeforeSuite
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.requestSpecification = given()
                .header("TOKEN", TOKEN)
                .header(HTTP.CONTENT_TYPE, ContentType.JSON);
    }

    @BeforeClass
    public void addProject() {

        QaseProject project = QaseProject.builder()
                .title(PROJECT_TITLE)
                .code(PROJECT_CODE)
                .description(PROJECT_DESCRIPTION)
                .build();
        new ProjectController().addProject(project);

    }

//    @AfterClass
//    public void deleteProject() {
//        new ProjectController().deleteProject(PROJECT_CODE);
//
//
//    }
//}
//
//
}


