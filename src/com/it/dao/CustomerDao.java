package com.it.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.it.bean.BaseDict;
import com.it.bean.Customer;

public interface CustomerDao {

	void save(Customer customer);

	List<BaseDict> findLevel(String string);

	List<BaseDict> findSource(String string);

	List<BaseDict> findIndustry(String string);

	List<Customer> findAll();

	Customer findById(Integer cust_id);

	void update(Customer customer);

	void delete(Integer integer);

	void delete2(Customer customer);

	List<Customer> findSome(DetachedCriteria dc);

	
	
}
