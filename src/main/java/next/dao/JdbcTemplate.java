package next.dao;

import core.jdbc.ConnectionManager;
import next.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 77loo on 2017-04-16.
 */
public class JdbcTemplate<T> {

    public void update(String sql, Object... parameters) {
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            setParameters(pstmt, parameters);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public List<T> query(String sql, RowMapper<T> rowMapper, Object... paramaters) {

        try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            List<T> results = new ArrayList<>();
            setParameters(pstmt, paramaters);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    results.add(rowMapper.mapRow(rs));
                }
            } catch (SQLException e) {
                throw new DataAccessException(e);
            }

            return results;

        } catch (SQLException e) {
            throw new DataAccessException(e);

        }
    }

    public T queryForObject(String sql, RowMapper<T> rowMapper, Object... parameters) {

        try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            T result = null;
            setParameters(pstmt, parameters);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    result = rowMapper.mapRow(rs);
                }

            } catch (SQLException e) {
                throw new DataAccessException(e);
            }

            return result;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    private void setParameters(PreparedStatement pstmt, Object[] parameters) throws SQLException {
        for (int index = 0; index < parameters.length; index++) {
            pstmt.setObject(index + 1, parameters[index]);
        }
    }
}
