package repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Computer;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {

    long countByNameContainingOrCompanyNameContaining(String name, String companyName);

    Page<Computer> findAllComputersByNameContainingOrCompanyNameContaining(Pageable page, String name, String companyName);

}
