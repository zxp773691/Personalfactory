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

import edu.yuhf.dao.MaterialDao;
import edu.yuhf.dao.StoreDao;
import edu.yuhf.dao.UserDao;
import edu.yuhf.entity.Material;
import edu.yuhf.entity.Store;
import edu.yuhf.entity.StoreInfo;
import edu.yuhf.entity.User;

@Controller

public class UserController {

	@Resource
	UserDao userDao;

	@RequestMapping("/user")
	public ModelAndView user(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String param = request.getParameter("param");
		String id = request.getParameter("id");
		String userName=request.getParameter("userName");
		String userNo=request.getParameter("userNo");
		String password=request.getParameter("password");
		String realName=request.getParameter("realName");
		String telephone=request.getParameter("telephone");
		String idCard=request.getParameter("idCard");
		String registerTime=request.getParameter("registerTime");
		String state=request.getParameter("state");
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
				List<User> list = userDao.queryByName(newKeyword);
				PageInfo<User> pageInfo=new PageInfo<>(list);
				modeandview.getModel().put("pageInfo", pageInfo);
				modeandview.getModel().put("keyword", newKeyword);
				modeandview.setViewName("views/usermanager");
				return modeandview;
			}
			PageHelper.startPage(Integer.parseInt(currentPage),3,true);
			List<User> list = userDao.queryAll();
			PageInfo<User> pageInfo=new PageInfo<>(list);
			System.out.println(list.get(0).toString());
			modeandview.getModel().put("pageInfo", pageInfo);
			modeandview.getModel().put("keyword", keyword);
			modeandview.setViewName("views/usermanager");
			return modeandview;
		}
		if ("doUpdate".equals(param)) {
			int newState=0;
			if("离职".equals(state)){
				newState=0;
			}else if("正常".equals(state)){
				newState=1;
			}else if("休假".equals(state)){
				newState=2;
			}
			User user=new User(Integer.parseInt(id),userNo,userName,password,realName,telephone,idCard,registerTime,newState);
			userDao.update(user);
			pw.println("<script>alert('修改成功');window.location.href='user?param=init'</script>");
		}
		if("doDelete".equals(param)){
			String ids=request.getParameter("ids");
			String[] arrays=ids.split(",");
			int[] array=new int[arrays.length];
			for(int i=0;i<arrays.length;i++){
				array[i]=Integer.parseInt(arrays[i]);
			}
			userDao.delete(array);
			pw.println("<script>alert('删除成功');window.location.href='user?param=init'</script>");
		}
		if("doAdd".equals(param)){
			int newState=0;
			if("离职".equals(state)){
				newState=0;
			}else if("正常".equals(state)){
				newState=1;
			}else if("休假".equals(state)){
				newState=2;
			}
					User user0=new User(userNo,userName,password,realName,telephone,idCard,registerTime,newState);
					userDao.insert(user0);
			pw.println("<script>alert('添加成功');window.location.href='user?param=init'</script>");
		}
		if("addMenu".equals(param)){
			String userName1=new String(userName.getBytes("iso8859-1"),"utf-8");
			System.out.println(userName1+"==============");
			List<User> list=userDao.queryAll();
			int i=0;
			for(User user:list){
				if(user.getUsername().equals(userName1)){
					i++;
				}
			}
			if("".equals(userName.trim())){
				i=-1;
			}
			map.put("message", "操作完成");
			map.put("i", i);
			String json=JSONObject.toJSONString(map);
			pw.println(json);
		}
		if("query".equals(param)){
			User user=userDao.queryById(Integer.parseInt(id));
			map.put("message", "查询完成");
			map.put("user", user);
			String json=JSONObject.toJSONString(map);
			pw.println(json);
		}
		return null;
	}
}
