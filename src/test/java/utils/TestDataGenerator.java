package utils;

import com.github.javafaker.Faker;
import enums.*;
import models.Project;
import models.Suite;
import models.TestCase;

public class TestDataGenerator {
    static Faker faker = new Faker();

    public static Project positiveAddProjectGeneration() {
        return Project.builder()
                .setName(faker.animal().name() + faker.number().randomDigit())
                .setCode(String.valueOf(faker.number().randomDigit()))
                .setDescription(faker.currency().name() + faker.number().randomDigit())
                .build();
    }

    public static Project negativeAddProjectGeneration() {
        return Project.builder()
                .setName("")
                .setCode("5555")
                .setDescription(faker.currency().name() + faker.number().randomDigit())
                .build();
    }

    public static Suite addSuiteGeneration() {
        return Suite.builder()
                .setSuiteTitle(faker.currency().name() + faker.number().randomDigit())
                .setSuiteDescription(faker.app().version())
                .setPreconditions(faker.country().name() + faker.number().randomDigit())
                .build();
    }

    public static Suite editSuiteGeneration() {
        return Suite.builder()
                .setSuiteTitle(faker.country().name() + faker.number().randomDigit())
                .setSuiteDescription(faker.app().version())
                .setPreconditions(faker.app().version())
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
