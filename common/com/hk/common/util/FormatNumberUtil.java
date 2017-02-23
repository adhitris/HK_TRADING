package com.hk.common.util;

import java.text.DecimalFormat;
import java.util.Date;



public class FormatNumberUtil {
	

	public static String getFormatNumberByMonthYear(String lastId,Date tanggal) {
		
		Integer monthNow=DateUtil.getMonthFromDate(tanggal);
		Integer yearNow=DateUtil.getYearFromDate(tanggal);
		String yearNowString=yearNow.toString().substring(2, 4);
		String dateNowString=monthNow.toString();
		String countString=null;
		Integer count=0;
		if(CommonUtil.isNotNullOrEmpty(lastId)){
			lastId=lastId.substring(6,11);
			count=new Integer(lastId);
		}else{
			count=0;
		}
		
		count++;
		if(count.toString().length()==1){
			countString="0000"+count;
		}else if(count.toString().length()==2){
			countString="000"+count;
		}else if(count.toString().length()==3){
			countString="00"+count;
		}else if(count.toString().length()==4){
			countString="0"+count;
		}else{
			countString=count.toString();
		}
		
		
		if((dateNowString.length())==1){
			dateNowString="0"+monthNow;
		}
        return yearNowString+"/"+dateNowString+"-"+countString;
    }
	
	public static String getFormatSo(String lastId,String kodeSo,String tanggal) {
	
		String yearNowString=tanggal.substring(3, 4);
		String monthNowString=tanggal.substring(5, 7);
		String countString=null;
		Integer count=0;
		if(CommonUtil.isNotNullOrEmpty(lastId)){
			lastId=lastId.substring(5,9);
			count=new Integer(lastId);
		}else{
			count=0;
		}
		
		count++;
		if(count.toString().length()==1){
			countString="000"+count;
		}else if(count.toString().length()==2){
			countString="00"+count;
		}else if(count.toString().length()==3){
			countString="0"+count;
		}else{
			countString=count.toString();
		}
		
		if(monthNowString.toString().length()==1){
			monthNowString="0"+monthNowString;
		}
	
        return yearNowString+kodeSo+monthNowString+"-"+countString;
    }
	
	public static String getFormatSoJasa(String lastId,String kodeSo,String tanggal) {
		
		String yearNowString=tanggal.substring(3, 4);
		String monthNowString=tanggal.substring(5, 7);
		String countString=null;
		Integer count=0;
		if(CommonUtil.isNotNullOrEmpty(lastId)){
			lastId=lastId.substring(5,9);
			count=new Integer(lastId);
		}else{
			count=0;
		}
		
		count++;
		if(count.toString().length()==1){
			countString="000"+count;
		}else if(count.toString().length()==2){
			countString="00"+count;
		}else if(count.toString().length()==3){
			countString="0"+count;
		}else{
			countString=count.toString();
		}
		
		if(monthNowString.toString().length()==1){
			monthNowString="0"+monthNowString;
		}
	
        return yearNowString+kodeSo+monthNowString+"J"+countString;
    }
	
	public static String getLastIdByParent(String parentId,String lastId) {
		
	
		String countString=null;
		Integer count=0;
		if(CommonUtil.isNotNullOrEmpty(lastId)){
			lastId=lastId.substring(12,14);
			count=new Integer(lastId);
		}else{
			count=0;
		}
		
		count++;
		if(count.toString().length()==1){
			countString="0"+count;
		}else{
			countString=count.toString();
		}
        return parentId+"-"+countString;
    }
	
	public static String getLastIdByParentPoBbm(String parentId,String lastId) {
		
		
		String countString=null;
		Integer count=0;
		if(CommonUtil.isNotNullOrEmpty(lastId)){
			lastId=lastId.substring(12);
			count=new Integer(lastId);
		}else{
			count=0;
		}
		
		count++;
		if(count.toString().length()==1){
			countString="0"+count;
		}else{
			countString=count.toString();
		}
        return parentId+"-"+countString;
    }
	
