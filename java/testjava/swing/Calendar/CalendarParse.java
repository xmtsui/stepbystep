/**
 * 没有使用Java的日期处理类库
 *
 */
public class  CalendarParse {

 public static Object[][] parse(String strYear,String strMonth) {
  //处理字符串
  int year = Integer.parseInt(strYear);
  int month = Integer.parseInt(strMonth);

  //定义一个数组存储日期数
  String[][]daysArray = new String[6][7];
  
  int days = 0; // 存储当月的天数
  boolean isRn;
  /* 判断是否是闰年 */
  if (year % 4 == 0 && !(year % 100 == 0) || year % 400 == 0) { // 判断是否为闰年
   isRn = true; // 闰年
  } else {
   isRn = false;// 平年
  }



  /* 计算输入的年份之前的天数 */
  int totalDays = 0;
  for (int i = 1900; i < year; i++) {
   /* 判断闰年或平年，并进行天数累加 */
   if (i % 4 == 0 && !(i % 100 == 0) || i % 400 == 0) { // 判断是否为闰年
    totalDays = totalDays + 366; // 闰年366天
   } else {
    totalDays = totalDays + 365; // 平年365天
   }
  }



  /* 计算输入月份之前的天数 */
  int beforeDays = 0;
  for (int i = 1; i <= month; i++) {
   switch (i) {
     case 1:
     case 3:
     case 5:
     case 7:
     case 8:
     case 10:
     case 12:
    days = 31;
    break;
     case 2:
    if (isRn) {
     days = 29;
    } else {
     days = 28;
    }
    break;
     default:
    days = 30;
    break;
   }
   if (i < month) {
    beforeDays = beforeDays + days;
   }
  }
  totalDays = totalDays + beforeDays; // 距离1900年1月到该月前一月的天数



  /* 计算星期几 */
  int firstDayOfMonth; // 存储当月第一天是星期几：星期日为0，星期一~星期六为1~6
  int temp = totalDays%7+1; // 从1900年1月1日为星期一
  if (temp == 7) { // 求当月第一天
   firstDayOfMonth = 0; // 周日
  } else {
   firstDayOfMonth = temp;
  }

  /* 输出日历 */
  int rows = 0;
  int cols = 0;
  //System.out.println("星期日 星期一 星期二 星期三 星期四 星期五 星期六");
  for (int nullNo = 0; nullNo < firstDayOfMonth; nullNo++) {
   //System.out.print(" *"); // 输出空格
	  daysArray[rows][nullNo] = "";  
  }
  //记录行号
  cols=firstDayOfMonth;//当月的第一天所在的列数
  for (int i = 1; i <= days; i++) {
	  
   if ((totalDays + i) % 7 == 6) {// 如果当天为周六，输出换行
	   daysArray[rows][cols] = i+""; 
	   rows++;
	   cols=0;
   }else{
      daysArray[rows][cols++] = i+"";
    }
  }
  return daysArray;
 }
 
 
 public static void main(String[] args) {
	 java.util.Calendar cal = java.util.Calendar.getInstance();
		
	 System.out.println(cal.get(java.util.Calendar.YEAR));
 }
}

