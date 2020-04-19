package com.abc.crm.service.bo;

import com.abc.crm.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@SuperBuilder
public class CompanyBo extends BaseBo {

    private String name;
    private String address;

    public static CompanyBo valueOf(Company company) {
        return CompanyBo.builder()
                .id(company.getId())
                .name(company.getName())
                .address(company.getAddress())
                .createdBy(company.getCreatedBy())
                .createdAt(company.getCreatedAt())
                .updatedBy(company.getUpdatedBy())
                .updatedAt(company.getUpdatedAt())
                .build();
    }

}
