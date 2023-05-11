package com.kakao.jPanda.chat.domain;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatReportDto {
    private Long reportNo;
    private String blackId;
    private String reportId;
    private String issue;
    private Timestamp reportDate;
    private Long chatNo;
}
