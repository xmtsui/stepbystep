public class SinletonHungry{
	/**
	 *单例对象实例
	 */
	private static SinletonHungry instance = new SinletonHungry();
	public static SinletonHungry getInstance() {
		return instance;
	}
}