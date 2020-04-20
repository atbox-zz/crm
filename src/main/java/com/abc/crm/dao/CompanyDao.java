package com.abc.crm.dao;

import com.abc.crm.entity.Company;
import com.abc.crm.repo.CompanyRepo;
import com.abc.crm.service.bo.CompanyBo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyDao {

    private final CompanyRepo companyRepo;

    public CompanyDao (CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }

    public Optional<CompanyBo> getOne(Long id) {
        return companyRepo.findById(id)
                .map(CompanyBo::valueOf);
    }

    public Optional<Long> addOne(CompanyBo companyBo) {
        Company company = Company.valueOf(companyBo);
        company = companyRepo.save(company);
        return Optional.ofNullable(company.getId());
    }

    public Optional<Long> updateOne(CompanyBo companyBo) {
        return companyRepo.findById(companyBo.getId())
                .map(e -> {
                    e.setName(companyBo.getName());
                    e.setAddress(companyBo.getAddress());
                    e.setUpdatedBy(companyBo.getUpdatedBy());
                    e.setUpdatedAt(companyBo.getUpdatedAt());
                    return e;
                }).map(companyRepo::save)
                .map(Company::getId);
    }

    public boolean deleteOne(Long id) {
        companyRepo.deleteById(id);
        return true;
    }

    public boolean isIdExist(Long id) {
        return companyRepo.existsById(id);
    }
}
