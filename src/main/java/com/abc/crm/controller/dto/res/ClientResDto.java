package com.abc.crm.controller.dto.res;

import com.abc.crm.controller.dto.BaseDto;
import com.abc.crm.service.bo.ClientBo;
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
public class ClientResDto extends BaseDto {

    private Long companyId;
    private String name;
    private String email;
    private String phone;

    public static ClientResDto valueOf(ClientBo clientBo) {
        return ClientResDto.builder()
                .id(clientBo.getId())
                .companyId(clientBo.getCompanyId())
                .name(clientBo.getName())
                .email(clientBo.getEmail())
                .phone(clientBo.getPhone())
                .createdBy(clientBo.getCreatedBy())
                .createdAt(clientBo.getCreatedAt())
                .updatedBy(clientBo.getUpdatedBy())
                .updatedAt(clientBo.getUpdatedAt())
                .build();
    }
}
