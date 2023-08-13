package models;

import lombok.Builder;
import lombok.Data;

@Builder(setterPrefix = "set")
@Data
public class Suite {

    private String suiteTitle;
    private String suiteDescription;
    private String preconditions;
}
