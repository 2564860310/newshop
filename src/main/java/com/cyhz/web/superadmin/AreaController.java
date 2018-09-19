package com.cyhz.web.superadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyhz.entity.Area;
import com.cyhz.service.AreaService;

@Controller
@RequestMapping("/superadmin")
public class AreaController {
	@Autowired
	private AreaService areaService;
	
	@GetMapping("shopedit")
	public String shopEdit() {
		return "shop/shopedit";
	}

	@GetMapping("shoplist")
	public String shopList(){
		return "shop/shoplist";
	}

	@GetMapping("shopmanage")
	public String shopManage(){
		return "shop/shopmanage";
	}

	@GetMapping("productcategorymanage")
	private String productCatgoryManage(){
		return "shop/productcategorymanage";
	}

	@GetMapping(value = "listarea")
	@ResponseBody
	private Map<String, Object> listAreas() {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<Area> list = new ArrayList<>();
		try {
			list = areaService.getAreaList();
			modelMap.put("row", list);
			modelMap.put("total", list.size());
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		return modelMap;
	}

}
