package com.kameleoon.dmitriypetrov.kameleoontrialtask.dto.quote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotingRq {
    private Long userId;
    private Long quoteId;

}
