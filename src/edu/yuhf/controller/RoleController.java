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

import edu.yuhf.dao.RoleDao;
import edu.yuhf.entity.Material;
import edu.yuhf.entity.Role;
import edu.yuhf.entity.Store;
import edu.yuhf.entity.StoreInfo;

@Controller
public class RoleController {
	@Resource
	RoleDao roleDao;

	@RequestMapping("/role")

	public ModelAndView role(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String param = request.getParameter("param");
		String id = request.getParameter("id");
		String roleName=request.getParameter("roleName");
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
				List<Role> list = roleDao.queryByName(newKeyword);
				PageInfo<Role> pageInfo=new PageInfo<>(list);
				modeandview.getModel().put("pageInfo", pageInfo);
				modeandview.getModel().put("keyword", newKeyword);
				modeandview.setViewName("views/rolemanager");
				return modeandview;
			}
			PageHelper.startPage(Integer.parseInt(currentPage),3,true);
			List<Role> list = roleDao.queryAll();
			PageInfo<Role> pageInfo=new PageInfo<>(list);
			System.out.println(list.get(0).toString());
			modeandview.getModel().put("pageInfo", pageInfo);
			modeandview.getModel().put("keyword", keyword);
			modeandview.setViewName("views/rolemanager");
			return modeandview;
		}
		if ("doUpdate".equals(param)) {
			Role role=new Role(Integer.parseInt(id),roleName,remark);
			roleDao.update(role);
			pw.println("<script>alert('修改成功');window.location.href='role?param=init'</script>");
		}
		if("doDelete".equals(param)){
			String ids=request.getParameter("ids");
			String[] arrays=ids.split(",");
			int[] array=new int[arrays.length];
			for(int i=0;i<arrays.length;i++){
				array[i]=Integer.parseInt(arrays[i]);
			}
			roleDao.delete(array);
			pw.println("<script>alert('删除成功');window.location.href='role?param=init'</script>");
		}
		if("doAdd".equals(param)){
			Role role=new Role();
			role.setName(roleName);
			role.setRemark(remark);
			roleDao.insert(role);
			pw.println("<script>alert('添加成功');window.location.href='role?param=init'</script>");
		}
		if("addMenu".equals(param)){
			String roleName1=new String(roleName.getBytes("iso8859-1"),"utf-8");
			System.out.println(roleName1+"==============");
			List<Role> list=roleDao.queryAll();
			int i=0;
			for(Role role:list){
				if(role.getName().equals(roleName1)){
					i++;
				}
			}
			if("".equals(roleName.trim())){
				i=-1;
			}
			map.put("message", "操作完成");
			map.put("i", i);
			String json=JSONObject.toJSONString(map);
			pw.println(json);
		}
		if("query".equals(param)){
			Role role=roleDao.queryById(Integer.parseInt(id));
			map.put("message", "查询完成");
			map.put("role", role);
			String json=JSONObject.toJSONString(map);
			pw.println(json);
		}
		return null;
	}

}
