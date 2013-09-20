class SinletonLazy{
	private static class SingletonHolder {
		/**
		 * 单例对象实例
		 */
		static final SinletonLazy INSTANCE = new SinletonLazy();
	}


	public static SinletonLazy getInstance() {
		return SingletonHolder.INSTANCE;
	}
}