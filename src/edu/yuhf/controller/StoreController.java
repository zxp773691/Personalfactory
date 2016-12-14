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
import edu.yuhf.dao.VoucherDao;
import edu.yuhf.entity.Customer;
import edu.yuhf.entity.Material;
import edu.yuhf.entity.Store;
import edu.yuhf.entity.StoreInfo;
import edu.yuhf.entity.Voucher;

@Controller
public class StoreController {
	@Resource
	StoreDao storeDao;
	@Resource
	MaterialDao materialDao;
	@Resource
	VoucherDao voucherDao;
	@Resource
	CustomerDao customerDao;

	@RequestMapping("/store")

	public ModelAndView store(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String param = request.getParameter("param");
		String id = request.getParameter("id");
		String bususerid=request.getParameter("userid");
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
				List<StoreInfo> list = storeDao.queryByName(newKeyword);
				PageInfo<StoreInfo> pageInfo=new PageInfo<>(list);
				modeandview.getModel().put("pageInfo", pageInfo);
				modeandview.getModel().put("keyword", newKeyword);
				modeandview.setViewName("views/storemanager");
				return modeandview;
			}
			PageHelper.startPage(Integer.parseInt(currentPage),3,true);
			List<StoreInfo> list = storeDao.queryInfo();
			PageInfo<StoreInfo> pageInfo=new PageInfo<>(list);
			System.out.println(list.get(0).toString());
			modeandview.getModel().put("pageInfo", pageInfo);
			modeandview.getModel().put("keyword", keyword);
			modeandview.setViewName("views/storemanager");
			return modeandview;
		}
		if ("doUpdate".equals(param)) {
			String newweight = request.getParameter("newweight");
			Store store = storeDao.queryById(Integer.parseInt(id));
			store.setWeight(store.getWeight() + Double.parseDouble(newweight));
			System.out.println(store);
			storeDao.update(store);
			pw.println("<script>alert('修改成功');window.location.href='store?param=init'</script>");
		}
		if("doDelete".equals(param)){
			String ids=request.getParameter("ids");
			String[] arrays=ids.split(",");
			int[] array=new int[arrays.length];
			for(int i=0;i<arrays.length;i++){
				array[i]=Integer.parseInt(arrays[i]);
			}
			storeDao.delete(array);
			pw.println("<script>alert('删除成功');window.location.href='store?param=init'</script>");
		}
		if("doAdd".equals(param)){
			String storeName=request.getParameter("storeName");
			String weight=request.getParameter("weight");
			List<Material> list=materialDao.queryall();
			for(Material material:list){
				if(material.getName().equals(storeName)){
					Store store=new Store();
					store.setMaterialid(material.getId());
					store.setWeight(Double.parseDouble(weight));
					storeDao.insert(store);
				}
			}
			pw.println("<script>alert('添加成功');window.location.href='store?param=init'</script>");
		}
		if("addMenu".equals(param)){
			String storeName=request.getParameter("storeName");
			String storeName1=new String(storeName.getBytes("iso8859-1"),"utf-8");
			System.out.println(storeName1+"==============");
			List<Material> list=materialDao.queryall();
			int i=0;
			for(Material material:list){
				if(material.getName().equals(storeName1)){
					i++;
				}
			}
			if("".equals(storeName.trim())){
				i=-1;
			}
			map.put("message", "操作完成");
			map.put("i", i);
			String json=JSONObject.toJSONString(map);
			pw.println(json);
		}
		if("query".equals(param)){
			StoreInfo storeInfo=storeDao.queryInfoById(Integer.parseInt(id));
			map.put("message", "查询完成");
			map.put("storeInfo", storeInfo);
			String json=JSONObject.toJSONString(map);
			pw.println(json);
		}
		if("buymaterial".equals(param)){
			modeandview.getModel().put("bususerid", bususerid);
			List<Material> list=materialDao.queryMaterial();
			List<Customer> clist=customerDao.queryAll();
			modeandview.getModel().put("list", list);
			modeandview.getModel().put("clist", clist);
			modeandview.setViewName("views/buymaterial");
			return modeandview;
		}
		if("doBuyMaterial".equals(param)){
			System.out.println("doBuying...");
			String name=request.getParameter("name");
			String date=request.getParameter("date");
			String price=request.getParameter("price");
			String weight=request.getParameter("weight");
			String customername=request.getParameter("customername");
			Customer customer=customerDao.queryBySingleName(customername);
			Material material=materialDao.queryBySingleName(name);
			Voucher voucher=new Voucher();
			int i=voucherDao.countByOccurtime(date);
			String newI=String.format("%03d", i+1);
			String newDate=date.replace("-", "");
			voucher.setVoucherno(newDate+newI);
			voucher.setOccurtime(date);
			voucher.setBususerid(Integer.parseInt(bususerid));
			voucher.setCustomerid(customer.getId());
			voucher.setTotalprice(Double.parseDouble(price));
			voucher.setType(0);
			voucher.setWeight(Double.parseDouble(weight));
			voucher.setMaterialid(material.getId());
			System.out.println(voucher);
			System.out.println("voucher");
			voucherDao.buyMaterial(voucher);
			pw.println("<script>alert('添加成功');window.location.href='store?param=buymaterial&storeuserid="+bususerid+"'</script>");
		}
		if("sellflour".equals(param)){
			modeandview.getModel().put("bususerid", bususerid);
			List<Material> list=materialDao.queryall();
			System.out.println(list);
			modeandview.getModel().put("list", list);
			modeandview.setViewName("views/sellflour");
			return modeandview;
		}
		if("doSellFlour".equals(param)){
			String name=request.getParameter("name");
			String date=request.getParameter("date");
			String price=request.getParameter("price");
			String weight=request.getParameter("weight");
			String customername=request.getParameter("customername");
			Customer customer=customerDao.queryBySingleName(customername);
			Material material=materialDao.queryBySingleName(name);
			Voucher voucher=new Voucher();
			int i=voucherDao.countByOccurtime(date);
			String newI=String.format("%03d", i+1);
			String newDate=date.replace("-", "");
			voucher.setVoucherno(newDate+newI);
			voucher.setOccurtime(date);
			voucher.setBususerid(Integer.parseInt(bususerid));
			voucher.setCustomerid(customer.getId());
			voucher.setTotalprice(Double.parseDouble(price));
			voucher.setType(2);
			voucher.setWeight(Double.parseDouble(weight));
			voucher.setMaterialid(material.getId());
			System.out.println(voucher);
			System.out.println("voucher");
			voucherDao.buyMaterial(voucher);
			pw.println("<script>alert('添加成功');window.location.href='store?param=sellflour&storeuserid="+bususerid+"'</script>");
		}
		return null;
	}

}
