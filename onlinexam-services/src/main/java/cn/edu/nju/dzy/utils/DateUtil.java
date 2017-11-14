package cn.edu.nju.dzy.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	// public static ZonedDateTime fromSecond(long second){
	//
	// Instant ins = Instant.ofEpochMilli(second*1000); // make sure
	// ZonedDateTime zd = ZonedDateTime.ofInstant(ins, ZoneId.systemDefault());
	// return zd;
	// }
	

	
	public static final String LOCAL_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static ZonedDateTime fromPiSecond(long second) {

		Instant ins = Instant.ofEpochMilli(second * 1000); // make sure
		LocalDateTime ld = LocalDateTime.ofInstant(ins, ZoneId.of("Z"));
		ZonedDateTime zd = ZonedDateTime.of(ld, ZoneId.of("GMT+8"));
		return zd;
	}
	


	public static String toLocalDateString(ZonedDateTime date) {
		if (date == null){
			return "";
		}
		return date.toLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE);
	}

	public static String toLocalDateTimeString(ZonedDateTime date) {
		if (date==null){
			return "";
		}
		return date.toLocalDateTime().format(DateTimeFormatter.ofPattern(LOCAL_DATE_PATTERN));
	}

	public static ZonedDateTime toDateStart(ZonedDateTime d0) {
		
		return  ZonedDateTime.of(d0.toLocalDate().atStartOfDay(),d0.getZone());
	}
	
	public static ZonedDateTime toDateEnd(ZonedDateTime d0) {
		
		return  ZonedDateTime.of(d0.plusDays(1l).toLocalDate().atStartOfDay(),d0.getZone());
	}


	public static Long toLong(ZonedDateTime d0) {
		
		return d0.toEpochSecond()+8*60*60;
	}
}
