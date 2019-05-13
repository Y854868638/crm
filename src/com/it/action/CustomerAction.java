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

	// ��װҳ������
	private Customer customer = new Customer();

	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}

	// �����ݴ���ֵջ��ȥ
	private List<BaseDict> listSource;// �ͻ���Ϣ��Դ
	private List<BaseDict> listIndustry;// �ͻ�������ҵ
	private List<BaseDict> listLevel;// �ͻ�����
	private List<Customer> listCustomer;// ���еĿͻ�
	private Customer customerById;// id���ҿͻ�

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

	// �����ͻ�ҳ�湦��
	@Action(value = "customer_addUI", results = { @Result(name = "addUI", location = "/jsp/customer/add.jsp") })
	public String addUI() {
		/*
		 * �������: 1 ��ѯ���пͻ��������� 006 ���ص���list 2 ��ѯ������Ϣ��Դ���� 002 ���ص���list 3 ��ѯ����������ҵ���� 001
		 * ���ص���list 4 �Ѳ�ѯ������list����ֵջ�� ��add.jspҳ���������ʾ����������
		 */
		listLevel = customerService.findLevel("006");

		listSource = customerService.findSource("002");

		listIndustry = customerService.findIndustry("001");
		return "addUI";
	}

	// ����ͻ� //����ת������ѯ���е�ҳ��
	@Action(value = "customer_save", results = {
			@Result(name = "save", type = "redirectAction", location = "customer_findAll") })
	public String save() {

		/*
		 * ����: 1 ��ҳ������ݷ�װ��modelDriver 2 ����װ�õĶ�����д��ݱ��� 3 �������,��Ҫִ�в�ѯ���� ���������ݲ�ѯ������ҳ��չʾ
		 */
		customerService.save(customer);
		return "save";
	}

	// ��ѯ���еĿͻ�
	@Action(value = "customer_findAll", results = { @Result(name = "findAll", location = "/jsp/customer/list.jsp") })
	public String findAll() {
		listLevel = customerService.findLevel("006");

		listSource = customerService.findSource("002");

		listIndustry = customerService.findIndustry("001");

		listCustomer = customerService.findAll();

		return "findAll";
	}
	//������ѯ�ͻ�
	@Action(value="customer_findSome",results= {@Result(name="findSome",location="/jsp/customer/list.jsp")})
	public String findSome() {
		/*����:
		 *  1 ��ҳ������ݷ�װ��modelDriver��customer������
		 *  2 ����������ѯ (�����˴�ͳ�ķ�ʽ ʹ��hibernate�ṩ�����߶��������������ѯ)
		 * 
		 * */
		
		// ��ȡһ��hibernate�ṩ�����߶���
		DetachedCriteria dc=DetachedCriteria.forClass(Customer.class);
		//�����������ģ����ѯ������
		dc.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		// ��û����ѡ���ʱ��,���������
		if (customer.getCust_level().getDict_id()!=-1) {
			//��Ӳ�ѯ�����������ݴ����Ķ����ȡ���ֵ�������id
			dc.add(Restrictions.eq("cust_level.dict_id", customer.getCust_level().getDict_id()));
		}
		if (customer.getCust_source().getDict_id()!=-1) {
			dc.add(Restrictions.eq("cust_source.dict_id", customer.getCust_source().getDict_id()));
		}
		if (customer.getCust_industry().getDict_id()!=-1) {
			dc.add(Restrictions.eq("cust_industry.dict_id", customer.getCust_industry().getDict_id()));
		}
		// ����dc����
		listCustomer=customerService.findSome(dc);
		// �ͻ�����,�ͻ���Դ,�ͻ�������ҵ����Ϣ �ٲ�һ��
		listLevel = customerService.findLevel("006");

		listSource = customerService.findSource("002");

		listIndustry = customerService.findIndustry("001");

		return "findSome";
	}

	// ���޸Ŀͻ�����
	@Action(value = "customer_editUI", results = { @Result(name = "editUI", location = "/jsp/customer/edit.jsp") })
	public String editUI() {
		listLevel = customerService.findLevel("006");

		listSource = customerService.findSource("002");

		listIndustry = customerService.findIndustry("001");
		// ����ҳ�洫���Ŀͻ�id���ҿͻ���������Ϣ
		System.out.println(customer.getCust_id());
		customerById = customerService.findById(customer.getCust_id());
		System.out.println(customerById);
		return "editUI";
	}

	// �޸Ŀͻ� //�޸ĺ�Ľ��ת������ѯ���пͻ�����
	@Action(value = "customer_update", results = {
			@Result(name = "update", type = "redirectAction", location = "customer_findAll") })
	public String update() {
		// �����޸ĵĿͻ���Ϣ
		customerService.update(customer);

		return "update";
	}

	// ɾ���ͻ�����1.���ݴ�������idɾ��
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
	
//	// ɾ���ͻ�����2.���ݶ���ɾ��
//		@Action(value = "customer_delete", results = {
//				@Result(name = "delete", type = "redirectAction", location = "customer_findAll") })
//		public String delete2() {
//			customerService.delete2(customer);
//			
//			return "delete";
//			
//		}
}
