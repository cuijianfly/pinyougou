package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.entity.PageResult;
import com.pinyougou.entity.Result;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.sellergoods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private TbBrandMapper brandMapper;

    @Override
    public List<TbBrand> findAll() {

        return brandMapper.selectByExample(null);

    }

    /**
     * 分页查询
     * @param pageNum   当前页
     * @param pageSize  当前页显示的条数
     * @return
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        // 开启分页
        PageHelper.startPage(pageNum,pageSize);
        Page<TbBrand> page = (Page<TbBrand>)brandMapper.selectByExample(null);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 添加
     */
    @Override
    public Result add(TbBrand tbBrand) {
        List<TbBrand> brands = brandMapper.selectByName(tbBrand.getName());
        if (brands != null && brands.size() > 0){
            // 说明品牌已经存在
            return new Result(false,"保存失败");
        }
        brandMapper.insert(tbBrand);
        return new Result(true,"保存成功");
    }

    /**
     * 根据主键查询单个实体
     * @param id
     * @return
     */
    @Override
    public TbBrand findOne(Long id) {

        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新品牌
     * @param tbBrand
     */
    @Override
    public void update(TbBrand tbBrand) {
        if(tbBrand != null){
            brandMapper.updateByPrimaryKey(tbBrand);
        }

    }

    /**
     * 删除选中品牌
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
        if(ids != null && ids.length > 0){
            for (Long id : ids) {
                brandMapper.deleteByPrimaryKey(id);
            }
        }
    }

    /**
     * 条件分页查询
     * @param tbBrand
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageResult findPage(TbBrand tbBrand, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        TbBrandExample example = new TbBrandExample();
        TbBrandExample.Criteria criteria = example.createCriteria();
      if(tbBrand != null && tbBrand.getName() != null && !"".equals(tbBrand.getName())){
        criteria.andNameLike("%"+tbBrand.getName()+"%");
      }
      if(tbBrand != null && tbBrand.getFirstChar() != null && !"".equals(tbBrand.getFirstChar())){

          criteria.andFirstCharEqualTo(tbBrand.getFirstChar().toUpperCase());
      }
        Page<TbBrand> brands = (Page<TbBrand>)brandMapper.selectByExample(example);
        return new PageResult(brands.getTotal(),brands.getResult());
    }

    /**
     * 查询品牌项
     * @return
     */
    @Override
    public List<Map> selectOptionList() {
        return brandMapper.selectOptionList();
    }

}
