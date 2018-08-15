package com.pinyougou.entity;

import com.pinyougou.pojo.TbBrand;

import java.io.Serializable;
import java.util.List;

/**
 * 用于分页的实体类
 */
public class PageResult implements Serializable {

    /**
     * 总商品记录数
     */
    private Long total;
    /**
     * 当前页显示的数据
     */
    private List rows;

    public PageResult(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
