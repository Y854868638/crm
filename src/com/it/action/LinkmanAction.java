package com.it.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.it.bean.Customer;
import com.it.bean.Linkman;
import com.it.bean.PageBean;
import com.it.service.CustomerService;
import com.it.service.LinkmanService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Controller("linkmanAction")
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/")
public class LinkmanAction extends ActionSupport implements ModelDriven<Linkman> {
	
	//��װҳ��linkman����
	private Linkman linkman =new Linkman();
	@Override
	public Linkman getModel() {
		// TODO Auto-generated method stub
		return linkman;
	}
	@Autowired
	private CustomerService customerService;
	@Autowired
	private LinkmanService linkmanService;
	//���еĿͻ�
	private List<Customer> listCustomer;
	//���е���ϵ��
	private List<Linkman> listLinkman;
	//��װ����id�鵽����ϵ��
	private Linkman linkmanById;
	// ��ҳ����
	private PageBean pb;
	
	public PageBean getPb() {
		return pb;
	}
	
	public Linkman getLinkmanById() {
		return linkmanById;
	}
	public List<Linkman> getListLinkman() {
		return listLinkman;
	}
	public List<Customer> getListCustomer() {
		return listCustomer;
	}
	
	// ��ҳ����
	private Integer pageNumber=1;
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	private Integer pageSize=3;
	//��ת��������ϵ�˽���
	@Action(value="linkman_addUI",results= {@Result(name="addUI",location="/jsp/linkman/add.jsp")})
	public String addUI() {
		listCustomer=customerService.findAll();
		return "addUI";
		
	}
	//������ϵ��
	@Action(value="linkman_save",results= {@Result(name="save",type = "redirectAction",location="linkman_findAll")})
	public String save() {
		linkmanService.save(linkman);
		return "save";
	}
	//�������е���ϵ��
	@Action(value="linkman_findAll",results= {@Result(name="findAll",location="/jsp/linkman/list.jsp")})
	public String findAll() {
		listCustomer=customerService.findAll();
		listLinkman= linkmanService.findAll();
		return "findAll";
	}
	//������
	@Action(value="linkman_findSome",results= {@Result(name="findSome",location="/jsp/linkman/list.jsp")})
	public String findSome() {
		// ��ȡһ��hibernate�ṩ�����߶���
		DetachedCriteria dc = DetachedCriteria.forClass(Linkman.class);
		//�����������ģ����ѯ������
		dc.add(Restrictions.like("lkm_name", "%"+linkman.getLkm_name()+"%"));
		if (linkman.getCustomer().getCust_id()!=-1) {
			dc.add(Restrictions.eq("customer.cust_id", linkman.getCustomer().getCust_id()));
		}
		
		listLinkman=linkmanService.findSome(dc);
		//��������
		listCustomer=customerService.findAll();
		return "findSome";
	}
	//���޸Ľ���
	@Action(value="linkman_editUI",results= {@Result(name="editUI",location="/jsp/linkman/edit.jsp")})
	public String editUI() {
		/*����:
		 *  1 ��ѯ���пͻ� ����list ����editҳ����ʾ
		 *  2 ����ҳ�洫�ݵ�id ���������ϵ�����ݸ������  ���صĶ���
		 *  3 ���������ݷ���ֵջ�� ����edit.jspҳ�����
		 * */
		listCustomer=customerService.findAll();
		linkmanById =linkmanService.findById(linkman.getLkm_id());
		return "editUI";
	}
	
	
	// ��ҳ��
	@Action(value="linkman_list",results={@Result(name="tolist",location="/jsp/linkman/pageList.jsp")})
	public String list()
	{
		// ���еĿͻ�
		listCustomer = customerService.findAll();
		 
		// ����������ѯ
		DetachedCriteria dc=DetachedCriteria.forClass(Linkman.class); // Ĭ���﷨:from Linkman
		// �������  ���������ϵ���б�ͺ�����2������  �������ɸѡ�ͼ�����2������
		if(linkman.getLkm_name()!=null)
		{
			dc.add(Restrictions.like("lkm_name", "%"+linkman.getLkm_name()+"%"));
		}
		
		if(linkman.getCustomer()!=null && linkman.getCustomer().getCust_id()!=-1)
		{
			dc.add(Restrictions.eq("customer.cust_id", linkman.getCustomer().getCust_id()));
		}
		
		// ���������ϵ���б���Ĭ���﷨ȫ��
		// �������ɸѡ�������� 
		pb = linkmanService.findByDc(dc,pageNumber,pageSize);
	
		return "tolist";
	}
		
	
	
	//�����޸Ľ���
	@Action(value="linkman_update",results= {@Result(name="update",type = "redirectAction",location="linkman_findAll")})
	public String update() {
		linkmanService.update(linkman);
		
		return "update";
	}
	//ɾ��
	@Action(value="linkman_delete",results= {@Result(name="delete",type="redirectAction",location="linkman_findAll")})
	public String delete() {
		linkmanService.delete(linkman);
		return "delete";
	}
}
