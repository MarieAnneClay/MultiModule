package controller;

import java.io.IOException;
import java.net.URI;

import javax.servlet.ServletException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mapper.MapperComputer;
import model.Computer;
import service.ServiceCompany;
import service.ServiceComputer;
import util.Pagination;

@Controller
public class DashBoard {
    private final ServiceCompany serviceCompany;
    private final ServiceComputer serviceComputer;
    private static final String VIEW = "DashBoard";
    private static final String VIEW_HOME = "dashboard";

    static org.glassfish.jersey.client.JerseyClient client = (org.glassfish.jersey.client.JerseyClient) ClientBuilder.newClient();
    static WebTarget target = client.target(getBaseURI());
    static ClientConfig cfg = new ClientConfig(GenericConverter.class);
    static ObjectMapper mapper = new ObjectMapper();

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/Webapp/wsdashboard/").port(8080).build();
    }

    @Autowired
    public DashBoard(ServiceCompany serviceCompany, ServiceComputer serviceComputer) {
        super();
        this.serviceComputer = serviceComputer;
        this.serviceCompany = serviceCompany;
    }

    @RequestMapping(value = { "", "/", "/dashboard" }, method = RequestMethod.GET)
    public String listOfComputers(ModelMap map, @RequestParam(value = "search", defaultValue = "") String search,
            @RequestParam(value = "numberOfComputerByPage", defaultValue = "10") int numberOfComputerByPage, @RequestParam(value = "currentPage", defaultValue = "0") int currentPage,
            @RequestParam(value = "sort", defaultValue = "cr.name") String sort, @RequestParam(value = "order", defaultValue = "DESC") String order,
            @RequestParam(value = "lang", defaultValue = "eng") String lang) throws JsonParseException, JsonMappingException, IOException {

        Page<Computer> page = serviceComputer
                .getComputerByName(PageRequest.of(currentPage, numberOfComputerByPage, Pagination.getOrder(sort).equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sort), search);

        // mapper.readValue(target.path("page/" + currentPage + "/search/" + search +
        // "/numberOfComputerByPage/" + numberOfComputerByPage + "/sort/" + sort +
        // "/order/" + order)
        // .request(MediaType.APPLICATION_JSON).get(String.class), new
        // TypeReference<List<Computer>>() {
        // });
        map.addAttribute("search", search);
        map.addAttribute("numberOfComputerByPage", numberOfComputerByPage);
        map.addAttribute("currentPage", currentPage);
        map.addAttribute("size", serviceComputer.getCount(search));
        // map.addAttribute("size", target.path("count/" +
        // search).request(MediaType.APPLICATION_JSON).get(String.class));
        map.addAttribute("computers", MapperComputer.toDTOComputer(page.getContent(), lang));
        map.addAttribute("serviceCompany", serviceCompany);

        return VIEW;
    }

    @RequestMapping(value = { "/deleted" }, method = RequestMethod.POST)
    public String deleteComputers(@RequestParam(value = "selection") String idsSelects) throws ServletException {
        target.path("delete/" + idsSelects).request(MediaType.APPLICATION_JSON).delete();
        return "redirect:/" + VIEW_HOME;

    }

}