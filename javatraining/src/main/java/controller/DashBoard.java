package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import service.ServiceCompany;
import service.ServiceComputer;
import util.Page;

@Controller

public class DashBoard {
    private final ServiceComputer serviceComputer;
    private final ServiceCompany serviceCompany;
    private static final String VIEW = "DashBoard";
    private static final String VIEW_HOME = "dashboard";

    @Autowired
    public DashBoard(ServiceComputer serviceComputer, ServiceCompany serviceCompany) {
        super();
        this.serviceComputer = serviceComputer;
        this.serviceCompany = serviceCompany;
    }

    @RequestMapping(value = { "", "/", "/dashboard" }/* , method = RequestMethod.GET */)
    public String listOfComputers(ModelMap map, @RequestParam(value = "search", defaultValue = "") String search,
            @RequestParam(value = "numberOfComputerByPage", defaultValue = "10") int numberOfComputerByPage, @RequestParam(value = "currentPage", defaultValue = "0") int currentPage,
            @RequestParam(value = "sort", defaultValue = "name") String sort, @RequestParam(value = "order", defaultValue = "DESC") String order) {
        map.addAttribute("search", search);
        map.addAttribute("numberOfComputerByPage", numberOfComputerByPage);
        map.addAttribute("currentPage", currentPage);
        map.addAttribute("size", serviceComputer.getCount(search));
        map.addAttribute("computers", serviceComputer
                .getComputerByName(PageRequest.of(currentPage, numberOfComputerByPage, Page.getOrder(sort).equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sort), search).getContent());
        map.addAttribute("serviceCompany", serviceCompany);

        return VIEW;
    }

    // @Path("/secure/delete/{id}")
    @RequestMapping(value = { "/dashboard" }, method = RequestMethod.POST)
    public String deleteComputers(@RequestParam(value = "selection") String idsSelects) throws ServletException {
        serviceComputer.deleteComputer(idsSelects);
        return "redirect:/" + VIEW_HOME;

    }

    @RequestMapping(value = "/login?error", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        // model.addAttribute("user", getPrincipal());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            return "redirect:/";
        }
        return "redirect:/";
    }

}