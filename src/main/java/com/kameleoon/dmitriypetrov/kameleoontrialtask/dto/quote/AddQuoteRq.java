package com.kameleoon.dmitriypetrov.kameleoontrialtask.dto.quote;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddQuoteRq {
    @NotNull(message = "User ID cannot be null")
    private long userId;
    
    @NotBlank(message = "Content cannot be empty")
    @Size(min = 1, max = 1000, message = "Content must be between 1 and 1000 characters")
    private String content;
}
