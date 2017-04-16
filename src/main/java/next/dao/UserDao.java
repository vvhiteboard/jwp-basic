package next.dao;

import next.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {

    public void insert(User user) throws SQLException {
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";

        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
            }
        };

        jdbcTemplate.update(sql);
    }

    public void update(User user) throws SQLException {
        String sql = "UPDATE USERS SET name = ?, email = ? WHERE userId = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getName());
                pstmt.setString(2, user.getEmail());
                pstmt.setString(3, user.getUserId());
            }
        };

        jdbcTemplate.update(sql);
    }

    public List<User> findAll() throws SQLException {
        String sql = "SELECT userId, name, email FROM USERS";

        SelectJdbcTemplate<User> selectJdbcTemplate = new SelectJdbcTemplate<User>() {
            @Override
            void setValues(PreparedStatement pstmt) throws SQLException {
            }

            @Override
            User mapRow(ResultSet rs) throws SQLException {
                return new User(rs.getString("userId"), "", rs.getString("name"), rs.getString("email"));
            }
        };

        return selectJdbcTemplate.query(sql);
    }

    public User findByUserId(String userId) throws SQLException {
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";

        SelectJdbcTemplate<User> selectJdbcTemplate = new SelectJdbcTemplate<User>() {
            @Override
            void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userId);
            }

            @Override
            User mapRow(ResultSet rs) throws SQLException {
                return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
            }
        };

        return selectJdbcTemplate.queryForObject(sql);
    }
}
