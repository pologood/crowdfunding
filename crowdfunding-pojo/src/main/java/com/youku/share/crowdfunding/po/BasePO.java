package com.youku.share.crowdfunding.po;

public class BasePO {
	
	private String order_;//排序行
	private String sort_;//正排倒排
	private int pageNum_ = 1;//当前页号
	private int pageSize_ = 10;//页大小
	private int limitStart_;//mysql查询页号
	private int limitSize_;//mysql页大小

	public String getOrder_() {
		return order_;
	}

	public void setOrder_(String order_) {
		this.order_ = order_;
	}

	public String getSort_() {
		return sort_;
	}

	public void setSort_(String sort_) {
		this.sort_ = sort_;
	}

	public int getPageNum_() {
		return pageNum_;
	}

	public void setPageNum_(int pageNum_) {
		this.pageNum_ = pageNum_;
	}

	public int getPageSize_() {
		return pageSize_;
	}

	public void setPageSize_(int pageSize_) {
		this.pageSize_ = pageSize_;
	}

	public int getLimitStart_() {
		//return limitStart;
		return (pageNum_ - 1) * pageSize_;
	}

	public int getLimitSize_() {
		//return limitSize;
		return pageSize_;
	}

}
