package com.it.serviceimpl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.it.bean.Linkman;
import com.it.bean.PageBean;
import com.it.dao.LinkmanDao;
import com.it.service.LinkmanService;
@Service("linkmanService")
@Transactional
public class LinkmanServiceImpl implements LinkmanService {
	@Autowired
	private LinkmanDao linkmanDao;
	@Override
	public void save(Linkman linkman) {

		linkmanDao.save(linkman);
		
	}
	@Override
	public List<Linkman> findAll() {
		
		return linkmanDao.findAll();
	}
	@Override
	public Linkman findById(Long lkm_id) {
		
		
		return linkmanDao.findById(lkm_id);
	}
	@Override
	public void update(Linkman linkman) {

		linkmanDao.update(linkman);
	}
	@Override
	public void delete(Linkman linkman) {

		linkmanDao.delete(linkman);
	}
	@Override
	public List<Linkman> findSome(DetachedCriteria dc) {
		
		return linkmanDao.findSome(dc);
	}
	//分页条件查
	@Override
	public PageBean findByDc(DetachedCriteria dc, Integer pageNumber, Integer pageSize) {
		// 总条数  //全查的  // 带条件
		int totalCount=linkmanDao.findcount(dc);
		System.out.println(totalCount);
		// 总页数
		int totalPage=0;
		if(totalCount%pageSize==0)
		{
			totalPage=totalCount/pageSize;
		}else
		{
			totalPage=totalCount/pageSize+1;
		}
		// 显示的数据
		List<Linkman> linkmanList=linkmanDao.findLinkmanPage(dc,(pageNumber-1)*pageSize,pageSize);
		
		// pageBean封装
		PageBean pb = new PageBean();
		pb.setPageNumber(pageNumber);
		pb.setPageSize(pageSize);
		pb.setTotalCount(totalCount);
		pb.setTotalPage(totalPage);
		pb.setLinkmanList(linkmanList);
		
		return pb;
	}

}
