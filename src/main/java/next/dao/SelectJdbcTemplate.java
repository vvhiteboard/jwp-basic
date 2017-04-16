package next.dao;

import core.jdbc.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 77loo on 2017-04-16.
 */
public abstract class SelectJdbcTemplate<T> {
    public List<T> query(String sql) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<T> results = new ArrayList<>();

        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);

            setValues(pstmt);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                results.add(mapRow(rs));
            }

            return results;

        } finally { // Close 순서는 ResultSet -> PreparedStatement -> Connection 순
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }

    public T queryForObject(String sql) throws SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        T result = null;

        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);

            setValues(pstmt);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                result = mapRow(rs);
            }

            return result;

        } finally { // Close 순서는 ResultSet -> PreparedStatement -> Connection 순
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }

    abstract void setValues(PreparedStatement pstmt) throws SQLException;

    abstract T mapRow(ResultSet rs) throws SQLException;
}
