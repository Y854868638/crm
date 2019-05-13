package com.it.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.it.bean.Linkman;
import com.it.bean.PageBean;

public interface LinkmanService {

	void save(Linkman linkman);

	List<Linkman> findAll();

	Linkman findById(Long lkm_id);

	void update(Linkman linkman);

	void delete(Linkman linkman);

	List<Linkman> findSome(DetachedCriteria dc);
	//分页条件查
	PageBean findByDc(DetachedCriteria dc, Integer pageNumber, Integer pageSize);

}
