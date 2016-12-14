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

import edu.yuhf.dao.PermissionDao;
import edu.yuhf.dao.RoleDao;
import edu.yuhf.entity.Permission;
import edu.yuhf.entity.Role;
import edu.yuhf.entity.User;

@Controller

public class RolePermissionController {

	@Resource
	RoleDao roleDao;
	@Resource
	PermissionDao permissionDao;

	@RequestMapping("/rolePermission")
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
			List<Permission> list=permissionDao.queryAll();
			Map<Integer,Permission> allMap=new HashMap<>();
			for(Permission permission:list){
				permission.setChecked(false);
				allMap.put(permission.getId(), permission);
			}
			List<Permission> list1=permissionDao.queryByRoleId(Integer.parseInt(id));
			Map<Integer,Permission> checkedMap=new HashMap<>();
			for(Permission permission:list1){
				permission.setChecked(true);
				checkedMap.put(permission.getId(), permission);
			}
			Role role=roleDao.queryById(Integer.parseInt(id));
			allMap.putAll(checkedMap);
			modeandview.getModel().put("map", allMap);
			modeandview.getModel().put("role", role);
			modeandview.setViewName("views/rolePermission");
			return modeandview;
		}
		if("doUpdate".equals(param)){
			permissionDao.deleteRolePermission(Integer.valueOf(id));
			if(null==checks){
				pw.print("<script>alert('role permission update success!');window.location.href='role?param=init'</script>");
			}
			for(String string:checks){
				HashMap<String,Integer> map=new HashMap<>();
				map.put("permissionid", Integer.parseInt(string));
				map.put("roleid", Integer.parseInt(id));
				permissionDao.insertRolePermission(map);
			}
				pw.print("<script>alert('role permission update success!');window.location.href='role?param=init'</script>");

		}
		return null;
}
}
