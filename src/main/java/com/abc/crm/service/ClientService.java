package com.abc.crm.service;

import com.abc.crm.dao.CompanyDao;
import com.abc.crm.service.bo.ClientBo;
import com.abc.crm.controller.dto.req.ClientReqDto;
import com.abc.crm.controller.dto.res.ClientResDto;
import com.abc.crm.dao.ClientDao;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@AllArgsConstructor
public class ClientService {

    private final ClientDao clientDao;
    private final CompanyDao companyDao;

    public Optional<ClientResDto> getOne(Long id) {
        return clientDao.getOne(id)
                .map(ClientResDto::valueOf);
    }

    public Optional<Long> addOne(ClientReqDto clientReqDto, String username) {
        if (isCompanyIdNotExist(clientReqDto.getCompanyId())) {
            log.debug("companyId={} doesn't exist", clientReqDto.getCompanyId());
            return Optional.empty();
        }

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

    private boolean isCompanyIdNotExist(Long companyId) {
        return !companyDao.isIdExist(companyId);
    }

    @Transactional
    public List<Long> addMany(List<ClientReqDto> clientReqDtoList, String username) {

        Timestamp currentTimestamp = Timestamp.from(Instant.now());
        List<ClientBo> clientBoList = clientReqDtoList.stream()
                .map(e -> ClientBo.builder()
                        .companyId(e.getCompanyId())
                        .name(e.getName())
                        .email(e.getEmail())
                        .phone(e.getPhone())
                        .createdBy(username)
                        .createdAt(currentTimestamp)
                        .build()).collect(Collectors.toList());

        return clientDao.addMany(clientBoList);
    }

    public Optional<Long> updateOne(ClientReqDto clientReqDto, String username) {
        if (isCompanyIdNotExist(clientReqDto.getCompanyId())) {
            log.debug("companyId={} doesn't exist", clientReqDto.getCompanyId());
            return Optional.empty();
        }

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
