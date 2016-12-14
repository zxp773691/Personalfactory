package edu.yuhf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.yuhf.entity.Page;
import edu.yuhf.entity.Store;
import edu.yuhf.entity.StoreInfo;
@Repository
public interface StoreDao {
	public List<Store> queryall();
	public Store queryById(int id);
	public List<StoreInfo> queryInfo();
	public StoreInfo queryInfoById(int id);
	public int update(Store store);
	public List<Store> queryByPage(Page<Store> Page);
	public int insert(Store store);
	public int delete(int ids[]);
	public List<StoreInfo> queryByName(String name);
	public Store queryBySingleName(String name);
	public Store queryByMaterialId(int id);
}
