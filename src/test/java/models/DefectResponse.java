package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DefectResponse {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("actual_result")
    private String actual_result;
    @SerializedName("severity")
    private String severity;
}
