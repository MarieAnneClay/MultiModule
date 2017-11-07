package DAOImpl;

import static DAOUtil.DAOUtil.initPreparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import DAO.ComputerDAO;
import DAOUtil.ConnectionManager;
import DAOUtil.DAOException;
import model.Computer;

@Repository
public class ComputerImpl implements ComputerDAO {
    private static Logger LOGGER = Logger.getLogger(ComputerImpl.class.getName());
    private ConnectionManager connectionManager;
    private static final String SELECT_BY_ID = "SELECT * FROM computer WHERE id = ?";
    private static final String INSERT = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";

    @Autowired
    private ComputerImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Long getCount(String name) {
        try (Connection connexion = connectionManager.getConnection();
                PreparedStatement preparedStatement = connexion
                        .prepareStatement(" SELECT COUNT(*) FROM computer cr LEFT JOIN company cy ON cy.id = cr.company_id WHERE cr.name LIKE '%" + name + "%' OR cy.name LIKE '%" + name + "%' ");
                ResultSet resultSet = preparedStatement.executeQuery();) {
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }

    };

    @Override
    public Page<Computer> findAll(Pageable page, String name) {
        List<Computer> computers = new ArrayList<Computer>();
        int start = page.getPageNumber() * page.getPageSize();
        int end = (start + page.getPageSize());
        String selectFilter = " SELECT * FROM computer cr LEFT JOIN company cy ON cy.id = cr.company_id WHERE cr.name LIKE '%" + name + "%' OR cy.name LIKE '%" + name + "%' ORDER BY " + page.getSort()
                + " LIMIT " + start + ", " + end;
        selectFilter = selectFilter.replace(":", "");
        try (Connection connexion = connectionManager.getConnection();
                PreparedStatement preparedStatement = initPreparedStatement(connexion, selectFilter);
                ResultSet resultSet = preparedStatement.executeQuery();) {

            while (resultSet.next()) {
                computers.add(map(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }

        return new PageImpl<Computer>(computers, page, computers.size());
    }

    @Override
    public Computer findById(Long id) {
        Computer computer = new Computer();
        try (Connection connexion = connectionManager.getConnection();
                PreparedStatement preparedStatement = initPreparedStatement(connexion, SELECT_BY_ID, id);
                ResultSet resultSet = preparedStatement.executeQuery();) {
            resultSet.next();
            computer = map(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }
        return computer;
    }

    @Override
    public void create(Computer result) {
        try (Connection connexion = connectionManager.getConnection();
                PreparedStatement preparedStatement = initPreparedStatement(connexion, INSERT, result.getName(), result.getIntroduced(), result.getDiscontinued(), result.getCompanyId());) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }
    }

    @Override
    public void update(Computer result) {
        try (Connection connexion = connectionManager.getConnection();
                PreparedStatement preparedStatement = initPreparedStatement(connexion, UPDATE, result.getName(), result.getIntroduced(), result.getDiscontinued(), result.getCompanyId(),
                        result.getId());) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }
    }

    @Override
    public void deleteByIds(String ids) {
        try (Connection connexion = connectionManager.getConnection(); PreparedStatement preparedStatement = initPreparedStatement(connexion, "DELETE FROM computer WHERE id IN(" + ids + ")");) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }
    }

    public static Computer map(ResultSet resultSet) throws SQLException {
        Computer computer = new Computer();
        computer.setId(resultSet.getLong("id"));
        if (resultSet.wasNull()) {
            computer.setId(null);
        }
        computer.setName(resultSet.getString("name"));
        Date introduced = resultSet.getDate("introduced");
        computer.setIntroduced(introduced != null ? ((java.sql.Date) introduced).toLocalDate() : null);
        Date discontinued = resultSet.getDate("discontinued");
        computer.setDiscontinued(discontinued != null ? ((java.sql.Date) discontinued).toLocalDate() : null);
        computer.setCompanyId(resultSet.getLong("company_id"));
        if (resultSet.wasNull()) {
            computer.setCompanyId(null);
        }
        return computer;
    }
}
