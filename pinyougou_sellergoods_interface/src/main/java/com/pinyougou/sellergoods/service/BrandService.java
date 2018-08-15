package com.pinyougou.sellergoods.service;

import com.pinyougou.entity.PageResult;
import com.pinyougou.entity.Result;
import com.pinyougou.pojo.TbBrand;

import java.util.List;

public interface BrandService {
    // 查询所有
    public List<TbBrand> findAll();

    // 分页
    public PageResult findPage(int pageNum,int pageSize);

    // 添加
    public Result add(TbBrand tbBrand);

    // 查询单个实体
    public TbBrand findOne(Long id);

    // 更新品牌
    public void update(TbBrand tbBrand);

    // 删除品牌
    public void delete(Long[] ids);

    // 条件分页查询
    public PageResult findPage(TbBrand tbBrand,int pageNum,int pageSize);
}
