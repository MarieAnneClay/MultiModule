package controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.servlet.ServletException;
import javax.validation.Valid;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.DTOComputer;
import mapper.MapperComputer;
import model.Company;
import model.Computer;
import service.ServiceComputer;
import util.ComputerValidator;

@Controller
@RequestMapping("/addcomputer")
public class AddComputer {
    private final ServiceComputer serviceComputer;
    static org.glassfish.jersey.client.JerseyClient client = (org.glassfish.jersey.client.JerseyClient) ClientBuilder.newClient();
    static WebTarget target = client.target(getBaseURI());
    static ClientConfig cfg = new ClientConfig(GenericConverter.class);
    static ObjectMapper mapper = new ObjectMapper();
    public static final String REST_SERVICE_URI = "http://localhost:8080/Webapp/wsdashboard";

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/Webapp/wsdashboard/").port(8080).build();
    }

    @Autowired
    public AddComputer(ServiceComputer serviceComputer) {
        super();
        this.serviceComputer = serviceComputer;
    }

    private static final String VIEW = "AddComputer";
    private static final String VIEW_HOME = "dashboard";

    @RequestMapping(method = RequestMethod.GET)
    public String listOfCompanies(ModelMap model) throws ServletException, JsonParseException, JsonMappingException, IOException {
        List<Company> companies = mapper.readValue(target.path("companies").request(MediaType.APPLICATION_JSON).get(String.class), new TypeReference<List<Company>>() {
        });
        model.addAttribute("companies", companies);
        model.addAttribute("computerForm", new Computer());
        return VIEW;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createComputer(Model model, @Valid @ModelAttribute("computerForm") DTOComputer computer, BindingResult result)
            throws ServletException, JsonParseException, JsonMappingException, IOException {
        ComputerValidator computerValidator = new ComputerValidator();
        computerValidator.validate(computer, result);

        if (result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError error : errors) {
                sb.append(error.toString());
            }
            model.addAttribute("errors", sb.toString());
            List<Company> companies = mapper.readValue(target.path("companies").request(MediaType.APPLICATION_JSON).get(String.class), new TypeReference<List<Company>>() {
            });
            model.addAttribute("companies", companies);
            model.addAttribute("computer", computer);
            return VIEW;
        } else {
            // RestTemplate restTemplate = new RestTemplate();
            // restTemplate.postForLocation(REST_SERVICE_URI + "/addcomputer", computer,
            // Computer.class);
            serviceComputer.setComputer(MapperComputer.toComputer(computer));
            return "redirect:/" + VIEW_HOME;
        }

    }

}