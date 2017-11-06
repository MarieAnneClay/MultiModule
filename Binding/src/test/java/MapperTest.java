import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import dto.DTOComputer;
import junit.framework.TestCase;
import mapper.MapperComputer;
import model.Computer;

@RunWith(MockitoJUnitRunner.class)
public class MapperTest extends TestCase {

    @Test
    public void toDTOComputerCustomParserDateEN() {
        DTOComputer DTOcomputer = new DTOComputer("1", "computer-test", "1993-04-21", null, "1");

        Computer computer = new Computer(1L, "computer-test", LocalDate.of(1993, 04, 21), null, 1L);

        DTOComputer test = MapperComputer.toDTOComputer(computer, "en");

        // assertEquals(computer.getId().toString(), test.getId());
        // assertEquals(computer.getName().toString(), test.getName());
        // assertEquals(computer.getIntroduced().toString(), test.getIntroduced());
        // assertEquals(computer.getDiscontinued().toString(), test.getDiscontinued());
        // assertEquals(computer.getCompanyId().toString(), test.getCompanyId());

        assertEquals(DTOcomputer.getId(), test.getId());
        assertEquals(DTOcomputer.getName(), test.getName());
        assertEquals(DTOcomputer.getIntroduced(), test.getIntroduced());
        assertEquals(DTOcomputer.getDiscontinued(), test.getDiscontinued());
        assertEquals(DTOcomputer.getCompanyId(), test.getCompanyId());

    }

    @Test
    public void toDTOComputerCustomParserDateFR() {
        DTOComputer DTOcomputer = new DTOComputer("1", "computer-test", "21/04/1993", null, "1");

        Computer computer = new Computer(1L, "computer-test", LocalDate.of(1993, 04, 21), null, 1L);

        DTOComputer test = MapperComputer.toDTOComputer(computer, "fr");

        assertEquals(DTOcomputer.getId(), test.getId());
        assertEquals(DTOcomputer.getName(), test.getName());
        assertEquals(DTOcomputer.getIntroduced(), test.getIntroduced());
        assertEquals(DTOcomputer.getDiscontinued(), test.getDiscontinued());
        assertEquals(DTOcomputer.getCompanyId(), test.getCompanyId());

    }

    @Test
    public void toComputerCustomParserDateEN() {
        DTOComputer DTOcomputer = new DTOComputer("1", "computer-test", "21/04/1993", null, "1");

        Computer computer = new Computer(1L, "computer-test", LocalDate.of(1993, 04, 21), null, 1L);

        Computer test = MapperComputer.toComputer(DTOcomputer);

        assertEquals(computer.getId(), test.getId());
        assertEquals(computer.getName(), test.getName());
        assertEquals(computer.getIntroduced(), test.getIntroduced());
        assertEquals(computer.getDiscontinued(), test.getDiscontinued());
        assertEquals(computer.getCompanyId(), test.getCompanyId());
    }

    @Test
    public void toComputerCustomParserDateFR() {

        DTOComputer DTOcomputer = new DTOComputer("1", "computer-test", "1993-04-21", null, "1");

        Computer computer = new Computer(1L, "computer-test", LocalDate.of(1993, 04, 21), null, 1L);

        Computer test = MapperComputer.toComputer(DTOcomputer);

        assertEquals(computer.getId(), test.getId());
        assertEquals(computer.getName(), test.getName());
        assertEquals(computer.getIntroduced(), test.getIntroduced());
        assertEquals(computer.getDiscontinued(), test.getDiscontinued());
        assertEquals(computer.getCompanyId(), test.getCompanyId());

    }

}
