package next.dao;

import next.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class UserDao {

    public void insert(User user) {
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";

        JdbcTemplate<User> jdbcTemplate = new JdbcTemplate();

        PreparedStatementSetter<User> pstmtSetter = (PreparedStatement pstmt) -> {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        };

        jdbcTemplate.update(sql, pstmtSetter);
    }

    public void update(User user) {
        String sql = "UPDATE USERS SET name = ?, email = ? WHERE userId = ?";

        JdbcTemplate<User> jdbcTemplate = new JdbcTemplate<>();

        PreparedStatementSetter<User> pstmtSetter = (PreparedStatement pstmt) -> {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getUserId());
        };

        jdbcTemplate.update(sql, pstmtSetter);
    }

    public List<User> findAll() {
        String sql = "SELECT userId, name, email FROM USERS";

        JdbcTemplate<User> jdbcTemplate = new JdbcTemplate<>();

        // FuntionalInterface 람다식으로 함수형 인터페이스 정의
        PreparedStatementSetter<User> pstmtSetter = (PreparedStatement pstmt) -> {
        };
        RowMapper<User> userRowMapper = (ResultSet resultSet) -> new User(resultSet.getString("userId"), "", resultSet.getString("name"), resultSet.getString("email"));

        jdbcTemplate.query(sql, pstmtSetter, userRowMapper);

        return jdbcTemplate.query(sql, pstmtSetter, userRowMapper);
    }

    public User findByUserId(String userId) {
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";

        JdbcTemplate<User> jdbcTemplate = new JdbcTemplate<>();

        // FuntionalInterface 람다식으로 함수형 인터페이스 정의
        PreparedStatementSetter<User> pstmtSetter = (PreparedStatement pstmt) -> pstmt.setString(1, userId);
        RowMapper<User> userRowMapper = (ResultSet resultSet) -> new User(resultSet.getString("userId"), "", resultSet.getString("name"), resultSet.getString("email"));

        return jdbcTemplate.queryForObject(sql, pstmtSetter, userRowMapper);
    }
}
