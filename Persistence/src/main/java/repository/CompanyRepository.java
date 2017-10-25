package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Company;

//@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
