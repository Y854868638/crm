package com.it.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.tomcat.jni.Local;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.hql.internal.ast.tree.RestrictableStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.it.bean.BaseDict;
import com.it.bean.Customer;
import com.it.service.CustomerService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("customerAction")
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	@Autowired
	private CustomerService customerService;

	// 封装页面数据
	private Customer customer = new Customer();

	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}

	// 把数据存入值栈种去
	private List<BaseDict> listSource;// 客户信息来源
	private List<BaseDict> listIndustry;// 客户所属行业
	private List<BaseDict> listLevel;// 客户级别
	private List<Customer> listCustomer;// 所有的客户
	private Customer customerById;// id查找客户

	public Customer getCustomerById() {
		return customerById;
	}

	public List<Customer> getListCustomer() {
		return listCustomer;
	}

	public List<BaseDict> getListSource() {
		return listSource;
	}

	public List<BaseDict> getListIndustry() {
		return listIndustry;
	}

	public List<BaseDict> getListLevel() {
		return listLevel;
	}

	// 新增客户页面功能
	@Action(value = "customer_addUI", results = { @Result(name = "addUI", location = "/jsp/customer/add.jsp") })
	public String addUI() {
		/*
		 * 步骤分析: 1 查询所有客户级别数据 006 返回的是list 2 查询所有信息来源数据 002 返回的是list 3 查询所有所属行业数据 001
		 * 返回的是list 4 把查询的三个list放在值栈中 到add.jsp页面把数据显示到下拉框中
		 */
		listLevel = customerService.findLevel("006");

		listSource = customerService.findSource("002");

		listIndustry = customerService.findIndustry("001");
		return "addUI";
	}

	// 保存客户 //请求转发到查询所有的页面
	@Action(value = "customer_save", results = {
			@Result(name = "save", type = "redirectAction", location = "customer_findAll") })
	public String save() {

		/*
		 * 步骤: 1 将页面的数据封装给modelDriver 2 将封装好的对象进行传递保存 3 保存完毕,需要执行查询操作 将最新数据查询出来到页面展示
		 */
		customerService.save(customer);
		return "save";
	}

	// 查询所有的客户
	@Action(value = "customer_findAll", results = { @Result(name = "findAll", location = "/jsp/customer/list.jsp") })
	public String findAll() {
		listLevel = customerService.findLevel("006");

		listSource = customerService.findSource("002");

		listIndustry = customerService.findIndustry("001");

		listCustomer = customerService.findAll();

		return "findAll";
	}
	//条件查询客户
	@Action(value="customer_findSome",results= {@Result(name="findSome",location="/jsp/customer/list.jsp")})
	public String findSome() {
		/*步骤:
		 *  1 将页面的数据封装给modelDriver的customer对象中
		 *  2 离线条件查询 (抛弃了传统的方式 使用hibernate提供的离线对象来完成条件查询)
		 * 
		 * */
		
		// 获取一个hibernate提供的离线对象
		DetachedCriteria dc=DetachedCriteria.forClass(Customer.class);
		//做条件，添加模糊查询的条件
		dc.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		// 当没有请选择的时候,才添加条件
		if (customer.getCust_level().getDict_id()!=-1) {
			//添加查询的条件，根据传来的对象获取到字典表里面的id
			dc.add(Restrictions.eq("cust_level.dict_id", customer.getCust_level().getDict_id()));
		}
		if (customer.getCust_source().getDict_id()!=-1) {
			dc.add(Restrictions.eq("cust_source.dict_id", customer.getCust_source().getDict_id()));
		}
		if (customer.getCust_industry().getDict_id()!=-1) {
			dc.add(Restrictions.eq("cust_industry.dict_id", customer.getCust_industry().getDict_id()));
		}
		// 传递dc对象
		listCustomer=customerService.findSome(dc);
		// 客户级别,客户来源,客户所属行业的信息 再查一遍
		listLevel = customerService.findLevel("006");

		listSource = customerService.findSource("002");

		listIndustry = customerService.findIndustry("001");

		return "findSome";
	}

	// 到修改客户界面
	@Action(value = "customer_editUI", results = { @Result(name = "editUI", location = "/jsp/customer/edit.jsp") })
	public String editUI() {
		listLevel = customerService.findLevel("006");

		listSource = customerService.findSource("002");

		listIndustry = customerService.findIndustry("001");
		// 根据页面传来的客户id查找客户的所有信息
		System.out.println(customer.getCust_id());
		customerById = customerService.findById(customer.getCust_id());
		System.out.println(customerById);
		return "editUI";
	}

	// 修改客户 //修改后的结果转发到查询所有客户界面
	@Action(value = "customer_update", results = {
			@Result(name = "update", type = "redirectAction", location = "customer_findAll") })
	public String update() {
		// 保存修改的客户信息
		customerService.update(customer);

		return "update";
	}

	// 删除客户方法1.根据传过来的id删除
	@Action(value = "customer_delete", results = {
			@Result(name = "delete", type = "redirectAction", location = "customer_findAll"), 
			@Result(name="nodelete",location="/jsp/error.jsp")
	})
	public String delete() {
		try {
			customerService.delete(customer.getCust_id());
			return "delete";
		} catch (Exception e) {
			e.printStackTrace();
			return "nodelete";
		}
	}
	
//	// 删除客户方法2.根据对象删除
//		@Action(value = "customer_delete", results = {
//				@Result(name = "delete", type = "redirectAction", location = "customer_findAll") })
//		public String delete2() {
//			customerService.delete2(customer);
//			
//			return "delete";
//			
//		}
}
