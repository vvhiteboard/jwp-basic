package next.dao;

import core.jdbc.ConnectionManager;
import next.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by 77loo on 2017-04-16.
 */
public abstract class UpdateJdbcTemplate {
    public void update(User user) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = ConnectionManager.getConnection();
            String sql = createQueryForUpdate();

            pstmt = con.prepareStatement(sql);
            setValuesForUpdate(user, pstmt);

            pstmt.execute();

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    abstract String createQueryForUpdate();

    abstract void setValuesForUpdate(User user, PreparedStatement pstmt)  throws SQLException;
}
