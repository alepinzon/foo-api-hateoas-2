package io.foo.hateoas.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

@Getter
@Setter
@JsonPropertyOrder("id")
public class StudentResource extends ResourceSupport {

    @JsonProperty("id")
    private Integer studentId;
    private String name;
    private String passportNumber;
}
