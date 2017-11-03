package DAOUtil;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariDataSource;

@Component
public class ConnectionManager {
    private HikariDataSource dataSource;

    @Autowired
    public ConnectionManager(HikariDataSource dataSource) {
        super();
        this.dataSource = dataSource;
    }

    public Connection getConnection() throws DAOException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new DAOException("Unable to connect to database : " + e.getMessage());
        }
    }
}
