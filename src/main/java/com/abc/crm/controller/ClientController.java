package com.abc.crm.controller;

import com.abc.crm.controller.dto.res.ResOneDto;
import com.abc.crm.controller.dto.req.ClientReqDto;
import com.abc.crm.service.ClientService;
import com.abc.crm.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final UserService userService;
    private final ClientService clientService;

    public ClientController(
            UserService userService,
            ClientService clientService) {
        this.userService = userService;
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public ResOneDto getOne(@PathVariable Long id) {
        return clientService.getOne(id)
                .map(ResOneDto::success)
                .orElse(ResOneDto.empty());
    }

    @PostMapping("/add")
    public ResOneDto addOne(@RequestBody ClientReqDto clientReqDto) {
        String username = userService.getCurrentUsername();
        return clientService.addOne(clientReqDto, username)
                .map(ResOneDto::success)
                .orElse(ResOneDto.fail());
    }

    @PatchMapping("/update")
    public ResOneDto updateOne(@RequestBody ClientReqDto clientReqDto) {
        String username = userService.getCurrentUsername();
        return clientService.updateOne(clientReqDto, username)
                .map(ResOneDto::success)
                .orElse(ResOneDto.fail());
    }

    @DeleteMapping("/delete")
    public ResOneDto deleteOne(@RequestBody Long id) {
        boolean success = clientService.deleteOne(id);
        if (success) {
            return ResOneDto.success();
        } else {
            return ResOneDto.fail();
        }
    }

}