package com.it.serviceimpl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.it.bean.BaseDict;
import com.it.bean.Customer;
import com.it.dao.CustomerDao;
import com.it.service.CustomerService;
@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService
{
	@Autowired
	private CustomerDao customerDao;

	@Override
	public void save(Customer customer) {
		
		customerDao.save(customer);
	}

	@Override
	public List<BaseDict> findLevel(String string) {
		
		return customerDao.findLevel(string);
	}

	@Override
	public List<BaseDict> findSource(String string) {
		
		return customerDao.findSource(string);
	}

	@Override
	public List<BaseDict> findIndustry(String string) {
		
		return customerDao.findIndustry(string);
	}

	@Override
	public List<Customer> findAll() {

		return customerDao.findAll();
	}

	@Override
	public Customer findById(Integer cust_id) {
		
		return customerDao.findById(cust_id);
	}

	@Override
	public void update(Customer customer) {

		customerDao.update(customer);
	}

	@Override
	public void delete(Integer integer) {

		customerDao.delete(integer);
	}

	@Override
	public void delete2(Customer customer) {
		customerDao.delete2(customer);
	}

	@Override
	public List<Customer> findSome(DetachedCriteria dc) {
		return customerDao.findSome(dc);
	}

	

}
