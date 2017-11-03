package DAOUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOUtil {
    public static PreparedStatement initPreparedStatement(Connection connection, String request, Object... params) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(request);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        return preparedStatement;
    }

    public static PreparedStatement initPreparedStatement(Connection connection, String request) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(request);
        return preparedStatement;
    }
}
