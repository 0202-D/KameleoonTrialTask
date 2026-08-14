package com.kameleoon.dmitriypetrov.kameleoontrialtask.dto.quote;

import com.kameleoon.dmitriypetrov.kameleoontrialtask.entity.User;
import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuoteRs {
    private long id;
    private String content;
    private Timestamp createDate;
    private User author;
    private Long votes;

}
