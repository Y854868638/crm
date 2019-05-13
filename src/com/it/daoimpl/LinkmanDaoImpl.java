package com.it.daoimpl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.it.bean.Linkman;
import com.it.dao.LinkmanDao;
@Repository("linkmanDao")
public class LinkmanDaoImpl implements LinkmanDao {
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	
	@Override
	public void save(Linkman linkman) {

		hibernateTemplate.save(linkman);
		
	}


	@Override
	public List<Linkman> findAll() {
		
		return (List<Linkman>) hibernateTemplate.find("from Linkman");
	}


	@Override
	public Linkman findById(Long lkm_id) {
		
		
		return hibernateTemplate.get(Linkman.class,lkm_id);
	}


	@Override
	public void update(Linkman linkman) {

		hibernateTemplate.update(linkman);
	}


	@Override
	public void delete(Linkman linkman) {

		hibernateTemplate.delete(linkman);
	}


	@Override
	public List<Linkman> findSome(DetachedCriteria dc) {
		
		return (List<Linkman>) hibernateTemplate.findByCriteria(dc);
	}

	//查询条数
	@Override
	public int findcount(DetachedCriteria dc) {
		// 设置dc的语法  改成语法: select count(*) from 表 或则  select count(*) from 表 where 条件
		dc.setProjection(Projections.rowCount());
		List<Long> list=(List<Long>) hibernateTemplate.findByCriteria(dc); //默认语法: select * from 表  或则  select * from 表  where 条件
		return list.get(0).intValue();
	}

	//查询显示的数据
	@Override
	public List<Linkman> findLinkmanPage(DetachedCriteria dc, int start, Integer pageSize) {
		// 当前dc的语法:select count(*) 
		dc.setProjection(null); // 回归默认语法 select * from 
		return (List<Linkman>) hibernateTemplate.findByCriteria(dc, start, pageSize);
	}

}
