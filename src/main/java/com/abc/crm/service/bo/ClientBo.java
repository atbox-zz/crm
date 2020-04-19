package com.abc.crm.service.bo;

import com.abc.crm.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@SuperBuilder
public class ClientBo extends BaseBo {

    private Long companyId;
    private String name;
    private String email;
    private String phone;

    public static ClientBo valueOf(Client client) {
        return ClientBo.builder()
                .id(client.getId())
                .companyId(client.getCompanyId())
                .name(client.getName())
                .email(client.getEmail())
                .phone(client.getPhone())
                .createdBy(client.getCreatedBy())
                .createdAt(client.getCreatedAt())
                .updatedBy(client.getUpdatedBy())
                .updatedAt(client.getUpdatedAt())
                .build();

    }

}
