package com.abc.crm.controller.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyReqDto {

    private Long id;
    private String name;
    private String address;

}
