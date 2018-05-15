package test;

import util.MD5;

public class TestMD5 {
	public static void main(String[] args) {
		System.out.println(new MD5().getMD5ofStr("1")) ;
		System.out.println(new MD5().getMD5ofStr("2")) ;
	}
}
