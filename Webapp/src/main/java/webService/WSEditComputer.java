package webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Computer;
import service.ServiceCompany;
import service.ServiceComputer;

// @Controller
@RequestMapping("/editcomputer")
@RestController
public class WSEditComputer {
    private final ServiceComputer serviceComputer;

    @Autowired
    public WSEditComputer(ServiceCompany serviceCompany, ServiceComputer serviceComputer) {
        super();
        this.serviceComputer = serviceComputer;
    }

    // @RequestMapping(method = RequestMethod.GET)
    // public String oldComputer(ModelMap model, @RequestParam(value = "computerId",
    // required = true) Long id) throws ServletException {
    // if (id != null) {
    // model.addAttribute("id", id);
    // model.addAttribute("computer", serviceComputer.getComputerById(id));
    // }
    // List<Company> companies = serviceCompany.getAllCompanies();
    // model.addAttribute("companies", companies);
    // model.addAttribute("computerForm", new Computer());
    //
    // return VIEW;
    // }
    //
    // @RequestMapping(method = RequestMethod.POST)
    // public String updateComputer(Model model, @ModelAttribute("computerForm")
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
    // List<Company> companies = serviceCompany.getAllCompanies();
    // model.addAttribute("companies", companies);
    // model.addAttribute("errors", sb.toString());
    // return VIEW;
    // } else {
    // serviceComputer.updateComputer(computer);
    // return "redirect:/" + VIEW_HOME;
    // }
    //
    // }

    @PutMapping("/customers/{id}")
    public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody Computer computer) {

        serviceComputer.updateComputer(computer);
        //
        // if (null == customer) {
        // return new ResponseEntity("No Customer found for ID " + id,
        // HttpStatus.NOT_FOUND);
        // }

        return new ResponseEntity(computer, HttpStatus.OK);
    }

}