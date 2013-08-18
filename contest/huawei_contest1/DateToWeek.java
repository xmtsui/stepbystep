import java.util.*;
class DateToWeek{
	static int CalculateWeekDay(int y, int m, int d)
	{
		if (m == 1 || m == 2) {
			m += 12;
			y--;
		}
		int weekDay = (d + 2 * m + 3 * (m + 1) / 5 + y + y / 4 - y / 100 + y / 400) % 7;
		return weekDay;
	}

	public static void main(String[] args)
	{
		System.out.println(CalculateWeekDay(2010,1,13));

		Calendar calendar = Calendar.getInstance();
		int weekofyear = calendar.get(Calendar.WEEK_OF_YEAR);
		int month=calendar.get(Calendar.MONTH);
		System.out.println(String.format("当天是第%d月,当天是一年当中的第%d周", 
			month+1,weekofyear));
	}
}