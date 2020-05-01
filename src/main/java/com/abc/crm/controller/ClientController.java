package com.abc.crm.controller;

import com.abc.crm.controller.dto.req.ClientReqDto;
import com.abc.crm.controller.dto.res.ResOneDto;
import com.abc.crm.service.ClientService;
import com.abc.crm.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController {

    private final UserService userService;
    private final ClientService clientService;

    @GetMapping("/view/{id}")
    public ResOneDto getOne(@PathVariable Long id) {
        return clientService.getOne(id)
                .map(ResOneDto::success)
                .orElse(ResOneDto.empty());
    }

    @PostMapping("/add")
    public ResOneDto addOne(@RequestBody ClientReqDto clientReqDto) {
        var username = userService.getCurrentUsername();
        return clientService.addOne(clientReqDto, username)
                .map(ResOneDto::success)
                .orElse(ResOneDto.fail());
    }

    @PostMapping("/add/many")
    public ResOneDto addMany(@RequestBody List<ClientReqDto> clientReqDtoList) {
        var username = userService.getCurrentUsername();
        var idList = clientService.addMany(clientReqDtoList, username);
        return ResOneDto.success(idList);
    }

    @PatchMapping("/update")
    public ResOneDto updateOne(@RequestBody ClientReqDto clientReqDto) {
        var username = userService.getCurrentUsername();
        return clientService.updateOne(clientReqDto, username)
                .map(ResOneDto::success)
                .orElse(ResOneDto.fail());
    }

    @DeleteMapping("/delete/{id}")
    public ResOneDto deleteOne(@PathVariable Long id) {
        var success = clientService.deleteOne(id);
        if (success) {
            return ResOneDto.success();
        } else {
            return ResOneDto.fail();
        }
    }

}
