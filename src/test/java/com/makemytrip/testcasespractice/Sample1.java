package com.makemytrip.testcasespractice;

import java.util.Random;

public class Sample1 {
	static String string = "jakay590@gmail.com";

	public static void main(String[] args) {
		
		
		Random random = new Random();
		int r = random.nextInt(100);
		String var1 = string.split("@")[0];
		String var2 = string.split("@")[1];
		String var3 = var1+r+"@"+var2;
		System.out.println(var3);
		
		String s = "homepage.loginpage.login.emailerrmsg|homepage.loginpage.login.pwderrmsg";
		System.out.println(s.split("\\|")[1]);
		
		StringBuilder stringbuilder = new StringBuilder();
		
		
		
		for(int i=0;i<10;i++){
			//System.out.println(stringbuilder.append(random.nextInt(10)));
			stringbuilder.append(random.nextInt(10));
		}
		System.out.println(stringbuilder.toString());
	}
}
