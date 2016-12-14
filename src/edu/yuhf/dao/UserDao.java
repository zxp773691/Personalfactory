package edu.yuhf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.yuhf.entity.User;

@Repository
public interface UserDao {
	public User queryById(int id);
	public List<User> queryAll();
	public List<User> queryByName(String name);
	public int update(User user);
	public int delete(int ids[]);
	public int insert(User user);
	public User queryBySingleName(String name);
}
