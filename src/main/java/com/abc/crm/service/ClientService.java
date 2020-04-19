package com.abc.crm.service;

import com.abc.crm.service.bo.ClientBo;
import com.abc.crm.controller.dto.req.ClientReqDto;
import com.abc.crm.controller.dto.res.ClientResDto;
import com.abc.crm.dao.ClientDao;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientDao clientDao;

    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public Optional<ClientResDto> getOne(Long id) {
        return clientDao.getOne(id)
                .map(ClientResDto::valueOf);
    }

    public Optional<Long> addOne(ClientReqDto clientReqDto, String username) {
        ClientBo clientBo = ClientBo.builder()
                .companyId(clientReqDto.getCompanyId())
                .name(clientReqDto.getName())
                .email(clientReqDto.getEmail())
                .phone(clientReqDto.getPhone())
                .createdBy(username)
                .createdAt(Timestamp.from(Instant.now()))
                .build();

        return clientDao.addOne(clientBo);
    }

    public Optional<Long> updateOne(ClientReqDto clientReqDto, String username) {
        ClientBo clientBo = ClientBo.builder()
                .id(clientReqDto.getId())
                .companyId(clientReqDto.getCompanyId())
                .name(clientReqDto.getName())
                .email(clientReqDto.getEmail())
                .phone(clientReqDto.getPhone())
                .updatedBy(username)
                .updatedAt(Timestamp.from(Instant.now()))
                .build();

        return clientDao.updateOne(clientBo);
    }

    public boolean deleteOne(Long id) {
        return clientDao.deleteOne(id);
    }
}
