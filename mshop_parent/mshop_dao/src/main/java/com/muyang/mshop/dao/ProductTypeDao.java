package com.muyang.mshop.dao;

import com.muyang.mshop.pojo.ProductType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductTypeDao {
    public List<ProductType> findAll();

    public void add(@Param("name")String name,@Param("status")int Status);

    public ProductType findById(int id);

    public ProductType findByName(String name);

    public void modifyName(@Param("id") int id, @Param("name") String name);

    public void deleteProductType(int id);

    public void updateStatus(@Param("id") int id,@Param("status") Integer status);
}
