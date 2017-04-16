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

    public void update(String sql) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ConnectionManager.getConnection();

            pstmt = con.prepareStatement(sql);
            setValues(pstmt);

            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            if ( con != null) {
                con.close();
            }
        }
    }

    abstract void setValues(PreparedStatement pstmt) throws SQLException;
}
