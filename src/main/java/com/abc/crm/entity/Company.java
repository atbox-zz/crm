package com.abc.crm.entity;

import com.abc.crm.service.bo.CompanyBo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@SuperBuilder
@Entity
public class Company extends BaseEntity {

    private String name;
    private String address;

    public static Company valueOf(CompanyBo companyBo) {
        return Company.builder()
                .id(companyBo.getId())
                .name(companyBo.getName())
                .address(companyBo.getAddress())
                .createdBy(companyBo.getCreatedBy())
                .createdAt(companyBo.getCreatedAt())
                .updatedBy(companyBo.getUpdatedBy())
                .updatedAt(companyBo.getUpdatedAt())
                .build();
    }

}
