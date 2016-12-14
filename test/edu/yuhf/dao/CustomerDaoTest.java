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
import mybatis.config.MyBatisConfig;
import mybatis.config.MyBatisMapperScannerConfig;


@RunWith(SpringJUnit4ClassRunner.class )
@WebAppConfiguration
@ContextConfiguration(classes = {DataSourceConfig.class,MyBatisConfig.class,MyBatisMapperScannerConfig.class,MvcConfig.class})
public class CustomerDaoTest extends  AbstractJUnit4SpringContextTests{

	@Resource
	CustomerDao customerDao;
	
	@Test
	public void testqueryAll() throws Exception {
		List<Customer> list=customerDao.queryAll();
		System.out.println(list);
	}
	@Test
	public void testqueryById() throws Exception {
		Customer customer=customerDao.queryById(2);
		System.out.println(customer);
	}
	@Test
	public void testqueryByName() throws Exception {
		List<Customer> list=customerDao.queryByName("张旭");
		System.out.println(list);
	}
	@Test
	public void testupdate() throws Exception {
		Customer customer=new Customer(2,"20161024001","张旭","山东淄博","12345678901","370602199405295555","2016-10-24","这是第一位客户",0);
		customerDao.update(customer);
	}
	@Test
	public void testinsert() throws Exception {
		Customer customer=new Customer(0,"20161024002","韦振瑶","山东临沂","12345678901","370602199405291111","2016-10-24","这是第二位客户",1);
		customerDao.insert(customer);
	}
	@Test
	public void testdelete() throws Exception {
		int ids[]=new int[]{3};
		customerDao.delete(ids);
	}
	@Test
	public void testqueryBySingleName() throws Exception {
		Customer customer=customerDao.queryBySingleName("韦振瑶");
		System.out.println(customer);
	}
}
