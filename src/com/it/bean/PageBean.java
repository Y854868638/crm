package com.it.bean;

import java.util.List;

public class PageBean 
{
	//��ǰҳ: pageNumber
	private Integer pageNumber;
	//ÿҳ��ʾ������: pageSize
	private Integer pageSize;
	//ÿҳ��ʾ������  List<Linkman> linkmanList;
	private List<Linkman> linkmanList;
	//������: totalcount
	private Integer totalCount;
	//��ҳ��: totalPage
	private Integer totalPage;
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public List<Linkman> getLinkmanList() {
		return linkmanList;
	}
	public void setLinkmanList(List<Linkman> linkmanList) {
		this.linkmanList = linkmanList;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
	
}
