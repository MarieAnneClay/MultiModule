package DAOImpl;

import static DAOUtil.DAOUtil.initPreparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import DAO.CompanyDAO;
import DAOUtil.ConnectionManager;
import DAOUtil.DAOException;
import model.Company;

@Repository
public class CompanyImpl implements CompanyDAO {
    private ConnectionManager connectionManager;
    private static final String SELECT_ALL = "SELECT * FROM company ";
    private static final String SELECT_BY_ID = "SELECT * FROM company WHERE id = ?";

    @Autowired
    private CompanyImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public List<Company> findAll() {
        List<Company> companies = new ArrayList<Company>();
        try (Connection connexion = connectionManager.getConnection();
                PreparedStatement preparedStatement = initPreparedStatement(connexion, SELECT_ALL);
                ResultSet resultSet = preparedStatement.executeQuery();) {
            while (resultSet.next()) {
                companies.add(map(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }
        return companies;
    }

    @Override
    public Company getOne(Long id) {
        Company company = new Company();
        try (Connection connexion = connectionManager.getConnection();
                PreparedStatement preparedStatement = initPreparedStatement(connexion, SELECT_BY_ID, id);
                ResultSet resultSet = preparedStatement.executeQuery();) {
            resultSet.next();
            company = map(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }
        return company;
    };

    public static Company map(ResultSet resultSet) throws SQLException {
        Company company = new Company();
        company.setId(resultSet.getLong("id"));
        company.setName(resultSet.getString("name"));
        return company;
    }
}
