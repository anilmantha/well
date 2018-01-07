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
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"%>
<%@page import="net.sf.jasperreports.engine.JRDataSource"%>
<%@page import="net.sf.jasperreports.engine.export.JRXlsExporter"%>
<%@page import="net.sf.jasperreports.export.SimpleExporterInput"%>
<%@page import="net.sf.jasperreports.export.SimpleOutputStreamExporterOutput"%>
<%@page import="net.sf.jasperreports.export.SimpleXlsReportConfiguration"%>
<%@page import="net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter"%>
<%@page import="net.sf.jasperreports.engine.JRExporterParameter"%>
<%@page import="org.apache.poi.util.IOUtils"%>
<%@page import="net.sf.jasperreports.engine.export.JRXlsExporterParameter"%>
<%

try{
	List<Map<String,?>> datasource=(List<Map<String,?>>)request.getAttribute("reviewlist");
	String type = request.getAttribute("generatetype").toString();
	
	if(type.equals("excel")){
		JRDataSource jrdatasource=new JRBeanCollectionDataSource(datasource);
		String jrXmlFile=session.getServletContext().getRealPath("/reports/ExcelReview.jrxml");
		String output=session.getServletContext().getRealPath("/reports/test.xls");
		
		InputStream input=new FileInputStream(new File(jrXmlFile));
		JasperReport jasperReport=JasperCompileManager.compileReport(input);
		JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport, new HashMap() ,jrdatasource);
		
		System.out.println(type);
		JRXlsxExporter Xlsxexporter = new JRXlsxExporter();
		File xlsFile = new File(output);
		Xlsxexporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
		Xlsxexporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE, xlsFile);
		Xlsxexporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		Xlsxexporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		Xlsxexporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
		Xlsxexporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,Boolean.TRUE);
		Xlsxexporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,Boolean.TRUE);
		Xlsxexporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8"); 
		Xlsxexporter.exportReport();
		FileInputStream fis = new FileInputStream(new File(output));
		IOUtils.copy(fis, response.getOutputStream());
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=" + "ExcelReview" + ".xlsx"); 
		response.flushBuffer();
		fis.close();
		System.out.println("Closed*********************");
	}
	else {
		JRDataSource jrdatasource=new JRBeanCollectionDataSource(datasource);
		String jrXmlFile=session.getServletContext().getRealPath("/reports/ReviewReports.jrxml");
		String output=session.getServletContext().getRealPath("/reports/test.xls");
		
		InputStream input=new FileInputStream(new File(jrXmlFile));
		JasperReport jasperReport=JasperCompileManager.compileReport(input);
		JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport, new HashMap() ,jrdatasource);
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
}
catch(Exception e){
	e.printStackTrace();
}
%>