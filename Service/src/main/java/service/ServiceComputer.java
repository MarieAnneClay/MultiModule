package service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DAOImpl.ComputerImpl;
import model.Computer;

/** Class of service for Computer DAO. */
@Service
public class ServiceComputer {
    private static Logger LOGGER = Logger.getLogger(ServiceCompany.class.getName());
    private final ComputerImpl computerImpl;

    @Autowired
    public ServiceComputer(ComputerImpl computerImpl) {
        super();
        this.computerImpl = computerImpl;
    }

    @Transactional(readOnly = true)
    public Long getCount(String name) {
        return computerImpl.getCount(name);
    }

    /** Function to get the computer.
     * @param name of the computer searched
     * @return A list of all the Computer in the database */
    @Transactional(readOnly = true)
    public Page<Computer> getComputerByName(Pageable page, String name) {
        return computerImpl.findAll(page, name);
    }

    @Transactional(readOnly = true)
    public Computer getComputerById(Long id) {
        return computerImpl.findById(id);
    }

    /** Function which call the createComputer to create a computer in the database
     * with a SQL request.
     * @param computer computer to add to the database */
    @Transactional
    public void setComputer(Computer result) {
        computerImpl.create(result);
    }

    /** Function which call the updateComputer to update a computer in the database
     * with a SQL request.
     * @param computer computer to update to the database */
    @Transactional
    public void updateComputer(Computer result) {
        computerImpl.update(result);
    }

    /** Function which call the deleteComputer to delete a computer in the database
     * with a SQL request.
     * @param id id of the computer to delete in the database */
    @Transactional
    public void deleteComputer(String ids) {
        computerImpl.deleteByIds(ids);

    }

}
