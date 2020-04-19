package com.abc.crm.controller.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ClientReqDto {

    private Long id;
    private Long companyId;
    private String name;
    private String email;
    private String phone;

}
