package models;

import lombok.Builder;
import lombok.Data;

@Builder(setterPrefix = "set")
@Data
public class SharedSteps {
    private String title;

}

