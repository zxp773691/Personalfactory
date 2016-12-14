package edu.yuhf.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import edu.yuhf.dao.CustomerDao;
import edu.yuhf.dao.MaterialDao;
import edu.yuhf.dao.StoreDao;
import edu.yuhf.dao.UserDao;
import edu.yuhf.dao.VoucherDao;
import edu.yuhf.entity.Customer;
import edu.yuhf.entity.Material;
import edu.yuhf.entity.Store;
import edu.yuhf.entity.User;
import edu.yuhf.entity.Voucher;

@Controller
public class VoucherController {
	@Resource
	VoucherDao voucherDao;
	@Resource
	StoreDao storeDao;
	@Resource
	CustomerDao customerDao;
	@Resource
	UserDao userDao;
	@Resource
	MaterialDao materialDao;

	@RequestMapping("/voucher")

	public ModelAndView role(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String param = request.getParameter("param");
		String id = request.getParameter("id");
		String voucherno=request.getParameter("voucherno");
		String type=request.getParameter("type");
		String occurtime=request.getParameter("occurtime");
		String weight=request.getParameter("weight");
		String totalprice=request.getParameter("totalprice");
		String materialname=request.getParameter("materialname");
		String customername=request.getParameter("customername");
		String bususername=request.getParameter("bususername");
		String storeusername=request.getParameter("storeusername");
		Map<String,Object> map=new HashMap<>();
		PrintWriter pw = response.getWriter();

		String currentPage = null == request.getParameter("currentPage") ? "" : request.getParameter("currentPage");
		String keyword = null == request.getParameter("keyword") ? "" : request.getParameter("keyword");

		ModelAndView modeandview = new ModelAndView();
		if ("init".equals(param)) {
			if("".equals(currentPage)){
				currentPage="1";
			}
			String newKeyword="";
			if(!"".equals(keyword)){
				newKeyword=new String(keyword.getBytes("ISO8859-1"),"UTF-8");
				PageHelper.startPage(Integer.parseInt(currentPage),3,true);
				Customer customer=customerDao.queryBySingleName(newKeyword);
				if(null==customer){
					List<Voucher> list = voucherDao.queryByCustomerId(0);
					PageInfo<Voucher> pageInfo=new PageInfo<>(list);
					modeandview.getModel().put("pageInfo", pageInfo);
					modeandview.getModel().put("keyword", newKeyword);
					modeandview.setViewName("views/vouchermanager");
					return modeandview;
				}
				List<Voucher> list = voucherDao.queryByCustomerId(customer.getId());
				PageInfo<Voucher> pageInfo=new PageInfo<>(list);
				modeandview.getModel().put("pageInfo", pageInfo);
				modeandview.getModel().put("keyword", newKeyword);
				modeandview.setViewName("views/vouchermanager");
				return modeandview;
			}
			PageHelper.startPage(Integer.parseInt(currentPage),3,true);
			List<Voucher> list = voucherDao.queryAll();
			PageInfo<Voucher> pageInfo=new PageInfo<>(list);
			modeandview.getModel().put("pageInfo", pageInfo);
			modeandview.getModel().put("keyword", keyword);
			modeandview.setViewName("views/vouchermanager");
			return modeandview;
		}
		if ("doUpdate".equals(param)) {
			int newType=0;
			Store store=storeDao.queryBySingleName(materialname);
			Customer customer=customerDao.queryBySingleName(customername);
			User bususer=userDao.queryBySingleName(bususername);
			User storeuser=userDao.queryBySingleName(storeusername);
			if("购买原料".equals(type)){
				newType=0;
			}else if("出售原料".equals(type)){
				newType=1;
			}else if("出售面粉".equals(type)){
				newType=2;
			}
			Voucher voucher=new Voucher(Integer.parseInt(id),voucherno,newType,occurtime,Double.parseDouble(weight),Double.parseDouble(totalprice),store.getMaterialid(),customer.getId(),bususer.getId(),storeuser.getId());
			voucherDao.update(voucher);
			pw.println("<script>alert('修改成功');window.location.href='voucher?param=init'</script>");
		}
		if("doDelete".equals(param)){
			String ids=request.getParameter("ids");
			String[] arrays=ids.split(",");
			int[] array=new int[arrays.length];
			for(int i=0;i<arrays.length;i++){
				array[i]=Integer.parseInt(arrays[i]);
			}
			voucherDao.delete(array);
			pw.println("<script>alert('删除成功');window.location.href='voucher?param=init'</script>");
		}
		if("doAdd".equals(param)){
			int newType=0;
			Store store=storeDao.queryBySingleName(materialname);
			Customer customer=customerDao.queryBySingleName(customername);
			User bususer=userDao.queryBySingleName(bususername);
			User storeuser=userDao.queryBySingleName(storeusername);
			if("购买原料".equals(type)){
				newType=0;
			}else if("出售原料".equals(type)){
				newType=1;
			}else if("出售面粉".equals(type)){
				newType=2;
			}
			Voucher voucher=new Voucher(0,voucherno,newType,occurtime,Double.parseDouble(weight),Double.parseDouble(totalprice),store.getMaterialid(),customer.getId(),bususer.getId(),storeuser.getId());
			voucherDao.insert(voucher);
			pw.println("<script>alert('添加成功');window.location.href='voucher?param=init'</script>");
		}
		if("addMenu".equals(param)){
			String voucherno1=new String(voucherno.getBytes("iso8859-1"),"utf-8");
			System.out.println(voucherno1+"==============");
			List<Voucher> list=voucherDao.queryAll();
			int i=0;
			for(Voucher voucher:list){
				if(voucher.getVoucherno().equals(voucherno1)){
					i++;
				}
			}
			if("".equals(voucherno.trim())){
				i=-1;
			}
			map.put("message", "操作完成");
			map.put("i", i);
			String json=JSONObject.toJSONString(map);
			pw.println(json);
		}
		if("query".equals(param)){
			Voucher voucher=voucherDao.queryById(Integer.parseInt(id));
			Material material=materialDao.queryById(voucher.getMaterialid());
			Customer customer=customerDao.queryById(voucher.getCustomerid());
			User bususer=userDao.queryById(voucher.getBususerid());
			User storeuser=userDao.queryById(voucher.getStoreuserid());
			map.put("message", "查询完成");
			map.put("voucher", voucher);
			map.put("material", material);
			map.put("customer",customer);
			map.put("bususer", bususer);
			map.put("storeuser", storeuser);
			String json=JSONObject.toJSONString(map);
			pw.println(json);
		}
		if("makesure".equals(param)){
			String bususerid=request.getParameter("userid");
			System.out.println("bususerid:"+bususerid);
			if("".equals(currentPage)){
				currentPage="1";
			}
			String newKeyword="";
			if(!"".equals(keyword)){
				newKeyword=new String(keyword.getBytes("ISO8859-1"),"UTF-8");
				PageHelper.startPage(Integer.parseInt(currentPage),3,true);
				Customer customer=customerDao.queryBySingleName(newKeyword);
				if(null==customer){
					List<Voucher> list = voucherDao.queryByCrIdAndBrNull(0);
					PageInfo<Voucher> pageInfo=new PageInfo<>(list);
					modeandview.getModel().put("pageInfo", pageInfo);
					modeandview.getModel().put("keyword", newKeyword);
					modeandview.getModel().put("bususerid", bususerid);
					modeandview.setViewName("views/vouchermakesure");
					return modeandview;
				}
				List<Voucher> list = voucherDao.queryByCrIdAndBrNull(customer.getId());
				PageInfo<Voucher> pageInfo=new PageInfo<>(list);
				modeandview.getModel().put("pageInfo", pageInfo);
				modeandview.getModel().put("keyword", newKeyword);
				modeandview.getModel().put("bususerid", bususerid);
				modeandview.setViewName("views/vouchermakesure");
				return modeandview;
			}
			PageHelper.startPage(Integer.parseInt(currentPage),3,true);
			List<Voucher> list = voucherDao.queryStoreUserNull();
			PageInfo<Voucher> pageInfo=new PageInfo<>(list);
			modeandview.getModel().put("pageInfo", pageInfo);
			modeandview.getModel().put("keyword", keyword);
			modeandview.getModel().put("bususerid", bususerid);
			modeandview.setViewName("views/vouchermakesure");
			return modeandview;
		}
		if("updatestoreuser".equals(param)){
			String userid=request.getParameter("userid");
			Voucher voucher=voucherDao.queryById(Integer.parseInt(id));
			System.out.println(userid+"===="+voucher);
			voucher.setStoreuserid(Integer.parseInt(userid));
			System.out.println("newVoucher:"+voucher);
			if(voucher.getType()==0){
				Store store=storeDao.queryByMaterialId(voucher.getMaterialid());
				if(null==store){
					Store store1=new Store();
					store1.setMaterialid(voucher.getMaterialid());
					store1.setWeight(voucher.getWeight());
					storeDao.insert(store1);
				}else{
					store.setWeight(store.getWeight()+voucher.getWeight());
					storeDao.update(store);
				}
			}else if(voucher.getType()==2){
				Store store=storeDao.queryByMaterialId(voucher.getMaterialid());
				if(store.getWeight()<voucher.getWeight()){
					pw.println("<script>alert('库存不足，请补充');window.location.href='voucher?param=makesure&userid="+userid+"'</script>");
					return null;
				}else{
					store.setWeight(store.getWeight()-voucher.getWeight());
					storeDao.update(store);
				}
			}
			voucherDao.update(voucher);
			pw.println("<script>alert('确认成功');window.location.href='voucher?param=makesure&userid="+userid+"'</script>");
		}
		return null;
	}

}
