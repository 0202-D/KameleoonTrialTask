package com.kameleoon.dmitriypetrov.kameleoontrialtask.dto.quote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddQuoteRq {
    private long userId;
    private String content;
}
