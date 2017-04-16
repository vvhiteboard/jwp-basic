package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 77loo on 2017-04-16.
 */
@FunctionalInterface
public interface RowMapper<T> {
    T mapRow(ResultSet resultSet) throws SQLException;
}
