package com.abc.crm.service;

import com.abc.crm.controller.dto.req.CompanyReqDto;
import com.abc.crm.controller.dto.res.CompanyResDto;
import com.abc.crm.dao.ClientDao;
import com.abc.crm.dao.CompanyDao;
import com.abc.crm.service.bo.CompanyBo;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Log4j2
@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyDao companyDao;
    private final ClientDao clientDao;

    public Optional<CompanyResDto> getOne(Long id) {
        return companyDao.getOne(id)
                .map(CompanyResDto::valueOf);
    }

    public Optional<Long> addOne(CompanyReqDto companyReqDto, String username) {
        CompanyBo companyBo = CompanyBo.builder()
                .name(companyReqDto.getName())
                .address(companyReqDto.getAddress())
                .createdBy(username)
                .createdAt(Timestamp.from(Instant.now()))
                .build();

        return companyDao.addOne(companyBo);
    }

    public Optional<Long> updateOne(CompanyReqDto companyReqDto, String username) {
        CompanyBo companyBo = CompanyBo.builder()
                .id(companyReqDto.getId())
                .name(companyReqDto.getName())
                .address(companyReqDto.getAddress())
                .updatedBy(username)
                .updatedAt(Timestamp.from(Instant.now()))
                .build();

        return companyDao.updateOne(companyBo);
    }

    public boolean deleteOne(Long id) {
        if (clientDao.getByCompanyId(id).size() > 0) {
            log.debug("id={} is in use.", id);
            return false;
        }
        return companyDao.deleteOne(id);
    }

}
