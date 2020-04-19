package com.abc.crm.controller.dto.res;

import com.abc.crm.controller.dto.BaseDto;
import com.abc.crm.service.bo.CompanyBo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CompanyResDto extends BaseDto {

    private String name;
    private String address;

    public static CompanyResDto valueOf(CompanyBo companyBo) {
        return CompanyResDto.builder()
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
