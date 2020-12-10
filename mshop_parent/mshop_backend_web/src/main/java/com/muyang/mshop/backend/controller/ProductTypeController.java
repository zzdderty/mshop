package com.muyang.mshop.backend.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.muyang.mshop.constant.PaginationConstant;
import com.muyang.mshop.constant.ResponseStatusConstant;
import com.muyang.mshop.exception.ProductTypeExistException;
import com.muyang.mshop.pojo.ProductType;
import com.muyang.mshop.service.ProductTypeService;
import com.muyang.mshop.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/backend/productType")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    //查询所有
    @RequestMapping("/findAll")
    public String findAll(Integer pageNum,Model model){
    //默认显示第一页
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum= PaginationConstant.PAGE_NUM;
        }
    //设置分页，当前显示第几页
        PageHelper.startPage(pageNum,PaginationConstant.PAGE_SIZE);
    //查找数据
        List<ProductType> productTypes = productTypeService.findAll();
    //将查找的数据放到pageInfo对象中去
        PageInfo<ProductType> pageInfo=new PageInfo<>(productTypes);
        model.addAttribute("pageInfo",pageInfo);
        return "productTypeManager";
    }

    //添加
    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(String name){
        ResponseResult result=new ResponseResult();
        try{
            productTypeService.add(name);
            result.setStatus(ResponseStatusConstant.RESPONSE_STATUS_SUCCESS);
            result.setMessage("添加成功");
        }
        catch (ProductTypeExistException e)
        {
            result.setStatus(ResponseStatusConstant.RESPONSE_STATUS_FAIL);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    //根据id查询
    @RequestMapping("/findById")
    @ResponseBody
    public ResponseResult findById(int id){
        ProductType byId = productTypeService.findById(id);
        return ResponseResult.success(byId);
    }
    //修改商品类型名称
    @RequestMapping("/modifyName")
    @ResponseBody
    public ResponseResult modifyName(int id,String name){
        try{
            productTypeService.modifyName(id,name);
            return ResponseResult.success("修改成功");
        }
        catch(ProductTypeExistException e){
            return ResponseResult.fail(e.getMessage());
        }
    }

    //删除商品类型
    @RequestMapping("/deleteProductType")
    @ResponseBody
    public ResponseResult romoveProductType(int id){
        productTypeService.deleteProductType(id);
        return ResponseResult.success();
    }

    //修改禁用启用状态
    @RequestMapping("/modifyStatus")
    @ResponseBody
    public ResponseResult modifyStatus(int id){
        productTypeService.updateStatus(id);
        return ResponseResult.success();
    }

}
