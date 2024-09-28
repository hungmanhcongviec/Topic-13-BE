
package com.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewsDTO {
    private Long id;
    private String comment;
    private int rating;
    private LocalDate dateCreated;
    private UsersDTO user;
}
