package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QaseProject {
    private String title;
    private String code;
    private String description;

}
