package com.example.demo.dto.mapper;

import com.example.demo.dto.CompanyRequest;
import com.example.demo.dto.CompanyResponse;
import com.example.demo.entity.Company;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyMapper {
    public CompanyResponse toResponse(Company company) {
        CompanyResponse companyResponse = new CompanyResponse();
        BeanUtils.copyProperties(company, companyResponse);
        return companyResponse;
    }

    public List<CompanyResponse> toResponse(List<Company> employees) {
        return employees.stream().map(this::toResponse).toList();
    }

    public Company toEntity(CompanyRequest companyRequest) {
        Company company = new Company();
        BeanUtils.copyProperties(companyRequest, company);
        return company;
    }
}
