package com.huawei.algorithm;

public class main {
public static void main(String[] args)
{
	System.out.println(Long.MAX_VALUE);
	long checker=0;
	for(int i=0; i<32; ++i)
		checker|=(1l<<i);
	System.out.println(checker);
	if(checker == 4294967295l)
		System.out.println("true");
	else
		System.out.println("false");
}
}
