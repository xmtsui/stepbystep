package com.huawei.contest;

import java.util.Scanner;


public class Main3 {
	static int CalculateWeekDay(int y, int m, int d)
	{
		if (m == 1 || m == 2) {
			m += 12;
			y--;
		}
		int weekDay = (d + 2 * m + 3 * (m + 1) / 5 + y + y / 4 - y / 100 + y / 400) % 7;
		return weekDay;
	}

	static int doFindLast6(int year, int mon, int daynum)
	{
		for(int i=1; i<=daynum; ++i)
		{
			if(CalculateWeekDay(year,mon,i) == 5)
			{
				if(i+7>daynum)
					return i;
			}
		}
		return -1;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int year = sc.nextInt();
		int mon = sc.nextInt();
//		System.out.println(2012%4); 
		int[] days={0,31,28,31,30,31,30,31,31,30,31,30,31};
		int[] days1={0,31,29,31,30,31,30,31,31,30,31,30,31};
		int res=0;
		if(year%4 == 0 && year%100 != 0)
			res = doFindLast6(year,mon,days1[mon]);
		else if(year%400 == 0)
			res = doFindLast6(year,mon,days1[mon]);
		else
			res = doFindLast6(year,mon,days[mon]);
		
		if(res != -1)
			System.out.println(res);
		/*
		if( year%4 == 0 && year%100 !=0)
		{	//ues days1;
			int daynum = days1[mon];
//			System.out.println("1"+daynum);
			for(int i=1; i<=daynum; i++)
				if(CalculateWeekDay(year,mon,i) == 5)
				{
					if(i+7>daynum)
						System.out.println(i);
				}
		}
		else if( year%400 == 0)
		{
			//use days1
			int daynum = days1[mon];
//			System.out.println("2"+daynum);
			for(int i=1; i<=daynum; i++)
				if(CalculateWeekDay(year,mon,i) == 5)
				{
					if(i+7>daynum)
						System.out.println(i);
				}
		}
		else
		{
			//use days
			int daynum = days[mon];
//			System.out.println("3"+daynum);
			for(int i=1; i<=daynum; i++)
				if(CalculateWeekDay(year,mon,i) == 5)
				{
					if(i+7>daynum)
						System.out.println(i);
				}
		}*/
	}
}
