package com.muyang.mshop.service;

import com.muyang.mshop.exception.ProductTypeExistException;
import com.muyang.mshop.pojo.ProductType;

import java.util.List;

public interface ProductTypeService {
    /**
     * 查找所有商品类型
     */
    public List<ProductType> findAll();

    /**
     * 添加商品类型
     * @param name
     */
    public void add(String name) throws ProductTypeExistException;

    /**
     * 根据id查询商品类型
     */
    public ProductType findById(int id);

    public void modifyName(int id, String name) throws ProductTypeExistException;

    public void deleteProductType(int id);

    public void updateStatus(int id);
}
