package edu.yuhf.dao;

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
public class UserDaoTest extends  AbstractJUnit4SpringContextTests{

	@Resource
	UserDao userDao;
	@Resource
	StoreDao storeDao;
	@Resource
	RoleDao roleDao;
	@Resource
	PermissionDao permissionDao;

	@Test
	public void testQueryById(){
		User user=userDao.queryById(4);
		System.out.println(user+"============");
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
		List<Role> list=roleDao.queryAll();
		System.out.println(list);
	}
	@Test
	public void testQueryAllPer(){
		List<Permission> list=permissionDao.queryAll();
		System.out.println(list);
	}
	@Test
	public void testqueryByNameuser(){
		List<User> list=userDao.queryByName("wzy");
		System.out.println(list);
	}
	@Test
	public void testupdate() throws Exception {
		User user=new User(4,"20151018001","zx","111","张旭","12345678901","370602199405290001","2016-10-18",1);
		userDao.update(user);
	}
	@Test
	public void testinsertuser() throws Exception {
		User user=new User("20151020001","wzy","123","韦振瑶","12345678901","370602199405290002","2016-10-20",2);
		userDao.insert(user);
		
	}
	@Test
	public void testgetPermission() throws Exception {
		List<Permission> list=permissionDao.getPermission(4);
		System.out.println(list);
	}
	@Test
	public void testinsertUserRole() throws Exception {
		HashMap<String,Integer> map=new HashMap<>();
		map.put("userid", 4);
		map.put("roleid", 2);
		int i=roleDao.insertUserRole(map);
		System.out.println(i);
	}
}
