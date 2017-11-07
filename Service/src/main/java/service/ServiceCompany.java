package service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Company;
import repository.CompanyRepository;

@Service
public class ServiceCompany {
    private static Logger LOGGER = Logger.getLogger(ServiceCompany.class.getName());
    // private final CompanyImpl dao;
    //
    // @Autowired
    // public ServiceCompany(CompanyImpl dao) {
    // super();
    // this.dao = dao;
    // }

    private final CompanyRepository dao;

    @Autowired
    public ServiceCompany(CompanyRepository dao) {
        super();
        this.dao = dao;
    }

    @Transactional(readOnly = true)
    public List<Company> getAllCompanies() {
        return dao.findAll();
    }

    /** Function search company by id in the database.
     * @param id id of the searched company
     * @return company searched */
    @Transactional(readOnly = true)
    public Company getCompany(Long id) {
        return dao.getOne(id);
    }

}
