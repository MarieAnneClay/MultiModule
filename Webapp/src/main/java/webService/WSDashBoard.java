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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.Computer;
import service.ServiceCompany;
import service.ServiceComputer;
import util.Page;

//@Controller
@RestController
public class WSDashBoard {
    private final ServiceComputer serviceComputer;
    private static Logger LOGGER = Logger.getLogger(WSDashBoard.class.getName());

    @Autowired
    public WSDashBoard(ServiceComputer serviceComputer, ServiceCompany serviceCompany) {
        super();
        this.serviceComputer = serviceComputer;
    }

    // @SuppressWarnings("unchecked")
    // @RequestMapping(value = { "/wsdashboard" }, method = RequestMethod.GET,
    // produces = MediaType.APPLICATION_JSON_VALUE)
    // public List<Computer> listOfComputers() {
    //
    // return serviceComputer.findAll();
    // }

    @RequestMapping(value = { "/wsdashboard" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Computer>> listOfComputers() {
        return new ResponseEntity<List<Computer>>(serviceComputer.findAll(), HttpStatus.OK);
    }

    // @GetMapping("/wsdashboard/{id}")
    // public ResponseEntity<Computer> getComputer(@PathVariable("id") Long id) {
    //
    // Computer computer = serviceComputer.getOne(id);
    // if (computer == null) {
    // return new ResponseEntity("No computer found for ID " + id,
    // HttpStatus.NOT_FOUND);
    // }
    // return new ResponseEntity<Computer>(computer, HttpStatus.OK);
    // }

    // http://localhost:8080/Webapp/wsdashboard/page/0/search/
    // /numberOfComputerByPage/3/sort/name/order/ASC
    @RequestMapping(value = {
            "/wsdashboard/page/{currentPage}/search/{search}/numberOfComputerByPage/{numberOfComputerByPage}/sort/{sort}/order/{order}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Computer>> listOfComputers(ModelMap map, @PathVariable(value = "search") String search, @PathVariable(value = "numberOfComputerByPage") int numberOfComputerByPage,
            @PathVariable(value = "currentPage") int currentPage, @PathVariable(value = "sort") String sort, @PathVariable(value = "order") String order) {
        return new ResponseEntity<List<Computer>>(serviceComputer
                .getComputerByName(PageRequest.of(currentPage, numberOfComputerByPage, Page.getOrder(sort).equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sort), search).getContent(),
                HttpStatus.OK);
    }

    @RequestMapping(value = { "/wsdashboard/count/{search}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> countOfComputers(ModelMap map, @PathVariable(value = "search") String search) {
        return new ResponseEntity<Long>(serviceComputer.getCount(search), HttpStatus.OK);
    }

    @DeleteMapping("/wsdashboard/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable String id) {
        serviceComputer.deleteComputer(id);
        return ResponseEntity.ok(null);
    }

}