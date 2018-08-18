package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.entity.Result;
import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Reference
    private SellerService sellerService;

    @RequestMapping("/regist")
    public Result regist(@RequestBody TbSeller tbSeller){

        try {
            sellerService.add(tbSeller);
            return new Result(true,"注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true,"注册失败");
        }
    }

}
