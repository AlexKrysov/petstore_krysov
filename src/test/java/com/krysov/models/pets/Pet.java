package com.krysov.models.pets;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {
    private Integer id;
    private String name, status;
    private List<String> photoUrls;
    @JsonProperty("tags")
    private List<Tags> tags;
    private Category category;
}
