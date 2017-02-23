package com.hk.common.report;

import java.io.ByteArrayOutputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

public class Exporter{
	/**
	* Exports a report to XLS (Excel) format. You can declare another export here, i.e PDF or CSV.
	* You don't really need to create a separate class or method for the exporter. You can call it
	* directly within your Service or Controller.
	* 
	* @param jp the JasperPrint object
	* @param baos the ByteArrayOutputStream where the report will be written
	*/
	public void exportXLS(JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
		// Create a JRXlsExporter instance
		JRXlsxExporter exporter = new JRXlsxExporter();
	 
		// Here we assign the parameters jp and baos to the exporter
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
	 
		// Excel specific parameters
		// Check the Jasper (not DynamicJasper) docs for a description of these settings. Most are 
		// self-documenting
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
	 
		// Retrieve the exported report in XLS format
		exporter.exportReport();
	}
	
	public void exportXLSOld(JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
		// Create a JRXlsExporter instance
		JRXlsExporter exporter = new JRXlsExporter();
	 
		// Here we assign the parameters jp and baos to the exporter
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
	 
		// Excel specific parameters
		// Check the Jasper (not DynamicJasper) docs for a description of these settings. Most are 
		// self-documenting
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
	 
		// Retrieve the exported report in XLS format
		exporter.exportReport();
	}
	
	public void exportPDF(JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
		// Create a JRPdfExporter instance
		JRPdfExporter exporter = new JRPdfExporter();
		 
		// Here we assign the parameters jp and baos to the exporter
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
		exporter.setParameter(JRPdfExporterParameter.PRINT_SCALING,"120");
		// Retrieve the exported report in PDF format
		exporter.exportReport();
	}
	
	public void exportCSV(JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
		// Create a JRPdfExporter instance
		JRCsvExporter exporter = new JRCsvExporter();
		 
		// Here we assign the parameters jp and baos to the exporter
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
		exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, true);
		exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, true);
		exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, true);
		exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, true);
		 
		// Retrieve the exported report in XLS format
		exporter.exportReport();
	}
	
		
	public void exportRTF(JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
		// Create a JRPdfExporter instance
		JRRtfExporter exporter = new JRRtfExporter();
		 
		// Here we assign the parameters jp and baos to the exporter
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
		
		exporter.exportReport(); 
	}
	
	public void exportHTML(JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
		// Create a JRPdfExporter instance
		JRHtmlExporter  exporter = new  JRHtmlExporter();
		exporter.setParameter(JRHtmlExporterParameter.JASPER_PRINT, jp);
		exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, false);
		exporter.setParameter(JRHtmlExporterParameter.ZOOM_RATIO, 2F);
		exporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
	    exporter.setParameter(JRHtmlExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
	    exporter.setParameter(JRHtmlExporterParameter.OUTPUT_STREAM, baos);
		exporter.exportReport(); 
	}
	
	public void exportText(JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
		// Create a JRPdfExporter instance
		JRTextExporter  exporter = new  JRTextExporter();
		exporter.setParameter(JRTextExporterParameter.JASPER_PRINT, jp);
	    exporter.setParameter(JRTextExporterParameter.OUTPUT_STREAM, baos);
	    exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, new Integer(7));
	    exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, new Integer(11));
		exporter.exportReport(); 
	}
}
