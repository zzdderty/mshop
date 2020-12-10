package com.muyang.mshop.service.impl;

import com.muyang.mshop.constant.ProductTypeConstant;
import com.muyang.mshop.dao.ProductTypeDao;
import com.muyang.mshop.exception.ProductTypeExistException;
import com.muyang.mshop.pojo.ProductType;
import com.muyang.mshop.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeDao productTypeDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<ProductType> findAll() {
        return productTypeDao.findAll();
    }

    @Override
    public void add(String name) throws ProductTypeExistException {
        ProductType byName = productTypeDao.findByName(name);
        if(byName!=null){
            throw new ProductTypeExistException("商品已经存在");
        }
        productTypeDao.add(name, ProductTypeConstant.PRODUCT_STATUS_ENABLE);
    }

    @Override
    public ProductType findById(int id) {
        return productTypeDao.findById(id);
    }

    @Override
    public void modifyName(int id, String name) throws ProductTypeExistException{
        ProductType byName = productTypeDao.findByName(name);
        if(byName!=null){
            throw new ProductTypeExistException("商品类型已经存在");
        }
        productTypeDao.modifyName(id,name);
    }

    @Override
    public void deleteProductType(int id) {
        productTypeDao.deleteProductType(id);
    }

    @Override
    public void updateStatus(int id) {
        ProductType byId = productTypeDao.findById(id);
        Integer status = byId.getStatus();
        if(status==ProductTypeConstant.PRODUCT_STATUS_ENABLE){
            status=ProductTypeConstant.PRODUCT_STATUS_DISABLE;
        }
        else{
            status=ProductTypeConstant.PRODUCT_STATUS_ENABLE;
        }
        productTypeDao.updateStatus(id,status);
    }
}
