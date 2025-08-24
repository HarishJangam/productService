package com.capstone.springprojecta.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    private long id;
    private Date CreateDate;
    private Date LastUpdateDate;
    private boolean isDeleted;
}
