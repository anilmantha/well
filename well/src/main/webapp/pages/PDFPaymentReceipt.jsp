<%@page import="java.util.ArrayList"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page contentType="application/pdf" %> 
<%@page trimDirectiveWhitespaces="true"%>
<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="net.sf.jasperreports.engine.JasperCompileManager"%>
<%@page import="net.sf.jasperreports.engine.JasperReport"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"%>
<%@page import="net.sf.jasperreports.engine.JRDataSource"%>
 <%-- <%@ page language="java" contentType="application/pdf; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>  --%>

<%
System.out.println("***************1111111111111111111111111111111111111111111111111111111111************");
try{

	Map<String,Object> newHash=new LinkedHashMap<String,Object>();
	Map<String,String> saq123=new LinkedHashMap<String,String>();
    saq123.put("pname","asdf");
	saq123.put("pqty","asd12f");
	saq123.put("prate","asd12f");
	List<Map<String,String>> sampe=new ArrayList<Map<String,String>>();
	sampe.add(saq123);

	List<Map<String,?>> datasource=(List<Map<String,?>>)request.getAttribute("listProducts");
	List<Map<String,String>> sampe1=(List<Map<String,String>>)request.getAttribute("listProducts1");
	newHash.put("fieldw",sampe1);
	System.out.println(sampe1+"------------exprot to sampel;"+sampe1.size());
	JRDataSource jrdatasource=new JRBeanCollectionDataSource(datasource);
	String jrXmlFile=session.getServletContext().getRealPath("/reports/paymentreceipt.jrxml");

	InputStream input=new FileInputStream(new File(jrXmlFile));
	JasperReport jasperReport=JasperCompileManager.compileReport(input);
	JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport, newHash ,jrdatasource);
	JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
	response.getOutputStream().flush();
	response.getOutputStream().close();
}
catch(Exception e){
	e.printStackTrace();
	System.out.println("exception ocuured");
}
System.out.println("HelloJasper.pdf has been generated!");
%>

