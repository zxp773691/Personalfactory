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
import edu.yuhf.entity.Material;
import edu.yuhf.entity.Permission;

@Controller
public class MaterialController {
	@Resource
	MaterialDao materialDao;

	@RequestMapping("/material")

	public ModelAndView role(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String param = request.getParameter("param");
		String id = request.getParameter("id");
		String name=request.getParameter("name");
		String rate=request.getParameter("rate");
		String type=request.getParameter("type");
		String price=request.getParameter("price");
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
				List<Material> list = materialDao.queryByName(newKeyword);
				PageInfo<Material> pageInfo=new PageInfo<>(list);
				modeandview.getModel().put("pageInfo", pageInfo);
				modeandview.getModel().put("keyword", newKeyword);
				modeandview.setViewName("views/materialmanager");
				return modeandview;
			}
			PageHelper.startPage(Integer.parseInt(currentPage),3,true);
			List<Material> list = materialDao.queryall();
			PageInfo<Material> pageInfo=new PageInfo<>(list);
			modeandview.getModel().put("pageInfo", pageInfo);
			modeandview.getModel().put("keyword", keyword);
			modeandview.setViewName("views/materialmanager");
			return modeandview;
		}
		if ("doUpdate".equals(param)) {
			int newType=0,newState=0;
			if("原料".equals(type)){
				newType=0;
			}else{
				newType=1;
			}
			if("停用".equals(state)){
				newState=0;
			}else{
				newState=1;
			}
			Material material=new Material(Integer.parseInt(id),name,Double.parseDouble(rate),newType,newState,Double.parseDouble(price));
			materialDao.update(material);
			pw.println("<script>alert('修改成功');window.location.href='material?param=init'</script>");
		}
		if("doDelete".equals(param)){
			String ids=request.getParameter("ids");
			String[] arrays=ids.split(",");
			int[] array=new int[arrays.length];
			for(int i=0;i<arrays.length;i++){
				array[i]=Integer.parseInt(arrays[i]);
			}
			materialDao.delete(array);
			pw.println("<script>alert('删除成功');window.location.href='material?param=init'</script>");
		}
		if("doAdd".equals(param)){
			int newType=0,newState=0;
			if("原料".equals(type)){
				newType=0;
			}else{
				newType=1;
			}
			if("停用".equals(state)){
				newState=0;
			}else{
				newState=1;
			}
			Material material=new Material(0,name,Double.parseDouble(rate),newType,newState,Double.parseDouble(price));
			materialDao.insert(material);
			pw.println("<script>alert('添加成功');window.location.href='material?param=init'</script>");
		}
		if("addMenu".equals(param)){
			String name1=new String(name.getBytes("iso8859-1"),"utf-8");
			System.out.println(name1+"==============");
			List<Material> list=materialDao.queryall();
			int i=0;
			for(Material material:list){
				if(material.getName().equals(name1)){
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
			Material material=materialDao.queryById(Integer.parseInt(id));
			map.put("message", "查询完成");
			map.put("material", material);
			String json=JSONObject.toJSONString(map);
			pw.println(json);
		}
		if("queryByName".equals(param)){
			String newName=new String(name.getBytes("ISO8859-1"),"UTF-8");
			Material material=materialDao.queryBySingleName(newName);
			map.put("message", "查询完成");
			map.put("material", material);
			String json=JSONObject.toJSONString(map);
			pw.println(json);
		}
		return null;
	}

}
