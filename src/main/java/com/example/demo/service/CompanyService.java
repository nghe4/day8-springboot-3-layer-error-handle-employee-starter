package com.example.demo.service;

import com.example.demo.entity.Company;
import com.example.demo.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void clearCompanies() {
        companyRepository.clearCompanies();
    }

    public List<Company> getCompanies(Integer page, Integer size) {
        return companyRepository.getCompanies(page, size);
    }

    public Company createCompany(Company company) {
        return companyRepository.createCompany(company);
    }

    public Company getCompanyById(int id) {
        return companyRepository.getCompanyById(id);
    }

    public Company updateCompany(int id, Company updatedCompany) {
        return companyRepository.updateCompany(id, updatedCompany);
    }

    public void deleteCompany(int id) {
        companyRepository.deleteCompany(id);
    }
}
