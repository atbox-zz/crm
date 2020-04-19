package com.abc.crm.controller.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CompanyReqDto {

    private Long id;
    private String name;
    private String address;

}
