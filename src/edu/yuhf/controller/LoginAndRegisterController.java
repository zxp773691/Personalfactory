package edu.yuhf.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.yuhf.dao.PermissionDao;
import edu.yuhf.dao.UserDao;
import edu.yuhf.entity.Permission;
import edu.yuhf.entity.User;

@Controller
public class LoginAndRegisterController{
	@Resource
	UserDao userDao;
	@Resource 
	PermissionDao permissionDao;
	
	@RequestMapping("/lar")
	public ModelAndView lar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String param=request.getParameter("param");
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		
		Map<Integer,Permission> map=new HashMap<>();
		
		ModelAndView modeandview = new ModelAndView();
		System.out.println("lar  ok");
		
		if("login".equals(param)){
			List<User> list=userDao.queryAll();
			for(User user:list){
				if(user.getUsername().equals(userName)&&user.getPassword().equals(password)){
					List<Permission> perList=permissionDao.getPermission(user.getId());
					for(Permission permission:perList){
						map.put(permission.getId(), permission);
					}
					System.out.println(map);
					modeandview.getModel().put("user", user);
					modeandview.getModel().put("map", map);
					modeandview.setViewName("main");
					return modeandview;
				}
			}
			modeandview.getModel().put("message", "username or password error");
			modeandview.setViewName("login");
			return modeandview;
		}
		return modeandview;
	}
}
