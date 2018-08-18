package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.entity.PageResult;
import com.pinyougou.entity.Result;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbBrand> findAll(){

        return brandService.findAll();
    }

    /**
     * 商品分页
     * @param page 当前页
     * @param rows 当前页显示的条数
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page,int rows){

        return brandService.findPage(page,rows);
    }

    /**
     * 添加品牌
     * @param tbBrand
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody TbBrand tbBrand){

        return brandService.add(tbBrand);
    }

    /**
     * 根据主键查询单个实体
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
   public TbBrand findOne(Long id){
        return brandService.findOne(id);
   }

    /**
     * 根据主键更新品牌
     * @param tbBrand
     * @return
     */
    @RequestMapping("/update")
   public Result update(@RequestBody TbBrand tbBrand){
       try {
           brandService.update(tbBrand);
           return new Result(true,"更新成功");
       } catch (Exception e) {
           e.printStackTrace();
           return new Result(false,"更新失败");
       }
   }

    /**
     * 删除选中项目
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
   public Result delete(Long[] ids){
        try {
            brandService.delete(ids);
            return new Result(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true,"删除失败");
        }
    }
   @RequestMapping("/search")
   public PageResult search(@RequestBody TbBrand tbBrand,int page,int rows){

         return brandService.findPage(tbBrand,page,rows);
   }

    /**
     * 查询品牌项
     * @return
     */
    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList(){

        return brandService.selectOptionList();
    }

}
