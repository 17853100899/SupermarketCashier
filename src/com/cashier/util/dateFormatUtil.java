package com.cashier.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class dateFormatUtil {
	public static String dateFormat(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

}