	public static String getLastIdByParentParent(String parentId,String lastId) {
		
		
		String countString=null;
		Integer count=0;
		if(CommonUtil.isNotNullOrEmpty(lastId)){
			lastId=lastId.substring(15,17);
			count=new Integer(lastId);
		}else{
			count=0;
		}
		
		if(count.toString().length()==1){
			countString="0"+count;
		}else{
			countString=count.toString();
		}
        return countString;
    }
	
	public static String getLastIdRolByParent(String parentId,String lastId) {
		
		
		String countString=null;
		Integer count=0;
		if(CommonUtil.isNotNullOrEmpty(lastId)){
			lastId=lastId.substring(15,17);
			count=new Integer(lastId);
		}else{
			count=0;
		}
		count++;
		if(count.toString().length()==1){
			countString="0"+count;
		}else{
			countString=count.toString();
		}
        return countString;
    }

	public static String getLastIdByParentKp(String parentId,String lastId) {
		
		
		String countString=null;
		Integer count=0;
		if(CommonUtil.isNotNullOrEmpty(lastId)){
			lastId=lastId.substring(21,23);
			count=new Integer(lastId);
		}else{
			count=0;
		}
		count++;
		if(count.toString().length()==1){
			countString="0"+count;
		}else{
			countString=count.toString();
		}
        return countString;
    }
	
	public static String getLastIdByParentKpForLoop(String parentId,String lastId) {
		
		
		String countString=null;
		Integer count=0;
		if(CommonUtil.isNotNullOrEmpty(lastId)){
			lastId=lastId.substring(21,23);
			count=new Integer(lastId);
		}else{
			count=0;
		}
		if(count.toString().length()==1){
			countString="0"+count;
		}else{
			countString=count.toString();
		}
        return countString;
    }
	
	public static String getLastIdByParentJasaGrupKp(String parentId,String lastId) {
		
		
		String countString=null;
		Integer count=0;
		if(CommonUtil.isNotNullOrEmpty(lastId)){
			int startChar = parentId.length();
			lastId=lastId.substring(startChar+1);
			count=new Integer(lastId);
		}else{
			count=0;
		}
		
		countString=count.toString();
		
        return countString;
    }

	public static String getLastIdByParentHdr(String parentId,String lastId) {
		
		
		String countString=null;
		Integer count=0;
		if(CommonUtil.isNotNullOrEmpty(lastId)){
			lastId=lastId.substring(12);
			count=new Integer(lastId);
		}else{
			count=0;
		}
		
		if(count.toString().length()==1){
			countString="0"+count;
		}else{
			countString=count.toString();
		}
        return countString;
    }
	
	public static String getLastNoRolByParentHdr(String lastId) {
		
		
		String countString=null;
		Integer count=0;
		if(CommonUtil.isNotNullOrEmpty(lastId)){
			lastId=lastId.substring(0,2);
			count=new Integer(lastId);
		}else{
			count=0;
		}
		
		if(count.toString().length()==1){
			countString="0"+count;
		}else{
			countString=count.toString();
		}
        return countString;
    }
	
	public static String getFormatNoOc(String lastId,Integer kodePo, Date tanggal,String ocSeparator,String ocSeparator2) {
		Integer monthNow=DateUtil.getMonthFromDate(tanggal);
		Integer yearNow=DateUtil.getYearFromDate(tanggal);
		String yearNowString=yearNow.toString().substring(2, 4);
		String monthNowString=monthNow.toString();
		String countString=null;
		Integer count=0;
		String kodePoNew="";
		if(CommonUtil.isNotNullOrEmpty(kodePo)){
			if(kodePo.toString().length()==1){
				kodePoNew="0"+kodePo;
			}else{
				kodePoNew=kodePo.toString();
			}
		}
		
		if(CommonUtil.isNotNullOrEmpty(lastId)){
			lastId=lastId.substring(6,10);
			count=new Integer(lastId);
		}else{
			count=0;
		}
		
		count++;
		if(count.toString().length()==1){
			countString="000"+count;
		}else if(count.toString().length()==2){
			countString="00"+count;
		}else if(count.toString().length()==3){
			countString="0"+count;
		}else{
			countString=count.toString();
		}
		
		if(monthNowString.toString().length()==1){
			monthNowString="0"+monthNowString;
		}
	
		if(CommonUtil.isNullOrEmpty(ocSeparator)){
			ocSeparator = "-";
		}
		
		if(CommonUtil.isNullOrEmpty(ocSeparator2)){
			ocSeparator2 = "-";
		}
	
		
        return kodePoNew+ocSeparator+yearNowString+ocSeparator2+countString;
	}
	
