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
	
	//封装页面linkman数据
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
	//所有的客户
	private List<Customer> listCustomer;
	//所有的联系人
	private List<Linkman> listLinkman;
	//封装根据id查到的联系人
	private Linkman linkmanById;
	// 分页数据
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
	
	// 分页数据
	private Integer pageNumber=1;
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	private Integer pageSize=3;
	//跳转到保存联系人界面
	@Action(value="linkman_addUI",results= {@Result(name="addUI",location="/jsp/linkman/add.jsp")})
	public String addUI() {
		listCustomer=customerService.findAll();
		return "addUI";
		
	}
	//保存联系人
	@Action(value="linkman_save",results= {@Result(name="save",type = "redirectAction",location="linkman_findAll")})
	public String save() {
		linkmanService.save(linkman);
		return "save";
	}
	//查找所有的联系人
	@Action(value="linkman_findAll",results= {@Result(name="findAll",location="/jsp/linkman/list.jsp")})
	public String findAll() {
		listCustomer=customerService.findAll();
		listLinkman= linkmanService.findAll();
		return "findAll";
	}
	//条件查
	@Action(value="linkman_findSome",results= {@Result(name="findSome",location="/jsp/linkman/list.jsp")})
	public String findSome() {
		// 获取一个hibernate提供的离线对象
		DetachedCriteria dc = DetachedCriteria.forClass(Linkman.class);
		//做条件，添加模糊查询的条件
		dc.add(Restrictions.like("lkm_name", "%"+linkman.getLkm_name()+"%"));
		if (linkman.getCustomer().getCust_id()!=-1) {
			dc.add(Restrictions.eq("customer.cust_id", linkman.getCustomer().getCust_id()));
		}
		
		listLinkman=linkmanService.findSome(dc);
		//回显数据
		listCustomer=customerService.findAll();
		return "findSome";
	}
	//到修改界面
	@Action(value="linkman_editUI",results= {@Result(name="editUI",location="/jsp/linkman/edit.jsp")})
	public String editUI() {
		/*步骤:
		 *  1 查询所有客户 返回list 带到edit页面显示
		 *  2 根据页面传递的id 将点击的联系人数据给查出来  返回的对象
		 *  3 将对象数据放在值栈中 带到edit.jsp页面回显
		 * */
		listCustomer=customerService.findAll();
		linkmanById =linkmanService.findById(linkman.getLkm_id());
		return "editUI";
	}
	
	
	// 分页的
	@Action(value="linkman_list",results={@Result(name="tolist",location="/jsp/linkman/pageList.jsp")})
	public String list()
	{
		// 所有的客户
		listCustomer = customerService.findAll();
		 
		// 离线条件查询
		DetachedCriteria dc=DetachedCriteria.forClass(Linkman.class); // 默认语法:from Linkman
		// 添加条件  点击的是联系人列表就忽略这2个条件  点击的是筛选就加上这2个条件
		if(linkman.getLkm_name()!=null)
		{
			dc.add(Restrictions.like("lkm_name", "%"+linkman.getLkm_name()+"%"));
		}
		
		if(linkman.getCustomer()!=null && linkman.getCustomer().getCust_id()!=-1)
		{
			dc.add(Restrictions.eq("customer.cust_id", linkman.getCustomer().getCust_id()));
		}
		
		// 点击的是联系人列表做默认语法全查
		// 点击的是筛选做条件查 
		pb = linkmanService.findByDc(dc,pageNumber,pageSize);
	
		return "tolist";
	}
		
	
	
	//保存修改界面
	@Action(value="linkman_update",results= {@Result(name="update",type = "redirectAction",location="linkman_findAll")})
	public String update() {
		linkmanService.update(linkman);
		
		return "update";
	}
	//删除
	@Action(value="linkman_delete",results= {@Result(name="delete",type="redirectAction",location="linkman_findAll")})
	public String delete() {
		linkmanService.delete(linkman);
		return "delete";
	}
}
