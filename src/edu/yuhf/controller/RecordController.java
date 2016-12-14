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
import edu.yuhf.dao.RecordDao;
import edu.yuhf.dao.StoreDao;
import edu.yuhf.entity.Material;
import edu.yuhf.entity.Record;
import edu.yuhf.entity.RecordInfo;
import edu.yuhf.entity.Store;

@Controller
public class RecordController {
	@Resource
	RecordDao recordDao;
	@Resource
	MaterialDao materialDao;
	@Resource
	StoreDao storeDao;

	@RequestMapping("/record")

	public ModelAndView role(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String param = request.getParameter("param");
		String id = request.getParameter("id");
		String processtime=request.getParameter("processtime");
		String mlname=request.getParameter("mlname");
		String frname=request.getParameter("frname");
		String mlweight=request.getParameter("mlweight");
		String frweight=request.getParameter("frweight");
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
				List<RecordInfo> list = recordDao.queryByFrName(newKeyword);
				PageInfo<RecordInfo> pageInfo=new PageInfo<>(list);
				modeandview.getModel().put("pageInfo", pageInfo);
				modeandview.getModel().put("keyword", newKeyword);
				modeandview.setViewName("views/recordmanager");
				return modeandview;
			}
			PageHelper.startPage(Integer.parseInt(currentPage),3,true);
			List<RecordInfo> list = recordDao.queryRecordInfo();
			PageInfo<RecordInfo> pageInfo=new PageInfo<>(list);
			modeandview.getModel().put("pageInfo", pageInfo);
			modeandview.getModel().put("keyword", keyword);
			modeandview.setViewName("views/recordmanager");
			return modeandview;
		}
		if ("doUpdate".equals(param)) {
			Material material=materialDao.queryBySingleName(mlname);
			Material flour=materialDao.queryBySingleName(frname);
			Record record=new Record(Integer.parseInt(id),processtime,Double.parseDouble(mlweight),Double.parseDouble(frweight),material.getId(),flour.getId());
			recordDao.update(record);
			pw.println("<script>alert('修改成功');window.location.href='record?param=init'</script>");
		}
		if("doDelete".equals(param)){
			String ids=request.getParameter("ids");
			String[] arrays=ids.split(",");
			int[] array=new int[arrays.length];
			for(int i=0;i<arrays.length;i++){
				array[i]=Integer.parseInt(arrays[i]);
			}
			recordDao.delete(array);
			pw.println("<script>alert('删除成功');window.location.href='record?param=init'</script>");
		}
		if("doAdd".equals(param)){
			Material material=materialDao.queryBySingleName(mlname);
			Material flour=materialDao.queryBySingleName(frname);
			Record record=new Record(0,processtime,Double.parseDouble(mlweight),Double.parseDouble(frweight),material.getId(),flour.getId());
			recordDao.insert(record);
			pw.println("<script>alert('添加成功');window.location.href='record?param=init'</script>");
		}
		if("query".equals(param)){
			RecordInfo recordInfo=recordDao.queryInfoById(Integer.parseInt(id));
			map.put("message", "查询完成");
			map.put("recordInfo", recordInfo);
			String json=JSONObject.toJSONString(map);
			pw.println(json);
		}
		if("mlweightChange".equals(param)){
			String newMlname=new String(mlname.getBytes("ISO8859-1"),"UTF-8");
			Material material=materialDao.queryBySingleName(newMlname);
			map.put("message", "查询完成");
			map.put("material", material);
			System.out.println(material);
			String json=JSONObject.toJSONString(map);
			pw.println(json);
		}
		if("process".equals(param)){
			modeandview.setViewName("views/processmaterial");
			return modeandview;
		}
		if("doProcess".equals(param)){
			Material material=materialDao.queryBySingleName(mlname);
			Material flour=materialDao.queryBySingleName(frname);
			Store mlstore=storeDao.queryByMaterialId(material.getId());
			Store frstore=storeDao.queryByMaterialId(flour.getId());
			if(mlstore.getWeight()<Double.parseDouble(mlweight)){
				pw.println("<script>alert('原料不足，请补充');window.location.href='record?param=process'</script>");
				return null;
			}
			Record record=new Record(0,processtime,Double.parseDouble(mlweight),Double.parseDouble(frweight),material.getId(),flour.getId());
			recordDao.insert(record);
			mlstore.setWeight(mlstore.getWeight()-Double.parseDouble(mlweight));
			frstore.setWeight(frstore.getWeight()+Double.parseDouble(frweight));
			storeDao.update(mlstore);
			storeDao.update(frstore);
			pw.println("<script>alert('添加成功');window.location.href='record?param=init'</script>");
		}
		return null;
	}

}
