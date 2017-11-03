package DAO;

import java.util.List;

import model.Company;

public interface CompanyDAO {
    List<Company> findAll();

    Company getOne(Long id);
}
