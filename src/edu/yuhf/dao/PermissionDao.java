package edu.yuhf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.yuhf.entity.Permission;

@Repository
public interface PermissionDao {
	public List<Permission> queryAll();
	public Permission queryById(int id);
	public List<Permission> getPermission(int id);
	public List<Permission> queryByName(String name);
	public int update(Permission permission);
	public int delete(int ids[]);
	public int insert(Permission permission);
	public List<Permission> queryByRoleId(int id);
	public int deleteRolePermission(int id);
	public int insertRolePermission(HashMap<String,Integer> map);
}
