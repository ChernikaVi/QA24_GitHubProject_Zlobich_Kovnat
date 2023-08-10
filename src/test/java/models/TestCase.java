package models;

import enums.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder(setterPrefix = "set")
@Data
public class TestCase {
    @EqualsAndHashCode.Exclude
    private String title;
    @EqualsAndHashCode.Exclude
    private String description;
    @EqualsAndHashCode.Exclude
    private String preConditions;
    @EqualsAndHashCode.Exclude
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
    @EqualsAndHashCode.Exclude
    private Status status;
    @EqualsAndHashCode.Exclude
    private Severity severity;
    @EqualsAndHashCode.Exclude
    private Priority priority;
    @EqualsAndHashCode.Exclude
    private Type type;
    @EqualsAndHashCode.Exclude
    private Layer layer;
    @EqualsAndHashCode.Exclude
    private IsFlaky isFlaky;
    @EqualsAndHashCode.Exclude
    private Behavior behavior;
    @EqualsAndHashCode.Exclude
    private AutomationStatus automationStatus;
}