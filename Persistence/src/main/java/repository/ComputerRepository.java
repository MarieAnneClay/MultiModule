package repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import model.Computer;

public interface ComputerRepository extends JpaRepository<Computer, Long> {

    long countByNameContainingOrCompanyNameContaining(String name, String companyName);

    Page<Computer> findAllComputersByNameContainingOrCompanyNameContaining(Pageable page, String name, String companyName); // TODO sort+limit

}
