package com.cyhz.web.shopadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cyhz.entity.PersonInfo;
import com.cyhz.service.impl.ShopServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sun.org.apache.regexp.internal.REUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.cyhz.dto.ShopExecution;
import com.cyhz.entity.Area;
import com.cyhz.entity.Shop;
import com.cyhz.entity.ShopCategory;
import com.cyhz.enums.ShopStateEnum;
import com.cyhz.service.AreaService;
import com.cyhz.service.ShopCategoryService;
import com.cyhz.service.ShopService;
import com.cyhz.util.CodeUtil;
import com.cyhz.util.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/shopadmin")
public class ShopManagementController {

	@Autowired
	private ShopService shopService;
	
	@Autowired
	private ShopCategoryService shopCategoryService;
	
	@Autowired
	private AreaService areaService;

	@GetMapping("getshopmanagementinfo")
	private Map<String,Object> getShopManagementInfo(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<>();
		long shopId=HttpServletRequestUtil.getLong(request,"shopId");
		if (shopId <= 0) {
			Object currentShopObj = request.getSession().getAttribute("currentShop");
			if (currentShopObj==null) {
				modelMap.put("redirect",true);
				modelMap.put("url","/newshop/superadmin/shoplist");
			}else{
				Shop currentShop = (Shop) currentShopObj;
				modelMap.put("redirect", false);
				modelMap.put("shopId", currentShop.getShopId());
			}
		}else {
			Shop currentShop=new Shop();
			currentShop.setShopId(shopId);
			request.getSession().setAttribute("currentShop",currentShop);
			modelMap.put("redirect",false);
		}
		return modelMap;
	}

	@GetMapping("getshoplist")
	private Map<String,Object> getShopList(HttpServletRequest request){
		Map<String,Object> modelMap=new HashMap<>();
		PersonInfo user=new PersonInfo();
		user.setUserId(8L);
		user.setName("小文文");
		request.getSession().setAttribute("user",user);
		user= (PersonInfo) request.getSession().getAttribute("user");
		long employeeId=user.getUserId();
		try{
			Shop shopCondition=new Shop();
			shopCondition.setOwnerId(user.getUserId());
			ShopExecution se= shopService.queryShopList(shopCondition);
			modelMap.put("shopList",se.getShopList());
			modelMap.put("user",user);
			modelMap.put("success", true);
		}catch (Exception e){
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
		}
		return modelMap;
	}

	@GetMapping("getshopbyid")
	private Map<String,Object> getShopById(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<>();
		Long shopId=HttpServletRequestUtil.getLong(request,"shopId");
		if (shopId>-1) {
			try{
				Shop shop = shopService.getByShopId(shopId);
				List<Area> areaList = areaService.getAreaList();
				modelMap.put("shop",shop);
				modelMap.put("areaList",areaList);
				modelMap.put("success",true);
			}catch (Exception e){
				modelMap.put("success",false);
				modelMap.put("errMsg",e.getMessage());
				return modelMap;
			}
		}
		return modelMap;
	}
	
	@GetMapping("/getshopinitinfo")
	public Map<String,Object> getShopInitInfo(){
		Map<String,Object> modelMap=new HashMap<>();
		List<ShopCategory> shopCategoryList=new ArrayList<>();
		List<Area> areaList=new ArrayList<>();
		try {
			shopCategoryList=shopCategoryService.queryShopCategory(new ShopCategory());
			areaList=areaService.getAreaList();
			modelMap.put("success", true);
			modelMap.put("areaList", areaList);
			modelMap.put("shopCategoryList", shopCategoryList);
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	@PostMapping("/registershop")
	private Map<String, Object> registerShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		if(!CodeUtil.checkVenifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		// 1.接受转化相应的参数，包括店铺信息以及图片信息
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传图片不能为空");
			return modelMap;
		}
		// 2.注册店铺
		if (shop != null && shopImg != null) {
			PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
			shop.setOwnerId(user.getUserId());
			ShopExecution se;
			try {
				se = shopService.addShop(shop, shopImg.getInputStream(),shopImg.getOriginalFilename());
				if(se.getState()==ShopStateEnum.CHECK.getState()) {
					modelMap.put("success", true);
					List<Shop> shopList=(List<Shop>)request.getSession().getAttribute("shopList");
					if (shopList==null||shopList.size()==0) {
						shopList=new ArrayList<>();
					}
					shopList.add(se.getShop());
					request.getSession().setAttribute("shopList",shopList);
				}else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺信息");
			return modelMap;
		}
		// 3.返回结果
	}

	@PostMapping("/modifyshop")
	private Map<String, Object> modifyShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		if(!CodeUtil.checkVenifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		// 1.接受转化相应的参数，包括店铺信息以及图片信息
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		}
		// 2.修改店铺信息
		if (shop != null && shop.getShopId()!=null) {
			ShopExecution se;
			try {
				if (shopImg==null){
					se = shopService.modifyShop(shop, null,null);
				}else {
					se = shopService.modifyShop(shop, shopImg.getInputStream(),shopImg.getOriginalFilename());
				}
				if(se.getState()==ShopStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				}else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺Id");
			return modelMap;
		}
		// 3.返回结果
	}

	/*// 把CommonsMultipartFile转换成File
	public static void inputStreamToFile(InputStream ins, File file) {
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = ins.read(buffer)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
		} catch (Exception e) {
			throw new RuntimeException("调用inputStreamToFile产生异常" + e.getMessage());
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				if (ins != null) {
					ins.close();
				}
			} catch (Exception e) {
				throw new RuntimeException("inputStreamToFile关闭io异常"+e.getMessage());
			}
		}
	}*/
}
