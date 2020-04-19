package com.abc.crm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
class BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String createdBy;
    private Timestamp createdAt;
    private String updatedBy;
    private Timestamp updatedAt;

}
