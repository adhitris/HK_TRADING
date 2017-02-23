package com.hk.common.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class ToRupiahFormat {
	
	private static Locale locale = null;
	private static NumberFormat rupiahFormat = null;

	public static String changeToRupiahFormat(String nominal) {
        /*String rupiah = "";
         
        locale = new Locale("ca", "CA");
        rupiahFormat = NumberFormat.getCurrencyInstance(locale);
         
        rupiah = rupiahFormat.format(Double.parseDouble(nominal)).substring(4);
         
     
        String rupiahReplaceDot = rupiah.replaceAll(",",".");
        
        return rupiahReplaceDot;*/
		NumberFormat nf;
	    double x = new Double(nominal);
	    nf = NumberFormat.getInstance();
	    
	    String tmp=nf.format(x);
	    
	    return tmp.replace(",", ".");
    }
	
	public static String changeToRpiahFormatNegative(String nominal){
		NumberFormat nf;
	    double x = new Double(nominal);
	    nf = NumberFormat.getInstance();
	    String tmp=nf.format(x);
	    
	    return tmp.replace(",", ".");
	}
}
