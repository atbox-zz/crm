package com.abc.crm.service;

import com.abc.crm.controller.dto.req.CompanyReqDto;
import com.abc.crm.controller.dto.res.CompanyResDto;
import com.abc.crm.dao.CompanyDao;
import com.abc.crm.service.bo.CompanyBo;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyDao companyDao;

    public CompanyService(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public Optional<CompanyResDto> getOne(Long id) {
        throw new UnsupportedOperationException();
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
        return companyDao.deleteOne(id);
    }

}
