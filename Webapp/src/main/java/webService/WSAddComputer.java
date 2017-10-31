package webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Computer;
import service.ServiceCompany;
import service.ServiceComputer;

// @Controller
@RestController
@RequestMapping("/wsaddcomputer")
public class WSAddComputer {
    private final ServiceComputer serviceComputer;

    @Autowired
    public WSAddComputer(ServiceComputer serviceComputer, ServiceCompany serviceCompany) {
        super();
        this.serviceComputer = serviceComputer;
    }

    // private static final String VIEW = "AddComputer";
    // private static final String VIEW_HOME = "dashboard";
    //
    // @RequestMapping(method = RequestMethod.GET)
    // public String listOfCompanies(ModelMap model) throws ServletException {
    // List<Company> companies = serviceCompany.getAllCompanies();
    // model.addAttribute("companies", companies);
    // model.addAttribute("computerForm", new Computer());
    // return VIEW;
    // }
    //
    // @RequestMapping(method = RequestMethod.POST)
    // public String createComputer(Model model, @ModelAttribute("computerForm")
    // Computer computer, BindingResult result) throws ServletException {
    // ComputerValidator computerValidator = new ComputerValidator();
    // computerValidator.validate(computer, result);
    //
    // if (result.hasErrors()) {
    // StringBuilder sb = new StringBuilder();
    // List<ObjectError> errors = result.getAllErrors();
    // for (ObjectError error : errors) {
    // sb.append(error.toString());
    // }
    // model.addAttribute("errors", sb.toString());
    // List<Company> companies = serviceCompany.getAllCompanies();
    // model.addAttribute("companies", companies);
    // model.addAttribute("computer", computer);
    // return VIEW;
    // } else {
    // serviceComputer.setComputer(computer);
    // return "redirect:/" + VIEW_HOME;
    // }
    //
    // }

    @PostMapping(value = "/computers")
    public ResponseEntity createCustomer(@RequestBody Computer computer) {

        serviceComputer.setComputer(computer);

        return new ResponseEntity(computer, HttpStatus.OK);
    }

}