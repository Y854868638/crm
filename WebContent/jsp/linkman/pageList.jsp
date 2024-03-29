<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>联系人列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<SCRIPT language=javascript>
		/* 
				1.4.4: 没有prop attr
				1.8以上才有prop这个属性
		*/
		
		$(function(){
			$("#customerid option[value='<s:property value="customer.cust_id"/>']").prop("selected",true);
		})
		
		function del(id)
		{
			if(confirm("确定要删除该联系人么"))
			{
				location.href="${pageContext.request.contextPath}/linkman_delete?lkm_id="+id;
			}
		}
		
		function topage(pageNumber)
		{
			$("#pageNumber").val(pageNumber);
			$("#customerForm").submit();
		} 
</SCRIPT>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<s:debug></s:debug>
	<FORM id="customerForm" name="customerForm"
		action="${pageContext.request.contextPath}/linkman_list.action"
		method=post>
		
		<input type="hidden" name="pageNumber" value="1" id="pageNumber"/>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_022.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：联系人管理 &gt;分页查看联系人列表</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0
							width="100%" align=center border=0>
							<TBODY>
								<TR>
									<TD height=25>
										<TABLE cellSpacing=0 cellPadding=2 border=0>
											<TBODY>
												<TR>
													<TD>联系人名称：</TD>
													<TD><INPUT class=textbox id=sChannel2
													value="<s:property value="lkm_name"/>"
														style="WIDTH: 80px" maxLength=50 name="lkm_name"></TD>
													
													<TD>所属客户：</TD>
													<TD>
														<select id="customerid" name="customer.cust_id" style="WIDTH: 180px">
														  	<option value="-1">---请选择---</option>
														  <s:iterator value="listCustomer" var="customer">
																<option value="<s:property value="#customer.cust_id"/>">
																		<s:property value="#customer.cust_name"/>
																</option>
															</s:iterator>
														</select>
													</TD>
													
													<TD><INPUT class=button id=sButton2 type=submit
														value=" 筛选 " name=sButton2></TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
							    
								<TR>
									<TD>
										<TABLE id=grid
											style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
											cellSpacing=1 cellPadding=2 rules=all border=0>
											<TBODY>
												<TR
													style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
													<TD>联系人名称</TD>
													<TD>性别</TD>
													<TD>办公电话</TD>
													<TD>手机</TD>
													<TD>所属客户</TD>
													<TD>操作</TD>
												</TR>
												<s:iterator value="pb.linkmanList" var="linkman">
													<TR
														style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
														<TD><s:property value="#linkman.lkm_name"/></TD>
														<TD><s:property value="#linkman.lkm_gender"/></TD>
														<TD><s:property value="#linkman.lkm_phone"/></TD>
														<TD><s:property value="#linkman.lkm_mobile"/></TD>
														<TD><s:property value="#linkman.customer.cust_name"/></TD>
														<TD>
														<a href="${pageContext.request.contextPath}/linkman_editUI?lkm_id=<s:property value="#linkman.lkm_id"/>">修改</a>
														&nbsp;&nbsp;
														<a href="#" onclick="del('<s:property value="#linkman.lkm_id"/>')">删除</a>
														</TD>
													</TR>
												</s:iterator>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								
								<TR>
									<TD><SPAN id=pagelink>
											<DIV
												style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
												共[<B><s:property value="pb.totalCount" /></B>]条记录,[<B>${pb.totalPage}</B>]页
												
												[<A href="#" onclick="topage('${pb.pageNumber-1}')">前一页</A>]
												<B>${page}</B>
												[<A href="#" onclick="topage('${pb.pageNumber+1}')">后一页</A>] 
												
											</DIV>
									</SPAN></TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
</BODY>
</HTML>
