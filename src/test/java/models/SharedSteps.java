package models;

import lombok.Builder;
import lombok.Data;

@Builder(setterPrefix = "set")
@Data
public class SharedSteps {
    private String title;
    private String stepAction;
    private String data;
    private String expectedResult;

    public static class SharedStepsBuilder {
        public SharedStepsBuilder() {

        }
    }
}