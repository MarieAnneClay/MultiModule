package mapper;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import DAOImpl.ComputerImpl;
import dto.DTOComputer;
import model.Computer;

@Component
public class MapperComputer {
    private static org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MapperComputer.class);
    private static ComputerImpl dao;
    private static final DateTimeFormatter FMT_EN = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter FMT_FR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    private MapperComputer(ComputerImpl dao) {
        this.dao = dao;
    }

    public static DTOComputer toDTOComputer(Computer computer, String lang) {
        DTOComputer returncomputer = new DTOComputer();
        returncomputer.setId(computer.getId().toString());
        returncomputer.setName(computer.getName());
        try {
            String date = computer.getIntroduced().toString();
            if (lang.equals("fr")) {
                date = date.substring(8, 10) + "/" + date.substring(5, 7) + "/" + date.substring(0, 4);
            }
            returncomputer.setIntroduced(date);
        } catch (NullPointerException e) {
            returncomputer.setIntroduced(null);
        }
        try {
            String date = computer.getDiscontinued().toString();
            if (lang.equals("fr")) {
                date = date.substring(8, 10) + "/" + date.substring(5, 7) + "/" + date.substring(0, 4);
            }
            returncomputer.setDiscontinued(date);
        } catch (NullPointerException e) {
            returncomputer.setDiscontinued(null);
        }
        try {
            returncomputer.setCompanyId(computer.getCompanyId().toString());
        } catch (NullPointerException e) {
            returncomputer.setCompanyId(null);
        }

        try {
            returncomputer.setCompanyName(dao.findById(computer.getCompanyId()).getName());
        } catch (NullPointerException e) {
            returncomputer.setCompanyName(null);
        }
        return returncomputer;
    }

    public static Computer toComputer(DTOComputer computer) {
        Computer returncomputer = new Computer();
        try {
            returncomputer.setId(Long.parseLong(computer.getId()));
        } catch (NumberFormatException e) {
            returncomputer.setId(null);
        }
        returncomputer.setName(computer.getName());
        try {
            String date = computer.getIntroduced();
            if (date.contains("/")) {
                date = date.substring(0, 4) + "-" + "-" + date.substring(5, 7) + "-" + date.substring(8, 10);
            }
            returncomputer.setIntroduced(LocalDate.parse(date, FMT_EN));
        } catch (DateTimeException | NullPointerException e) {
            returncomputer.setIntroduced(null);
        }
        try {
            String date = computer.getDiscontinued();
            if (date.contains("/")) {
                date = date.substring(0, 4) + "-" + "-" + date.substring(5, 7) + "-" + date.substring(8, 10);
            }
            returncomputer.setDiscontinued(LocalDate.parse(date, FMT_EN));
        } catch (DateTimeException | NullPointerException e) {
            returncomputer.setDiscontinued(null);
        }
        try {
            returncomputer.setCompanyId(Long.parseLong(computer.getCompanyId()));
            if (returncomputer.getCompanyId() == 0) {
                returncomputer.setCompanyId(null);
            }
        } catch (NumberFormatException e) {
            returncomputer.setCompanyId(null);
        }
        return returncomputer;
    }

    public static List<DTOComputer> toDTOComputer(List<Computer> computers, String lang) {
        List<DTOComputer> returncomputers = new ArrayList<DTOComputer>();
        for (Computer computer : computers) {
            returncomputers.add(toDTOComputer(computer, lang));
        }
        return returncomputers;
    }

    public static List<Computer> toComputer(List<DTOComputer> computers) {
        List<Computer> returncomputers = new ArrayList<Computer>();
        for (DTOComputer computer : computers) {
            returncomputers.add(toComputer(computer));
        }
        return returncomputers;
    }
}