package com.example.demo.repository;

import com.example.demo.entity.Company;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Repository
public class CompanyRepository {
    private final List<Company> companies = new ArrayList<>();

    public CompanyRepository() {
        companies.add(new Company(1, "Spring"));
        companies.add(new Company(2, "Autumn"));
        companies.add(new Company(3, "Winter"));
        companies.add(new Company(4, "Summer"));
    }

    public void clearCompanies() {
        companies.clear();
    }

    public List<Company> getCompanies(Integer page, Integer size) {
        Stream<Company> stream = companies.stream();
        if (page != null && size != null) {
            stream = stream.skip((long) (page - 1) * size).limit(size);
        }
        return stream.toList();
    }
}
