package service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DAOImpl.CompanyImpl;
import model.Company;

@Service
public class ServiceCompany {
    private static Logger LOGGER = Logger.getLogger(ServiceCompany.class.getName());
    private final CompanyImpl companyImpl;

    @Autowired
    public ServiceCompany(CompanyImpl companyImpl) {
        super();
        this.companyImpl = companyImpl;
    }

    @Transactional(readOnly = true)
    public List<Company> getAllCompanies() {
        return companyImpl.findAll();
    }

    /** Function search company by id in the database.
     * @param id id of the searched company
     * @return company searched */
    @Transactional(readOnly = true)
    public Company getCompany(Long id) {
        return companyImpl.getOne(id);
    }

}
