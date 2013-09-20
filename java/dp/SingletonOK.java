/**   
 * 能应对大多数情况的单例实现
 */
import java.io.Serializable;
public class SingletonOK implements Serializable {

    private static class SingletonHolder {
        /**
         * 单例对象实例
         */
        static final SingletonOK INSTANCE = new SingletonOK();
    }

    public static SingletonOK getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**   
     * private的构造函数用于避免外界直接使用new来实例化对象
     */
    private SingletonOK() {
    }

    /**   
     * readResolve方法应对单例对象被序列化时候
     */
    private Object readResolve() {
        return getInstance();
    }
}    