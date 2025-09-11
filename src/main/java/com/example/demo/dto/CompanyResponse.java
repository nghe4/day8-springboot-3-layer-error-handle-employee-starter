package com.example.demo.dto;

public class CompanyResponse {
    private Integer id;
    private String name;

    public CompanyResponse(Integer id, String name, Integer age, String gender) {
        this.id = id;
        this.name = name;
    }

    public CompanyResponse() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
