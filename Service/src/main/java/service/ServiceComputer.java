package service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Computer;
import repository.ComputerRepository;

/** Class of service for Computer DAO. */
@Service
public class ServiceComputer {
    private static Logger LOGGER = Logger.getLogger(ServiceCompany.class.getName());
    // private final ComputerImpl dao;
    //
    // @Autowired
    // public ServiceComputer(ComputerImpl dao) {
    // super();
    // this.dao = dao;
    // }

    private final ComputerRepository dao;

    @Autowired
    public ServiceComputer(ComputerRepository dao) {
        super();
        this.dao = dao;
    }

    @Transactional(readOnly = true)
    public Long getCount(String name) {
        return dao.countByNameContainingOrCompanyNameContaining(name, name);
        // return dao.getCount(name);
    }

    /** Function to get the computer.
     * @param name of the computer searched
     * @return A list of all the Computer in the database */
    @Transactional(readOnly = true)
    public Page<Computer> getComputerByName(Pageable page, String name) {
        return dao.findAllComputersByNameContainingOrCompanyNameContaining(page, name, name);
    }

    @Transactional(readOnly = true)
    public Computer getComputerById(Long id) {
        return dao.findById(id).get();
    }

    /** Function which call the createComputer to create a computer in the database
     * with a SQL request.
     * @param computer computer to add to the database */
    @Transactional
    public void setComputer(Computer result) {
        dao.save(result);
        // dao.create(result);
    }

    /** Function which call the updateComputer to update a computer in the database
     * with a SQL request.
     * @param computer computer to update to the database */
    @Transactional
    public void updateComputer(Computer result) {
        dao.save(result);
        // dao.update(result);
    }

    /** Function which call the deleteComputer to delete a computer in the database
     * with a SQL request.
     * @param id id of the computer to delete in the database */
    @Transactional
    public void deleteComputer(String ids) {
        String idS[] = ids.split(",");
        for (String id : idS) {
            dao.deleteById(Long.getLong(id));
        }

        // dao.deleteByIds(ids);

    }

}
