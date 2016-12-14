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
import edu.yuhf.entity.Customer;
import edu.yuhf.entity.User;

@Controller

public class CustomerController {

	@Resource
	CustomerDao customerDao;

	@RequestMapping("/customer")
	public ModelAndView user(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String param = request.getParameter("param");
		String id = request.getParameter("id");
		String name=request.getParameter("name");
		String customerno=request.getParameter("customerno");
		String address=request.getParameter("address");
		String telephone=request.getParameter("telephone");
		String idcard=request.getParameter("idcard");
		String registertime=request.getParameter("registertime");
		String state=request.getParameter("state");
		String remark=request.getParameter("remark");
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
				List<Customer> list = customerDao.queryByName(newKeyword);
				PageInfo<Customer> pageInfo=new PageInfo<>(list);
				modeandview.getModel().put("pageInfo", pageInfo);
				modeandview.getModel().put("keyword", newKeyword);
				modeandview.setViewName("views/customermanager");
				return modeandview;
			}
			PageHelper.startPage(Integer.parseInt(currentPage),3,true);
			List<Customer> list = customerDao.queryAll();
			PageInfo<Customer> pageInfo=new PageInfo<>(list);
			System.out.println(list.get(0).toString());
			modeandview.getModel().put("pageInfo", pageInfo);
			modeandview.getModel().put("keyword", keyword);
			modeandview.setViewName("views/customermanager");
			return modeandview;
		}
		if ("doUpdate".equals(param)) {
			int newState=0;
			if("黑名单".equals(state)){
				newState=0;
			}else if("正常".equals(state)){
				newState=1;
			}
			Customer customer=new Customer(Integer.parseInt(id),customerno,name,address,telephone,idcard,registertime,remark,newState);
			customerDao.update(customer);
			pw.println("<script>alert('修改成功');window.location.href='customer?param=init'</script>");
		}
		if("doDelete".equals(param)){
			String ids=request.getParameter("ids");
			String[] arrays=ids.split(",");
			int[] array=new int[arrays.length];
			for(int i=0;i<arrays.length;i++){
				array[i]=Integer.parseInt(arrays[i]);
			}
			customerDao.delete(array);
			pw.println("<script>alert('删除成功');window.location.href='customer?param=init'</script>");
		}
		if("doAdd".equals(param)){
			int newState=0;
			if("黑名单".equals(state)){
				newState=0;
			}else if("正常".equals(state)){
				newState=1;
			}
					Customer customer=new Customer(0,customerno,name,address,telephone,idcard,registertime,remark,newState);
					customerDao.insert(customer);
			pw.println("<script>alert('添加成功');window.location.href='customer?param=init'</script>");
		}
		if("addMenu".equals(param)){
			String name1=new String(name.getBytes("iso8859-1"),"utf-8");
			List<Customer> list=customerDao.queryAll();
			int i=0;
			for(Customer customer:list){
				if(customer.getName().equals(name1)){
					i++;
				}
			}
			if("".equals(name.trim())){
				i=-1;
			}
			map.put("message", "操作完成");
			map.put("i", i);
			String json=JSONObject.toJSONString(map);
			pw.println(json);
		}
		if("query".equals(param)){
			Customer customer=customerDao.queryById(Integer.parseInt(id));
			map.put("message", "查询完成");
			map.put("customer", customer);
			String json=JSONObject.toJSONString(map);
			pw.println(json);
		}
		return null;
	}
}
