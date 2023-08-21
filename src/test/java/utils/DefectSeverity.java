package utils;

import lombok.Getter;

@Getter
public enum DefectSeverity {
    MAJOR(3, "major");

    final int intValue;
    final String stringValue;

    DefectSeverity(int intValue, String stringValue){
        this.stringValue = stringValue;
        this.intValue = intValue;
    }
}
