package api_tests;

import controllers.DefectController;
import controllers.ProjectController;
import models.QaseProject;
import org.testng.annotations.*;

public class BaseApiTest {
    public final static String PROJECT_TITLE = "Qase Api Tests";
    public final static String PROJECT_CODE = "QAT";
    public final static String PROJECT_DESCRIPTION = "Api tests for Diploma";
    protected DefectController defectController;

    @BeforeTest
    public void addProject() {
        QaseProject project = QaseProject.builder()
                .title(PROJECT_TITLE)
                .code(PROJECT_CODE)
                .description(PROJECT_DESCRIPTION)
                .build();
        new ProjectController().addProject(project);
    }

    @AfterTest
    public void deleteProject() {
        new ProjectController().deleteProject(PROJECT_CODE);
    }
}



