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
import edu.yuhf.entity.Voucher;
import mybatis.config.MyBatisConfig;
import mybatis.config.MyBatisMapperScannerConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { DataSourceConfig.class, MyBatisConfig.class, MyBatisMapperScannerConfig.class,
		MvcConfig.class })
public class VoucherDaoTest extends AbstractJUnit4SpringContextTests {

	@Resource
	VoucherDao voucherDao;

	@Test
	public void testqueryAll() throws Exception {
		List<Voucher> list = voucherDao.queryAll();
		System.out.println(list);
	}

	@Test
	public void testqueryById() throws Exception {
		Voucher voucher = voucherDao.queryById(2);
		System.out.println(voucher);
	}
	@Test
	public void testCountByOccurtime() throws Exception {
		int i=voucherDao.countByOccurtime("2016-10-28");
		String newI=String.format("%03d", i);
		String date="2016-10-28";
		String newDate=date.replace("-", "");
		System.out.println(newDate+newI);
		System.out.println("次数:"+i);
	}
	@Test
	public void testbuyMaterial() throws Exception {
		Voucher voucher=new Voucher();
		voucher.setVoucherno("20161031001");
		voucher.setBususerid(8);
		voucher.setCustomerid(2);
		voucher.setTotalprice(1000);
		voucher.setType(0);
		voucher.setWeight(200);
		voucher.setMaterialid(2);
		voucher.setOccurtime("2016/10/31");
		voucherDao.buyMaterial(voucher);
		System.out.println(voucher);
	}
	@Test
	public void testUpdate() throws Exception {
		Voucher voucher=new Voucher();
		voucher.setId(2);
		voucher.setVoucherno("20161031001");
		voucher.setStoreuserid(8);
		voucher.setCustomerid(2);
		voucher.setTotalprice(10001);
		voucher.setType(0);
		voucher.setWeight(200);
		voucher.setMaterialid(2);
		voucher.setBususerid(4);
		voucher.setOccurtime("2016/10/31");
		voucherDao.update(voucher);
	}
	@Test
	public void testdelete() throws Exception {
		int ids[]=new int[]{27};
		voucherDao.delete(ids);
	}
	@Test
	public void testinsert() throws Exception {
		Voucher voucher=new Voucher();
		voucher.setId(2);
		voucher.setVoucherno("20161031001");
		voucher.setStoreuserid(8);
		voucher.setCustomerid(2);
		voucher.setTotalprice(10001);
		voucher.setType(0);
		voucher.setWeight(200);
		voucher.setMaterialid(2);
		voucher.setBususerid(4);
		voucher.setOccurtime("2016/10/31");
		voucherDao.insert(voucher);
	}
	@Test
	public void testqueryByCustomerId() throws Exception {
		List<Voucher> list=voucherDao.queryByCustomerId(2);
		System.out.println(list);
	}
	@Test
	public void testqueryBusUserNull() throws Exception {
		List<Voucher> list=voucherDao.queryStoreUserNull();
		System.out.println(list);
	}
	
}
