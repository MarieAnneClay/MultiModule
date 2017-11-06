package webService;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.Company;
import model.Computer;
import service.ServiceCompany;
import service.ServiceComputer;
import util.Pagination;

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

    // @RequestMapping(value = { "/wsdashboard" }, method = RequestMethod.GET,
    // produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<List<Computer>> listOfComputers() {
    // return new ResponseEntity<List<Computer>>(serviceComputer.findAll(),
    // HttpStatus.OK);
    // }

    @GetMapping("/wsdashboard/getcomputer/{id}")
    public ResponseEntity<Computer> getComputer(@PathVariable("id") Long id) {

        Computer computer = serviceComputer.getComputerById(id);
        if (computer == null) {
            return new ResponseEntity("No computer found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Computer>(computer, HttpStatus.OK);
    }

    // http://localhost:8080/Webapp/wsdashboard/page/0/search/
    // /numberOfComputerByPage/3/sort/name/order/ASC
    @RequestMapping(value = {
            "/wsdashboard/page/{currentPage}/search/{search}/numberOfComputerByPage/{numberOfComputerByPage}/sort/{sort}/order/{order}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Computer>> listOfComputers(ModelMap map, @PathVariable(value = "search") String search, @PathVariable(value = "numberOfComputerByPage") int numberOfComputerByPage,
            @PathVariable(value = "currentPage") int currentPage, @PathVariable(value = "sort") String sort, @PathVariable(value = "order") String order) {
        if (search == " ") {
            search = "";
        }
        return new ResponseEntity<List<Computer>>(serviceComputer
                .getComputerByName(PageRequest.of(currentPage, numberOfComputerByPage, Pagination.getOrder(sort).equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sort), search).getContent(),
                HttpStatus.OK);
    }

    @RequestMapping(value = { "/wsdashboard/companies" }, method = RequestMethod.GET)
    public ResponseEntity<List<Company>> listOfCompanies() {
        return new ResponseEntity<List<Company>>(serviceCompany.getAllCompanies(), HttpStatus.OK);
    }

    @RequestMapping(value = { "/wsdashboard/count/{search}" }, method = RequestMethod.GET)
    public ResponseEntity<Long> countOfComputers(ModelMap map, @PathVariable(value = "search") String search) {
        if (search == " ") {
            search = "";
        }

        return new ResponseEntity<Long>(serviceComputer.getCount(search), HttpStatus.OK);
    }

    @DeleteMapping("/wsdashboard/delete/{id}")
    public ResponseEntity deleteComputer(@PathVariable String id) {
        serviceComputer.deleteComputer(id);
        return ResponseEntity.ok(null);
    }

    @PostMapping(value = "/wsdashboard/addcomputer")
    public ResponseEntity createComputer(@RequestBody Computer computer) {

        serviceComputer.setComputer(computer);

        return new ResponseEntity(computer, HttpStatus.OK);
    }

    @PutMapping("/wsdashboard/updatecomputer")
    public ResponseEntity updateComputer(@RequestBody Computer computer) {

        serviceComputer.updateComputer(computer);

        return new ResponseEntity(computer, HttpStatus.OK);
    }

}