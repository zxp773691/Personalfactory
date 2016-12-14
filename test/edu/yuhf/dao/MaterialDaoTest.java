package edu.yuhf.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.yuhf.config.DataSourceConfig;
import edu.yuhf.config.MvcConfig;
import edu.yuhf.entity.Customer;
import edu.yuhf.entity.Material;
import mybatis.config.MyBatisConfig;
import mybatis.config.MyBatisMapperScannerConfig;


@RunWith(SpringJUnit4ClassRunner.class )
@WebAppConfiguration
@ContextConfiguration(classes = {DataSourceConfig.class,MyBatisConfig.class,MyBatisMapperScannerConfig.class,MvcConfig.class})
public class MaterialDaoTest extends  AbstractJUnit4SpringContextTests{

	@Resource
	MaterialDao materialDao;
	
	@Test
	public void testqueryAll() throws Exception {
		List<Material> list=materialDao.queryall();
		System.out.println(list);
	}
	@Test
	public void testqueryById() throws Exception {
		Material material=materialDao.queryById(2);
		System.out.println(material);
	}
	@Test
	public void testqueryByName() throws Exception {
		List<Material> list=materialDao.queryByName("玉米");
		System.out.println(list);
	}
	@Test
	public void testupdate() throws Exception {
		Material material=new Material(2,"小麦",0.5,0,1,11.0);
		materialDao.update(material);
	}
	@Test
	public void testinsert() throws Exception {
		Material material=new Material(0,"大米",0.4,1,1,15);
		materialDao.insert(material);
	}
	@Test
	public void testdelete() throws Exception {
		int ids[]=new int[]{42};
		materialDao.delete(ids);
	}
	@Test
	public void testqueryBySingleName() throws Exception {
		Material material=materialDao.queryBySingleName("小米面");
		System.out.println(material);
	}
}
