package com.abc.crm.controller;

import com.abc.crm.controller.dto.req.CompanyReqDto;
import com.abc.crm.controller.dto.res.ResOneDto;
import com.abc.crm.service.CompanyService;
import com.abc.crm.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final UserService userService;
    private final CompanyService companyService;

    public CompanyController(
            UserService userService,
            CompanyService companyService) {
        this.userService = userService;
        this.companyService = companyService;
    }

    @GetMapping("/{id}")
    public ResOneDto getOne(@PathVariable Long id) {
        return companyService.getOne(id)
                .map(ResOneDto::success)
                .orElse(ResOneDto.fail());
    }

    @PostMapping("/add")
    public ResOneDto addOne(@RequestBody CompanyReqDto companyReqDto) {
        String username = userService.getCurrentUsername();
        return companyService.addOne(companyReqDto, username)
                .map(ResOneDto::success)
                .orElse(ResOneDto.fail());
    }

    @PatchMapping("/update")
    public ResOneDto updateOne(@RequestBody CompanyReqDto companyReqDto) {
        String username = userService.getCurrentUsername();
        return companyService.updateOne(companyReqDto, username)
                .map(ResOneDto::success)
                .orElse(ResOneDto.fail());
    }

    @DeleteMapping("/delete")
    public ResOneDto deleteOne(@RequestBody Long id) {
        boolean success = companyService.deleteOne(id);
        if (success) {
            return ResOneDto.success();
        } else {
            return ResOneDto.fail();
        }
    }
}
