package next.dao;

import next.model.User;

import java.sql.ResultSet;
import java.util.List;

public class UserDao {

    public void insert(User user) {
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";

        JdbcTemplate<User> jdbcTemplate = new JdbcTemplate();

        jdbcTemplate.update(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    public void update(User user) {
        String sql = "UPDATE USERS SET name = ?, email = ? WHERE userId = ?";

        JdbcTemplate<User> jdbcTemplate = new JdbcTemplate<>();

        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getUserId());
    }

    public List<User> findAll() {
        String sql = "SELECT userId, name, email FROM USERS";

        JdbcTemplate<User> jdbcTemplate = new JdbcTemplate<>();

        // FuntionalInterface 람다식으로 함수형 인터페이스 정의
        RowMapper<User> userRowMapper = (ResultSet resultSet) -> new User(resultSet.getString("userId"), "", resultSet.getString("name"), resultSet.getString("email"));

        return jdbcTemplate.query(sql, userRowMapper);
    }

    public User findByUserId(String userId) {
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";

        JdbcTemplate<User> jdbcTemplate = new JdbcTemplate<>();

        // FuntionalInterface 람다식으로 함수형 인터페이스 정의
        RowMapper<User> userRowMapper = (ResultSet resultSet) -> new User(resultSet.getString("userId"), "", resultSet.getString("name"), resultSet.getString("email"));

        return jdbcTemplate.queryForObject(sql, userRowMapper, userId);
    }
}
