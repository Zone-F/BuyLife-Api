package com.agileboot.domain.buylife.checkInTask.dto;

import com.agileboot.domain.buylife.checkInTask.db.TaskEntity;

import lombok.Data;

import java.util.Date;

@Data
public class TaskDTO {

    public TaskDTO(TaskEntity entity) {
        if (entity != null) {
            this.id = entity.getId() + "";
            this.title = entity.getTitle();
            this.description = entity.getDescription();
            this.startDate = entity.getStartDate();
            this.status = entity.getStatus();
            this.createTime = entity.getCreateTime();
            this.repeatCycle = entity.getRepeatCycle();
        }
    }

    private String id;

    private String title;

    private String description;

    private Integer point;

    private Integer status;

    private Date startDate;

    private Integer repeatCycle;

    private Date createTime;

}
