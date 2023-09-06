package models;

import com.google.gson.annotations.SerializedName;
import enums.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

@Builder(setterPrefix = "set")
@Data
public class TestCase {

    private String title;
    private String description;
    private String preConditions;
    private String postConditions;
    @EqualsAndHashCode.Exclude
    private String stepAction;
    @EqualsAndHashCode.Exclude
    private String data;
    @EqualsAndHashCode.Exclude
    private String expectedResult;
    private Status status;
    private Severity severity;
    private Priority priority;
    private Type type;
    private Layer layer;
    @SerializedName(value = "is_flaky")
    private IsFlaky isFlaky;
    private Behavior behavior;
    @SerializedName(value = "automation")
    private AutomationStatus automationStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestCase aTestCase = (TestCase) o;
        return Objects.equals(title, aTestCase.title)
                && Objects.equals(description, aTestCase.description)
                && Objects.equals(preConditions, aTestCase.preConditions)
                && Objects.equals(postConditions, aTestCase.postConditions)
                && status == aTestCase.status
                && severity == aTestCase.severity
                && priority == aTestCase.priority
                && type == aTestCase.type
                && layer == aTestCase.layer
                && isFlaky == aTestCase.isFlaky
                && behavior == aTestCase.behavior
                && automationStatus == aTestCase.automationStatus;
    }

    public static class TestCaseBuilder {
        public TestCaseBuilder() {

        }
    }
}