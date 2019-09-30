package com.example.demo.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addUser(User user) {
		jdbcTemplate.execute("insert into user(`id`, `username`, `password`, `fullname`) values (?,?,?,?)",
				(PreparedStatementCallback<Boolean>) (ps) -> {
					ps.setInt(1, user.getId());
					ps.setString(2, user.getUsername());
					ps.setString(3, user.getPassword());
					ps.setString(4, user.getFullname());
					return ps.execute();
				});
	}

	@Override
	public List<User> getAllUsers() {
		return jdbcTemplate.query("select * from user", (rs, rowNum) -> new User(rs.getInt("id"),
				rs.getString("username"), rs.getString("password"), rs.getString("fullname")));
	}

	@Override
	public User getUserByUsername(String username) {
		return jdbcTemplate.query("select * from user where username = ?", (ps) -> {
			ps.setString(1, username);
		}, (rs) -> {
			if (rs.next()) {
				return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("fullname"));
			} else {
				return null;
			}
		});
	}

	@Override
	public User getUserById(int id) {
		return jdbcTemplate.query("select * from user where id = ?", (ps) -> {
			ps.setInt(1, id);
		}, (rs) -> {
			if (rs.next()) {
				return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("fullname"));
			} else {
				return null;
			}
		});
	}

	@Override
	public void changeUser(int id, User user) {
		jdbcTemplate.execute("update user set username = ?, password = ?, fullname = ? where id = ?", (PreparedStatementCallback<Boolean>) (ps) -> {
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFullname());
			ps.setInt(4, id);
			return ps.execute();
		});
	}

	@Override
	public void deleteUser(int id) {
		jdbcTemplate.execute("delete from user where id = ?", (PreparedStatementCallback<Boolean>) (ps) -> {
			ps.setInt(1, id);
			return ps.execute();
		});
	}

	@Override
	public String getFullNameById(int id) {
		return jdbcTemplate.query("select fullname from user where id = ?", (ps) -> {
			ps.setInt(1, id);
		}, (rs) -> {
			if (rs.next()) {
				return rs.getString("fullname");
			} else {
				return null;
			}
		});
	}

	@Override
	public boolean isExist(int id) {
		return jdbcTemplate.query("select id from user where id = ?", (ps) -> {
			ps.setInt(1, id);
		}, (rs) -> {
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		});
	}

}