package edu.yuhf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.yuhf.entity.Role;

@Repository
public interface RoleDao {
	public List<Role> queryAll();
	public Role queryById(int id);
	public List<Role> queryByName(String name);
	public int update(Role role);
	public int delete(int ids[]);
	public int insert(Role role);
	public List<Role> queryByUserId(int id);
	public int deleteUserRole(int id);
	public int insertUserRole(HashMap<String,Integer> map);
}
