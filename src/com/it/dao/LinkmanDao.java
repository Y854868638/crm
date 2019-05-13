package com.it.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.it.bean.Linkman;

public interface LinkmanDao {

	void save(Linkman linkman);

	List<Linkman> findAll();

	Linkman findById(Long lkm_id);

	void update(Linkman linkman);

	void delete(Linkman linkman);

	List<Linkman> findSome(DetachedCriteria dc);
	//��ѯ����
	int findcount(DetachedCriteria dc);
	//��ѯ��ʾ������
	List<Linkman> findLinkmanPage(DetachedCriteria dc, int i, Integer pageSize);

}
