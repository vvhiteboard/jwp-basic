package next.dao;

import core.jdbc.ConnectionManager;
import next.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by 77loo on 2017-04-16.
 */
public abstract class JdbcTemplate {

    public void update(User user) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ConnectionManager.getConnection();
            String sql = createQuery();

            pstmt = con.prepareStatement(sql);
            setValues(user, pstmt);

            pstmt.execute();
        } finally {
            if ( con != null) {
                con.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    abstract String createQuery();

    abstract void setValues(User user, PreparedStatement pstmt) throws SQLException;
}
