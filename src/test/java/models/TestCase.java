package models;

import com.google.gson.annotations.SerializedName;
import enums.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder(setterPrefix = "set")
@Data
public class TestCase {

    private String title;
    private String description;
    private String preConditions;
    private String postConditions;
    @EqualsAndHashCode.Exclude
    private String parameterTitle;
    @EqualsAndHashCode.Exclude
    private String parameterValues;
    @EqualsAndHashCode.Exclude
    private String stepAction;
    @EqualsAndHashCode.Exclude
    private String data;
    @EqualsAndHashCode.Exclude
    private String expectedResult;
    @EqualsAndHashCode.Exclude
    private String suite;
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
}