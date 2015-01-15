package com.youku.share.crowdfunding.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

public class Utils{
	public static final String dateFormatExp = "yyyy-MM-dd HH:mm:ss";
	public static final String dateFormatExp1 = "yyyyMMddHHmmss";
    public static final DateFormat standDateFormator = new SimpleDateFormat(dateFormatExp);
    public static final DateFormat standDateFormator1 = new SimpleDateFormat(dateFormatExp1);
    
    private static AtomicLong al = new AtomicLong();
    
    
	public static boolean isAjaxRequest(HttpServletRequest request) {
		boolean isAjaxRequest = false;
		@SuppressWarnings("rawtypes")
		Enumeration headerNameEnum = request.getHeaderNames();
		while(headerNameEnum.hasMoreElements()){
			Object headerName = headerNameEnum.nextElement();
			String headerNameString = headerName.toString();
			String headerValueString = request.getHeader(headerNameString);
			if("X-Requested-With".equalsIgnoreCase(headerNameString)){
				if("XMLHttpRequest".equalsIgnoreCase(headerValueString)){
					isAjaxRequest = true;
					break;
				}
			}
		}
		return isAjaxRequest;
	}
	
	public static String generateSerialNumber(){
		long serial = al.getAndIncrement();
		String dateStr = standDateFormator1.format(new Date());
		return dateStr + "_" + serial;
	}
}