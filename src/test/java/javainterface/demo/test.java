package javainterface.demo;

import java.util.Calendar;
import java.util.Date;

public class test {

	public static void main(String[] args) {
		//方法 一
		System.out.println(System.currentTimeMillis());
		//方法 二
		System.out.println(Calendar.getInstance().getTimeInMillis());
		//方法 三
		System.out.println(new Date().getTime());

	}
}
