package com.it.daoimpl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.it.bean.BaseDict;
import com.it.bean.Customer;
import com.it.dao.CustomerDao;
@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao
{
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	
	@Override
	public void save(Customer customer) 
	{
		hibernateTemplate.save(customer);
		
	}


	@Override
	public List<BaseDict> findLevel(String string) {
		// hql语法  from 类名 where 字段名
		return (List<BaseDict>) hibernateTemplate.find("from BaseDict where dict_type_code = ?", string);
	}


	@Override
	public List<BaseDict> findSource(String string) {

		return (List<BaseDict>) hibernateTemplate.find("from BaseDict where dict_type_code = ?", string);
	}


	@Override
	public List<BaseDict> findIndustry(String string) {

		return (List<BaseDict>) hibernateTemplate.find("from BaseDict where dict_type_code = ?", string);
	}


	@Override
	public List<Customer> findAll() {
		
		return (List<Customer>) hibernateTemplate.find("from Customer");
	}


	@Override
	public Customer findById(Integer cust_id) {
		
		return hibernateTemplate.get(Customer.class, cust_id);
	}


	@Override
	public void update(Customer customer) {

		hibernateTemplate.update(customer);
		
	}


	@Override
	public void delete(Integer integer) {
		Customer customer = hibernateTemplate.get(Customer.class, integer);
		
		hibernateTemplate.delete(customer);
		
	}


	@Override
	public void delete2(Customer customer) {

		hibernateTemplate.delete(customer);
	}


	@Override
	public List<Customer> findSome(DetachedCriteria dc) {
		
		return (List<Customer>) hibernateTemplate.findByCriteria(dc);
	}




}
