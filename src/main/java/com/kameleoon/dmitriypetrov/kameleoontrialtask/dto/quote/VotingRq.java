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
public class VotingRq {
    @NotNull(message = "User ID cannot be null")
    private Long userId;
    
    @NotNull(message = "Quote ID cannot be null")
    private Long quoteId;
}
