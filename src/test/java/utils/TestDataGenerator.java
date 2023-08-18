package utils;
import enums.*;
import models.Project;
import models.Suite;
import models.TestCase;

public class TestDataGenerator {

    public static Project positiveAddProjectGeneration() {
        return Project.builder()
                .setName("My first project")
                .setCode("5555")
                .setDescription("There are my test cases")
                .build();
    }

    public static Project negativeAddProjectGeneration() {
        return Project.builder()
                .setName("")
                .setCode("5555")
                .setDescription("There are my test cases")
                .build();
    }

    public static Suite dddSuiteGeneration() {
        return Suite.builder()
                .setSuiteTitle("My suite")
                .setSuiteDescription("Creating new suite")
                .setPreconditions("Open projects page")
                .build();
    }

    public static Suite editSuiteGeneration() {
        return Suite.builder()
                .setSuiteTitle("My suite 1")
                .setSuiteDescription("Creating new suite")
                .setPreconditions("Open projects page")
                .build();
    }

    public static TestCase createTestCaseGeneration() {
        return TestCase.builder()
                .setTitle("Authorization")
                .setDescription("We can authorize on page qase.io")
                .setPreConditions("Pre-conditions")
                .setPostConditions("Post-conditions")
                .setStatus(Status.ACTUAL)
                .setSeverity(Severity.CRITICAL)
                .setPriority(Priority.HIGH)
                .setType(Type.FUNCTIONAL)
                .setLayer(Layer.E2E)
                .setIsFlaky(IsFlaky.NO)
                .setBehavior(Behavior.POSITIVE)
                .setAutomationStatus(AutomationStatus.AUTOMATED)
                .setStepAction("Step Action")
                .setData("Data")
                .setExpectedResult("Expected result")
                .build();
    }
}
