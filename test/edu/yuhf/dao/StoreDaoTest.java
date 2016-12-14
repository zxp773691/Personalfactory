package edu.yuhf.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import edu.yuhf.config.DataSourceConfig;
import edu.yuhf.config.MvcConfig;
import edu.yuhf.entity.Permission;
import edu.yuhf.entity.Role;
import edu.yuhf.entity.Store;
import edu.yuhf.entity.User;
import mybatis.config.MyBatisConfig;
import mybatis.config.MyBatisMapperScannerConfig;


@RunWith(SpringJUnit4ClassRunner.class )
@WebAppConfiguration
@ContextConfiguration(classes = {DataSourceConfig.class,MyBatisConfig.class,MyBatisMapperScannerConfig.class,MvcConfig.class})
public class StoreDaoTest extends  AbstractJUnit4SpringContextTests{
	@Resource
	StoreDao storeDao;
	@Test
	public void testqueryBySingleName() throws Exception {
		Store store=storeDao.queryBySingleName("小麦");
		System.out.println(store);
	}
	@Test
	public void testQueryById(){
		Store store=storeDao.queryById(4);
		System.out.println(store+"============");
	}
	@Test
	public void testinsert(){
		Store store=new Store();
		store.setMaterialid(21);
		store.setWeight(240);
		storeDao.insert(store);
		System.out.println(store.getId()+"====================");
	}
	@Test
	public void testdelete(){
		String ids="23";
		String[] arrays=ids.split(",");
		int[] array=new int[arrays.length];
		for(int i=0;i<arrays.length;i++){
			array[i]=Integer.parseInt(arrays[i]);
		}		
		int i=storeDao.delete(array);
		System.out.println(i+"=====================");
	}
	@Test
	public void testquerybypage(){
		PageHelper.startPage(1,3,true);
		List<Store> list=storeDao.queryall();
		PageInfo<Store> pageInfo = new PageInfo<>(list);
		System.out.println(pageInfo.getEndRow());
		System.out.println(pageInfo.getTotal());
		System.out.println(pageInfo.toString());
		System.out.println(pageInfo.getList());
		for(Store user:pageInfo.getList()){
			System.out.println(user.getId()+","+user.getMaterialid());
		}
	}
	@Test
	public void testQueryAll(){
		List<Store> list=storeDao.queryall();
		System.out.println(list);
	}
	@Test
	public void testupdate() throws Exception {
		Store store=new Store(41,150,2);
		storeDao.update(store);
	}
	@Test
	public void testQueryByMaterialId() throws Exception {
		Store store =storeDao.queryByMaterialId(2);
		System.out.println(store);
	}
}
