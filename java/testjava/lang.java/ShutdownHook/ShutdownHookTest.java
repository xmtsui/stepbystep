/**
 * Java应用系统退出事件处理机制
 */

import java.util.*;
import java.io.*;

public class ShutdownHookTest {

	public ShutdownHookTest() {
		doShutDownWork();
	}

	/**
	 * 这里为了演示，为应用程序的退出增加了一个事件处理，
	 * 当应用程序退出时候，将程序退出的日期写入 d:/t.log文件
	 * This is the right work that will do before the system shutdown
	 */
	private void doShutDownWork() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
						FileWriter fw = new FileWriter("/Users/saixiaomin/Downloads/sharedata/hook.log");
						System.out.println("Im going to end");
						fw.write("the application ended! " + (new Date()).toString());
						fw.close();
					}catch (IOException e) {
						e.printStackTrace();
					}//end of try catch
				}//end of run
			});//end of method
	}//end of doShutDownWork

	public static void main(String[] args) {
		ShutdownHookTest untitled11 = new ShutdownHookTest();
		long s = System.currentTimeMillis();
		for (int i = 0; i < 1000000000; i++) {
			//在这里增添您需要处理代码
		}
		long se = System.currentTimeMillis();
		System.out.println(se - s);
	}//end of main
}