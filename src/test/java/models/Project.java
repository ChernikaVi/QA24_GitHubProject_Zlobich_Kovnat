package models;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder(setterPrefix = "set")
@Data
public class Project {

    private String name;
    @EqualsAndHashCode.Exclude
    private String code;
    private String description;
}
