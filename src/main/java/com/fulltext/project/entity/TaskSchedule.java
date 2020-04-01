package com.fulltext.project.entity;

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskSchedule {
    private Long id;
    private String taskName;
    private Date beginDateTime;
    private Date endDateTime;
    private Long operUserId;
    private Date createTime;
    private Date updateTime;

    private String beginDateStr;
    private String endDateStr;
    private String createDateStr;

    public String getBeginDateStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(beginDateTime);
    }

    public String getEndDateStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(endDateTime);
    }

    public String getCreateDateStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(createTime);
    }
}