package com.kameleoon.dmitriypetrov.kameleoontrialtask.dto.quote;

import com.kameleoon.dmitriypetrov.kameleoontrialtask.entity.User;
import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class QuoteRs {
    private long id;
    private String content;
    private Timestamp createData;
    private User author;
    private Long votes;

}
