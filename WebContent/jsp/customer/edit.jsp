﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>添加客户</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css"
	type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css"
	type=text/css rel=stylesheet>


<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<script>
	$(function()
	{
		$("#level option[value='<s:property value="customerFind.cust_level.dict_id"/>']").prop("selected",true);
		$("#source option[value='<s:property value="customerFind.cust_source.dict_id"/>']").prop("selected",true);
		$("#industry option[value='<s:property value="customerFind.cust_industry.dict_id"/>']").prop("selected",true);
		
		
		
	})
</script>
</HEAD>
<BODY>
	<s:debug></s:debug>
	<FORM id=form1 name=form1
		action="${pageContext.request.contextPath }/customer_update.action"
		method=post>
		<input type="hidden" value='<s:property value="customerById.cust_id"/>' name="cust_id">

		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG
						src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%"
						background="${pageContext.request.contextPath }/images/new_020.jpg"
						height=20></TD>
					<TD width=15><IMG
						src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background=${pageContext.request.contextPath }
						/images/new_022.jpg><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg"
						border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：客户管理 &gt; 添加客户</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>

						<TABLE cellSpacing=0 cellPadding=5 border=0>


							<TR>
								<td>客户名称：</td>
								<td><INPUT class=textbox id=sChannel2 style="WIDTH: 180px"
									maxLength=50 name="cust_name" value='<s:property value="customerById.cust_name"/>'></td>
								<TD>客户级别：</TD>
								<TD>
								<select id="level" name="cust_level.dict_id" style="WIDTH: 180px">
										<s:iterator value="listLevel" var="basedict">
												<option value="<s:property value="#basedict.dict_id"/>">
													<s:property value="#basedict.dict_item_name"/>
												</option>
										</s:iterator>
									</select>
								</select>
								</TD>
							</TR>

							<TR>

								<td>信息来源 ：</td>
								<td>
								<select id="source" name="cust_source.dict_id"style="WIDTH: 180px">
									<s:iterator value="listSource" var="basedict">
										<option value="<s:property value="#basedict.dict_id"/>">
											<s:property value="#basedict.dict_item_name" />
										</option>
									</s:iterator>
								</select>
								</td>
								<td>所属行业 ：</td>
								<td><select id="industry" name="cust_industry.dict_id"
									style="WIDTH: 180px">
										<s:iterator value="listIndustry" var="basedict">
											<option value="<s:property value="#basedict.dict_id"/>">
												<s:property value="#basedict.dict_item_name" />
											</option>
										</s:iterator>
								</select></td>
							</TR>

							<TR>


								<td>固定电话 ：</td>
								<td><INPUT class=textbox id=sChannel2 style="WIDTH: 180px"
									maxLength=50  name="cust_phone" value='<s:property value="customerById.cust_phone"/>'></td>
								<td>移动电话 ：</td>
								<td><INPUT class=textbox id=sChannel2 style="WIDTH: 180px"
									maxLength=50 name="cust_mobile" value='<s:property value="customerById.cust_mobile"/>'></td>
							</TR>


							<tr>
								<td rowspan=2><INPUT class=button id=sButton2 type=submit
									value=" 保存 " name=sButton2></td>
							</tr>
						</TABLE>


					</TD>
					<TD width=15
						background="${pageContext.request.contextPath }/images/new_023.jpg">
						<IMG src="${pageContext.request.contextPath }/images/new_023.jpg"
						border=0>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG
						src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg"
						height=15></TD>
					<TD width=15><IMG
						src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
</BODY>
</HTML>
