package edu.yuhf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.yuhf.entity.Customer;
import edu.yuhf.entity.Material;

@Repository
public interface MaterialDao {
	public List<Material> queryall();
	public Material queryById(int id);
	public List<Material> queryByName(String name);
	public int update(Material material);
	public int delete(int ids[]);
	public int insert(Material material);
	public Material queryBySingleName(String name);
	public List<Material> queryMaterial();
}
