package utils;
import models.Project;
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
                .setName("My first project")
                .setCode("5545")
                .setDescription("There are my test cases")
                .build();
    }
}
