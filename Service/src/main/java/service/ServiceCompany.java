package service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Company;
import repository.CompanyRepository;

@Service
public class ServiceCompany {
    private static Logger LOGGER = Logger.getLogger(ServiceCompany.class.getName());

    private final CompanyRepository dao;

    @Autowired
    public ServiceCompany(CompanyRepository dao) {
        super();
        this.dao = dao;
    }

    @Transactional(readOnly = true)
    public Page<Company> getCompanies(Pageable page) {
        return dao.findAll(page);
    }

    @Transactional(readOnly = true)
    public Company getCompanyById(Long id) {
      if(dao.existsById(id)) {
        return dao.findById(id).get();
      }
      return null;
    }

    @Transactional
    public void setCompany(Company result) {
        dao.save(result);
    }
    @Transactional
    public void updateCompany(Company result) {
        dao.save(result);
    }

    @Transactional
    public void deleteCompany(String ids) {
        String idS[] = ids.split(",");
        for (String id : idS) {
            dao.deleteById(Long.parseLong(id));
        }
    }

}
