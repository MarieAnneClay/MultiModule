package util;

import java.time.LocalDate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dto.DTOComputer;
import model.Computer;

public class ComputerValidator implements Validator {

    @Override
    public boolean supports(Class<?> computer) {
        return Computer.class.equals(computer);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DTOComputer computer = (DTOComputer) target;

        if (ValidationComputer.validationName(computer.getName()) != null) {
            errors.rejectValue("name", ValidationComputer.validationName(computer.getName()));
        }

        if (computer.getIntroduced() != null) {
            if (ValidationComputer.validationDate(computer.getIntroduced()) != null) {
                errors.rejectValue("introduced", ValidationComputer.validationDate(computer.getIntroduced()));
            }
        }

        if (computer.getDiscontinued() != null) {
            if (ValidationComputer.validationDate(computer.getDiscontinued()) != null) {
                errors.rejectValue("discontinued", ValidationComputer.validationDate(computer.getDiscontinued()));
            }
        }

        if (computer.getIntroduced() != null && computer.getDiscontinued() != null) {
            if (ValidationComputer.validationIntroducedBeforeDiscontinued(computer.getIntroduced() == "" ? null : LocalDate.parse(computer.getIntroduced()),
                    computer.getDiscontinued() == "" ? null : LocalDate.parse(computer.getDiscontinued())) != null) {
                errors.rejectValue("introduced", ValidationComputer.validationIntroducedBeforeDiscontinued(computer.getIntroduced() == "" ? null : LocalDate.parse(computer.getIntroduced()),
                        computer.getDiscontinued() == "" ? null : LocalDate.parse(computer.getDiscontinued())));
                errors.rejectValue("discontinued", ValidationComputer.validationIntroducedBeforeDiscontinued(computer.getIntroduced() == "" ? null : LocalDate.parse(computer.getIntroduced()),
                        computer.getDiscontinued() == "" ? null : LocalDate.parse(computer.getDiscontinued())));
            }
        }

    }

}
