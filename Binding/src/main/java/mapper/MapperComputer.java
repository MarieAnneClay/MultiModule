package mapper;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import dto.DTOComputer;
import model.Computer;

@Component
public class MapperComputer {
    private static org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MapperComputer.class);
    private static final DateTimeFormatter FMT_EN = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter FMT_FR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public MapperComputer() {
        // TODO Auto-generated constructor stub
    }

    public static DTOComputer toDTOComputer(Computer computer, String lang) {
        DTOComputer returnComputer = new DTOComputer();
        returnComputer.setId(computer.getId().toString());
        returnComputer.setName(computer.getName());
        try {
            String date = computer.getIntroduced().toString();
            if (lang.equals("fr")) {
                date = date.substring(8, 10) + "/" + date.substring(5, 7) + "/" + date.substring(0, 4);
            }
            returnComputer.setIntroduced(date);
        } catch (NullPointerException e) {
            returnComputer.setIntroduced(null);
        }
        try {
            String date = computer.getDiscontinued().toString();
            if (lang.equals("fr")) {
                date = date.substring(8, 10) + "/" + date.substring(5, 7) + "/" + date.substring(0, 4);
            }
            returnComputer.setDiscontinued(date);
        } catch (NullPointerException e) {
            returnComputer.setDiscontinued(null);
        }
        try {
            returnComputer.setCompanyId(computer.getCompanyId().toString());
        } catch (NullPointerException e) {
            returnComputer.setCompanyId(null);
        }

        try {
            returnComputer.setCompanyName(computer.getCompanyName());
        } catch (NullPointerException e) {
            returnComputer.setCompanyName(null);
        }
        return returnComputer;
    }

    public static Computer toComputer(DTOComputer computer) {
        Computer returnComputer = new Computer();
        try {
            returnComputer.setId(Long.parseLong(computer.getId()));
        } catch (NumberFormatException e) {
            returnComputer.setId(null);
        }
        returnComputer.setName(computer.getName());
        try {
            String date = computer.getIntroduced();
            if (date.contains("/")) {
                date = date.substring(6, 10) + "-" + date.substring(3, 5) + "-" + date.substring(0, 2);
            }
            returnComputer.setIntroduced(LocalDate.parse(date, FMT_EN));
        } catch (DateTimeException | NullPointerException e) {
            returnComputer.setIntroduced(null);
        }
        try {
            String date = computer.getDiscontinued();
            if (date.contains("/")) {
                date = date.substring(6, 10) + "-" + date.substring(3, 5) + "-" + date.substring(0, 2);
            }
            returnComputer.setDiscontinued(LocalDate.parse(date, FMT_EN));
        } catch (DateTimeException | NullPointerException e) {
            returnComputer.setDiscontinued(null);
        }
        try {
            returnComputer.setCompanyId(Long.parseLong(computer.getCompanyId()));
            if (returnComputer.getCompanyId() == 0) {
                returnComputer.setCompanyId(null);
            }
        } catch (NumberFormatException e) {
            returnComputer.setCompanyId(null);
        }
        return returnComputer;
    }

    public static List<DTOComputer> toDTOComputer(List<Computer> computers, String lang) {
        List<DTOComputer> returnComputers = new ArrayList<DTOComputer>();
        for (Computer computer : computers) {
            returnComputers.add(toDTOComputer(computer, lang));
        }
        return returnComputers;
    }

    public static List<Computer> toComputer(List<DTOComputer> computers) {
        List<Computer> returnComputers = new ArrayList<Computer>();
        for (DTOComputer computer : computers) {
            returnComputers.add(toComputer(computer));
        }
        return returnComputers;
    }
}