	public static String getFormatNoDo(String lastId,Integer kodePo, Date tanggal) {
		Integer monthNow=DateUtil.getMonthFromDate(tanggal);
		Integer yearNow=DateUtil.getYearFromDate(tanggal);
		String yearNowString=yearNow.toString().substring(2, 4);
		String monthNowString=monthNow.toString();
		String countString=null;
		Integer count=0;
		String kodePoNew="";
		if(CommonUtil.isNotNullOrEmpty(kodePo)){
			if(kodePo.toString().length()==1){
				kodePoNew="0"+kodePo;
			}else{
				kodePoNew=kodePo.toString();
			}
		}
		
		if(CommonUtil.isNotNullOrEmpty(lastId)){
			lastId=lastId.substring(6,10);
			count=new Integer(lastId);
		}else{
			count=0;
		}
		
		count++;
		if(count.toString().length()==1){
			countString="000"+count;
		}else if(count.toString().length()==2){
			countString="00"+count;
		}else if(count.toString().length()==3){
			countString="0"+count;
		}else{
			countString=count.toString();
		}
		
		if(monthNowString.toString().length()==1){
			monthNowString="0"+monthNowString;
		}
	
        return kodePoNew+"-"+yearNowString+"-"+countString;
	}
	
	public static String getFormatNoBbm(String lastId,Date tanggal) {
		
		Integer monthNow=DateUtil.getMonthFromDate(tanggal);
		Integer yearNow=DateUtil.getYearFromDate(tanggal);
		String yearNowString=yearNow.toString().substring(2, 4);
		String dateNowString=monthNow.toString();
		String countString=null;
		Integer count=0;
		if(CommonUtil.isNotNullOrEmpty(lastId)){
			lastId=lastId.substring(6,10);
			count=new Integer(lastId);
		}else{
			count=0;
		}
		
		count++;
		if(count.toString().length()==1){
			countString="000"+count;
		}else if(count.toString().length()==2){
			countString="00"+count;
		}else if(count.toString().length()==3){
			countString="0"+count;
		}else{
			countString=count.toString();
		}
		
		
		if((dateNowString.length())==1){
			dateNowString="0"+monthNow;
		}
        return yearNowString+"-"+dateNowString+"/"+countString;
    }
	
	public static String customFormat(String pattern, double value ) {
      DecimalFormat myFormatter = new DecimalFormat(pattern);
      String output = myFormatter.format(value);
      return output;
   }
	
	public static String getLastNoUrutByParent(String parentId,String lastNoUrut) {
		
		
		String countString=null;
		Integer count=0;
		if(CommonUtil.isNotNullOrEmpty(lastNoUrut)){
			count=new Integer(lastNoUrut);
		}else{
			count=0;
		}
		count++;
		if(count.toString().length()==1){
			countString="0"+count;
		}else{
			countString=count.toString();
		}
        return countString;
    }
	
	public static Integer getLastNoUrutByParentInt(String parentId,String lastNoUrut) {
		
		Integer count=0;
		if(CommonUtil.isNotNullOrEmpty(lastNoUrut)){
			count=new Integer(lastNoUrut);
		}else{
			count=0;
		}
		count++;
        return count;
    }
	
	public static String getLastIdBookingByParent(String parentId,String lastId) {
		
		
		String countString=null;
		Integer count=0;
		if(CommonUtil.isNotNullOrEmpty(lastId)){
			lastId=lastId.substring(15);
			count=new Integer(lastId);
		}else{
			count=0;
		}
		
		count++;
		countString=count.toString();
        return parentId+"-"+countString;
    }
}
