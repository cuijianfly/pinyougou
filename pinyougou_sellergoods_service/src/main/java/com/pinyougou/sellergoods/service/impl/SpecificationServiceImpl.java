package com.pinyougou.sellergoods.service.impl;
import java.util.List;
import java.util.Map;

import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import com.pinyougou.pojogroup.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.pojogroup.Specification;
import com.pinyougou.pojo.TbSpecificationExample;
import com.pinyougou.pojo.TbSpecificationExample.Criteria;
import com.pinyougou.sellergoods.service.SpecificationService;

import com.pinyougou.entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private TbSpecificationMapper specificationMapper;
	@Autowired
	private TbSpecificationOptionMapper specificationOptionMapper;
	/**
	 * 查询全部
	 */
	@Override
	public List<TbSpecification> findAll() {
		return specificationMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbSpecification> page=   (Page<TbSpecification>) specificationMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Specification specification) {
		if(specification != null){
		    // 保存规格表
			specificationMapper.insert(specification.getSpecification());
                List<TbSpecificationOption> specificationOptions = specification.getSpecificationOptionList();
            // 保存规格表关联的规格项表
            for (TbSpecificationOption specificationOption : specificationOptions) {
                specificationOption.setSpecId(specification.getSpecification().getId());
                specificationOptionMapper.insert(specificationOption);
            }
		}

	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Specification specification){
	    if(specification != null){
	        // 根据主键更新规格表
            specificationMapper.updateByPrimaryKeySelective(specification.getSpecification());
            // 根据主键更新规格选项表
            // 不知道具体修改了或者新增了哪些内容，因此先将原来的数据全部删除，然后在将提交的数据保存
            TbSpecificationOptionExample example = new TbSpecificationOptionExample();
            TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
            criteria.andSpecIdEqualTo(specification.getSpecification().getId());
            specificationOptionMapper.deleteByExample(example);
            for (TbSpecificationOption tbSpecificationOption : specification.getSpecificationOptionList()) {
                // 设置关联id
                tbSpecificationOption.setSpecId(specification.getSpecification().getId());
                specificationOptionMapper.insert(tbSpecificationOption);
            }
        }
	}
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Specification findOne(Long id){
	    //查询规范实体数据
        TbSpecification specification = specificationMapper.selectByPrimaryKey(id);
        //查询规范选项数据
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);
        List<TbSpecificationOption> specificationOptions = specificationOptionMapper.selectByExample(example);
        Specification spec = new Specification();
        spec.setSpecification(specification);
        spec.setSpecificationOptionList(specificationOptions);
        return spec;
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			specificationMapper.deleteByPrimaryKey(id);
			// 删除关联的规格项目表
            TbSpecificationOptionExample example = new TbSpecificationOptionExample();
            example.createCriteria().andSpecIdEqualTo(id);
            specificationOptionMapper.deleteByExample(example);
		}
	}
	
	
		@Override
	public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbSpecificationExample example=new TbSpecificationExample();
		Criteria criteria = example.createCriteria();
		
		if(specification!=null){			
						if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}
	
		}
		
		Page<TbSpecification> page= (Page<TbSpecification>)specificationMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

    @Override
    public List<Map> selectOptionList() {
        return specificationMapper.selectOptionList();
    }

}
