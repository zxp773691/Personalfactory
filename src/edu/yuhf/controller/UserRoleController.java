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

import edu.yuhf.dao.RoleDao;
import edu.yuhf.dao.UserDao;
import edu.yuhf.entity.Role;
import edu.yuhf.entity.User;

@Controller

public class UserRoleController {

	@Resource
	RoleDao roleDao;
	@Resource
	UserDao userDao;

	@RequestMapping("/userRole")
	public ModelAndView user(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String param=request.getParameter("param");
		String id=request.getParameter("id");
		String[] checks=request.getParameterValues("checkName");
		PrintWriter pw=response.getWriter();
		ModelAndView modeandview = new ModelAndView();
		if("update".equals(param)){
			List<Role> list=roleDao.queryAll();
			Map<Integer,Role> allMap=new HashMap<>();
			for(Role role:list){
				role.setChecked(false);
				allMap.put(role.getId(), role);
			}
			List<Role> list1=roleDao.queryByUserId(Integer.parseInt(id));
			Map<Integer,Role> checkedMap=new HashMap<>();
			for(Role role:list1){
				role.setChecked(true);
				checkedMap.put(role.getId(), role);
			}
			User user=userDao.queryById(Integer.parseInt(id));
			allMap.putAll(checkedMap);
			modeandview.getModel().put("map", allMap);
			modeandview.getModel().put("user", user);
			modeandview.setViewName("views/userRole");
			return modeandview;
		}
		if("doUpdate".equals(param)){
			roleDao.deleteUserRole(Integer.valueOf(id));
			if(null==checks){
				pw.print("<script>alert('user role update success!');window.location.href='user?param=init'</script>");
			}
			for(String string:checks){
				HashMap<String,Integer> map=new HashMap<>();
				map.put("userid", Integer.parseInt(id));
				map.put("roleid", Integer.parseInt(string));
				roleDao.insertUserRole(map);
			}
				pw.print("<script>alert('user role update success!');window.location.href='user?param=init'</script>");

		}
		return null;
}
}
