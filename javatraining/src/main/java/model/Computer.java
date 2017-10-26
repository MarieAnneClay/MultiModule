package model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Formula;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "computer")
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 1, max = 50)
    @NonNull
    private String name;
    @Nullable
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate introduced;
    @Nullable
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate discontinued;
    @Nullable
    @Column(name = "company_id")
    private Long companyId;

    @Nullable
    @Formula("(SELECT c.name FROM company c WHERE c.id = company_id)")
    // @Cascade(CascadeType.REFRESH)
    private String companyName;

    /** DEFAULT CONSTRUCTOR. */
    public Computer() {
        super();
    }

    /** CONSTRUCTOR with id.
     * @param id id of the companyId in the database
     * @param name name of the companyId in the database
     * @param introduced DATETIME of the day the computer has been introduced in the
     * companyId
     * @param discontinued DATETIME of the day the computer has been discontinued
     * @param companyIdId id of the companyId which possess this computer */
    public Computer(Long id) {
        super();
        this.id = id;
    }

    /** CONSTRUCTOR with id.
     * @param id id of the companyId in the database
     * @param name name of the companyId in the database
     * @param introduced DATETIME of the day the computer has been introduced in the
     * companyId
     * @param discontinued DATETIME of the day the computer has been discontinued
     * @param companyIdId id of the companyId which possess this computer */
    public Computer(Long id, String name, LocalDate introduced, LocalDate discontinued, String companyName) {
        super();
        this.id = id;
        this.name = name;
        this.introduced = introduced;
        this.discontinued = discontinued;
        this.companyName = companyName;
    }

    public Computer(Long id, String name, LocalDate introduced, LocalDate discontinued, Long companyId, String companyName) {
        super();
        this.id = id;
        this.name = name;
        this.introduced = introduced;
        this.discontinued = discontinued;
        this.companyId = companyId;
        this.companyName = companyName;
    }

    /** CONSTRUCTOR with id.
     * @param name name of the companyId in the database
     * @param introduced DATETIME of the day the computer has been introduced in the
     * companyId
     * @param discontinued DATETIME of the day the computer has been discontinued
     * @param companyIdId id of the companyId which possess this computer */
    public Computer(String name, LocalDate introduced, LocalDate discontinued, String companyName) {
        super();
        this.name = name;
        this.introduced = introduced;
        this.discontinued = discontinued;
        this.companyName = companyName;
    }

    // public static class ComputerBuilder {
    // public Computer computer;
    //
    // public ComputerBuilder() {
    // this.computer = new Computer();
    // }
    //
    // public ComputerBuilder setId(Long id) {
    // this.computer.setId(id);
    // return this;
    // }
    //
    // public ComputerBuilder setName(String name) {
    // this.computer.setName(name);
    // return this;
    // }
    //
    // public ComputerBuilder setIntroduced(LocalDate introduced) {
    // this.computer.setIntroduced(introduced);
    // return this;
    // }
    //
    // public ComputerBuilder setDiscontinued(LocalDate discontinued) {
    // this.computer.setDiscontinued(discontinued);
    // return this;
    // }
    //
    // public ComputerBuilder setLongId(Long companyId) {
    // this.computer.setCompanyId(companyId);
    // return this;
    // }
    //
    // public Computer build() {
    // return this.computer;
    // }
    // }

    /* GETTERS & SETTERS */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getIntroduced() {
        return introduced;
    }

    public void setIntroduced(LocalDate introduced) {
        this.introduced = introduced;
    }

    public LocalDate getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(LocalDate discontinued) {
        this.discontinued = discontinued;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

}