package next.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by 77loo on 2017-04-16.
 */
@FunctionalInterface
public interface PreparedStatementSetter<T> {
    void setValues(PreparedStatement pstmt) throws SQLException;
}
