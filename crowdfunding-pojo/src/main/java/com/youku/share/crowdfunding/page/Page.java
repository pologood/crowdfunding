package com.youku.share.crowdfunding.page;

import java.util.List;

import com.youku.share.crowdfunding.po.BasePO;

public class Page<T> {
    
    private BasePO pageParameter;
    private int total;// 总页数
    private int page;// 当前页
    private int records;// 查询出的记录数
    private List<T> rows;// 包含实际数据的数组

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
    
    public BasePO getPageParameter() {
        return pageParameter;
    }

    public void setPageParameter(BasePO pageParameter) {
        this.pageParameter = pageParameter;
    }
    
    public void pageCalculation(T[] a){
        this.page = this.pageParameter.getPageNum_();
        this.total = this.records / this.pageParameter.getPageSize_() + (this.records % this.pageParameter.getPageSize_() == 0 ? 0 : 1);
    }
}
