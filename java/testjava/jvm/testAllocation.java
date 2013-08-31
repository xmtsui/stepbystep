class testAllocation{
	private static final int _1MB = 1024 * 1024;

/**
 * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * j -client -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC -Xloggc:gc.log testAllocation
 * j -client -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:+PrintGCApplicationStoppedTime -XX:+PrintGCDateStamps -Xloggc:gc.log testAllocation
 * -XX:+UseSerialGC -Xloggc:gc.log
 * -XX:+PrintGCApplicationStoppedTime
 * -XX:+PrintGCDateStamps
 */
public static void main(String[] args) {
	byte[] allocation1, allocation2, allocation3, allocation4;
	allocation1 = new byte[1 * _1MB];
	allocation2 = new byte[1 * _1MB];
	allocation3 = new byte[1 * _1MB];
 	allocation4 = new byte[4 * _1MB];  // 出现一次Minor GC
 }
}