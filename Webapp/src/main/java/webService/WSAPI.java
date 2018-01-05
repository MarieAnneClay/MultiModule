package webService;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.Company;
import model.Computer;
import service.ServiceCompany;
import service.ServiceComputer;
import util.PageDTO;

@RestController
public class WSAPI {
  private final ServiceComputer serviceComputer;
  private final ServiceCompany serviceCompany;
  private static Logger LOGGER = Logger.getLogger(WSAPI.class.getName());

  @Autowired
  public WSAPI(ServiceComputer serviceComputer, ServiceCompany serviceCompany) {
    super();
    this.serviceComputer = serviceComputer;
    this.serviceCompany = serviceCompany;
  }
  
  /* COMPUTER */
  @GetMapping(value = { "/api/computers" }, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getAllComputers() {
    PageDTO page = new PageDTO(serviceComputer
      .getComputerByName(PageRequest.of(0, 20, Sort.Direction.ASC, "id"), ""),"", "introduced", Sort.Direction.ASC);
    return new ResponseEntity(page, HttpStatus.OK);
  }

  @GetMapping(value = "/api/computers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Computer> getComputer(@PathVariable("id") Long id) {
    Computer computer = serviceComputer.getComputerById(id);
    return new ResponseEntity<Computer>(computer, HttpStatus.OK);
  }

  @PostMapping(value = "/api/computers", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity postComputer(@RequestBody Computer computer) {
    System.out.println("coucou");
    serviceComputer.setComputer(computer);
    System.out.println(computer);
    return new ResponseEntity(computer, HttpStatus.CREATED);
  }
  
  @PutMapping("/api/computers/{id}")
  public ResponseEntity<?> putComputer(@PathVariable("id") Long id, @RequestBody Computer computer) {
    serviceComputer.updateComputer(computer);
    return ResponseEntity.ok(null);
  }
  
  @DeleteMapping("/api/computers/{id}")
  public ResponseEntity<Object> deleteComputer(@PathVariable String id) {
    System.out.println(id);
    serviceComputer.deleteComputer(id);
    return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
  }
  
  /* COMPANY */
  @GetMapping(value = { "/api/companies" }, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getAllCompanies() {
    PageDTO page = new PageDTO(serviceCompany
        .getCompanies(PageRequest.of(0, 20, Sort.Direction.ASC, "id")),null, "id", Sort.Direction.ASC);
    return new ResponseEntity(page, HttpStatus.OK);
  }

  @GetMapping(value = "/api/companies/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Company> getCompany(@PathVariable("id") Long id) {
    Company company = serviceCompany.getCompanyById(id);
    return new ResponseEntity<Company>(company, HttpStatus.OK);
  }

  @PostMapping(value = "/api/companies", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity postCompany(@RequestBody Company company) {
    serviceCompany.setCompany(company);
    return new ResponseEntity(company, HttpStatus.CREATED);
  }
  
  @PutMapping("/api/companies/{id}")
  public ResponseEntity putCompany(@PathVariable("id") Long id, @RequestBody Company company) {
    serviceCompany.updateCompany(company);
    return ResponseEntity.ok(null);
  }
  
  @DeleteMapping("/api/companies/{id}")
  public ResponseEntity deleteCompany(@PathVariable String id) {
    serviceCompany.deleteCompany(id);
    return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
  }

}