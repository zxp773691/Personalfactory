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
import edu.yuhf.entity.Record;
import edu.yuhf.entity.RecordInfo;
import mybatis.config.MyBatisConfig;
import mybatis.config.MyBatisMapperScannerConfig;


@RunWith(SpringJUnit4ClassRunner.class )
@WebAppConfiguration
@ContextConfiguration(classes = {DataSourceConfig.class,MyBatisConfig.class,MyBatisMapperScannerConfig.class,MvcConfig.class})
public class RecordDaoTest extends  AbstractJUnit4SpringContextTests{

	@Resource
	RecordDao recordDao;
	
	@Test
	public void testqueryAll() throws Exception {
		List<Record> list=recordDao.queryAll();
		System.out.println(list);
	}
	@Test
	public void testqueryById() throws Exception {
		Record record=recordDao.queryById(2);
		System.out.println(record);
	}
	@Test
	public void testinsert() throws Exception {
		Record record=new Record(0,"2016/11/1",100,60,3,61);
		recordDao.insert(record);
	}
	@Test
	public void testupdate() throws Exception {
		Record record=new Record(2,"2016/11/2",100,60,3,61);
		recordDao.update(record);
	}
	@Test
	public void testdelete() throws Exception {
		int ids[]=new int[]{3};
		recordDao.delete(ids);
	}
	@Test
	public void testqueryRecordInfo() throws Exception {
		List<RecordInfo> list=recordDao.queryRecordInfo();
		System.out.println(list);
	}
	@Test
	public void testqueryByFrName() throws Exception {
		List<RecordInfo> list=recordDao.queryByFrName("面");
		System.out.println(list);
	}
	@Test
	public void testqueryInfoById() throws Exception {
		RecordInfo recordInfo=recordDao.queryInfoById(4);
		System.out.println(recordInfo);
	}
}
