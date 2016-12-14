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

import edu.yuhf.dao.PermissionDao;
import edu.yuhf.entity.Permission;
import edu.yuhf.entity.Role;

@Controller
public class PermissionController {
	@Resource
	PermissionDao permissionDao;

	@RequestMapping("/permission")

	public ModelAndView role(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String param = request.getParameter("param");
		String id = request.getParameter("id");
		String name=request.getParameter("name");
		String url=request.getParameter("url");
		String remark=request.getParameter("remark");
		String type=request.getParameter("type");
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
				List<Permission> list = permissionDao.queryByName(newKeyword);
				PageInfo<Permission> pageInfo=new PageInfo<>(list);
				modeandview.getModel().put("pageInfo", pageInfo);
				modeandview.getModel().put("keyword", newKeyword);
				modeandview.setViewName("views/permissionmanager");
				return modeandview;
			}
			PageHelper.startPage(Integer.parseInt(currentPage),3,true);
			List<Permission> list = permissionDao.queryAll();
			PageInfo<Permission> pageInfo=new PageInfo<>(list);
			modeandview.getModel().put("pageInfo", pageInfo);
			modeandview.getModel().put("keyword", keyword);
			modeandview.setViewName("views/permissionmanager");
			return modeandview;
		}
		if ("doUpdate".equals(param)) {
			Permission permission=new Permission(Integer.parseInt(id),name,url,remark,Integer.parseInt(type));
			permissionDao.update(permission);
			pw.println("<script>alert('修改成功');window.location.href='permission?param=init'</script>");
		}
		if("doDelete".equals(param)){
			String ids=request.getParameter("ids");
			String[] arrays=ids.split(",");
			int[] array=new int[arrays.length];
			for(int i=0;i<arrays.length;i++){
				array[i]=Integer.parseInt(arrays[i]);
			}
			permissionDao.delete(array);
			pw.println("<script>alert('删除成功');window.location.href='permission?param=init'</script>");
		}
		if("doAdd".equals(param)){
			Permission permission=new Permission();
			permission.setName(name);
			permission.setUrl(url);
			permission.setRemark(remark);
			permission.setType(Integer.parseInt(type));
			permissionDao.insert(permission);
			pw.println("<script>alert('添加成功');window.location.href='permission?param=init'</script>");
		}
		if("addMenu".equals(param)){
			String name1=new String(name.getBytes("iso8859-1"),"utf-8");
			System.out.println(name1+"==============");
			List<Permission> list=permissionDao.queryAll();
			int i=0;
			for(Permission permission:list){
				if(permission.getName().equals(name1)){
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
			Permission permission=permissionDao.queryById(Integer.parseInt(id));
			map.put("message", "查询完成");
			map.put("permission", permission);
			String json=JSONObject.toJSONString(map);
			pw.println(json);
		}
		return null;
	}

}
