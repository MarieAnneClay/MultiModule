package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company")
// @SecondaryTables({ @SecondaryTable(name = "computer", pkJoinColumns = {
// @PrimaryKeyJoinColumn(name = "company_id", referencedColumnName = "id") }) })
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    // @OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade =
    // CascadeType.REMOVE)
    // private List<Computer> computers;

    /** DEFAULT CONSTRUCTOR. */
    public Company() {
        super();
    }

    /** CONSTRUCTOR with id.
     * @param id id of the company in the database */
    public Company(String name) {
        super();
        this.name = name;
    }

    /** CONSTRUCTOR with id and name.
     * @param id id of the company in the database */
    public Company(Long id) {
        super();
        this.id = id;
    }

    /** CONSTRUCTOR with id and name.
     * @param id id of the company in the database
     * @param name name of the company in the database */
    public Company(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public static class CompanyBuilder {
        Company company;

        public CompanyBuilder() {
            this.company = new Company();
        }

        public CompanyBuilder setId(Long id) {
            this.company.setId(id);
            return this;
        }

        public CompanyBuilder setName(String name) {
            this.company.setName(name);
            return this;
        }

        public Company build() {
            return this.company;
        }
    }

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

    // public List<Computer> getComputers() {
    // return computers;
    // }
    //
    // public void setComputers(List<Computer> computers) {
    // this.computers = computers;
    // }
}
