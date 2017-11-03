package DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import model.Computer;

public interface ComputerDAO {
    Long getCount(String name);

    Page<Computer> findAll(Pageable page, String name);

    Computer findById(Long id);

    void create(Computer result);

    void update(Computer result);

    void deleteByIds(String ids);
}
