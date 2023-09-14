package utils;

import com.github.javafaker.Faker;
import enums.*;
import models.Project;
import models.SharedSteps;
import models.SharedSteps;
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
                .setCode(String.valueOf(faker.number().randomDigit()))
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

    public static TestCase createTestCaseGeneration() {
        return TestCase.builder()
                .setTitle(faker.animal().name())
                .setDescription(faker.book().author())
                .setPreConditions(faker.funnyName().name())
                .setPostConditions(faker.funnyName().name())
                .setStatus(Status.ACTUAL)
                .setSeverity(Severity.CRITICAL)
                .setPriority(Priority.HIGH)
                .setType(Type.FUNCTIONAL)
                .setLayer(Layer.E2E)
                .setIsFlaky(IsFlaky.NO)
                .setBehavior(Behavior.POSITIVE)
                .setAutomationStatus(AutomationStatus.AUTOMATED)
                .setStepAction(faker.friends().character())
                .setData(faker.friends().character())
                .setExpectedResult(faker.friends().character())
                .build();
    }
    public static TestCase createNewTestCaseWithSharedStepsGeneration() {
        return TestCase.builder()
                .setTitle(faker.animal().name())
                .setDescription(faker.book().author())
                .setPreConditions(faker.funnyName().name())
                .setPostConditions(faker.funnyName().name())
                .setStatus(Status.ACTUAL)
                .setSeverity(Severity.CRITICAL)
                .setPriority(Priority.HIGH)
                .setType(Type.FUNCTIONAL)
                .setLayer(Layer.E2E)
                .setIsFlaky(IsFlaky.NO)
                .setBehavior(Behavior.POSITIVE)
                .setAutomationStatus(AutomationStatus.AUTOMATED)
                .build();
    }
    public static SharedSteps addSharedStepsGeneration() {
        return SharedSteps.builder()
                .setTitle(faker.country().name())
                .setStepAction(faker.friends().character())
                .setData(faker.friends().character())
                .setExpectedResult(faker.friends().character())
                .build();
    }
}
