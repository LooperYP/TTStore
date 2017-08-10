package com.ithiema.store.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.beanutils.Converter;

public class DateConverterUtils implements Converter{

	@Override
	public Date convert(Class arg0, Object arg1) {
		// arg1 代表的就是转换之前的数据,就是request.getparameter("birthday")方法的返回值
		Date date;
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			date = simpleDateFormat.parse((String) arg1);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
