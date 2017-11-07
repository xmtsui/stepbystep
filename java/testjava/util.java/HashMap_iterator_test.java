import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * 测试各遍历集合，如果原始map变化，会跟着变化
 */
class HashMap_iterator_test {
	public static void main(String[] args) throws InterruptedException {
        final Map<String, String> m = new HashMap<String, String>();
        m.put("a", "hehe");
        m.put("b", "hehe");
        m.put("c", "hehe");
        Map<String, String> n = new HashMap<String, String>();
        n.putAll(m);

        Set<String> keys = m.keySet();
        Set<Map.Entry<String, String>> entries = m.entrySet();
        Collection<String> values = m.values();

        Set<String> keysn = n.keySet();
        Set<Map.Entry<String, String>> entriesn = n.entrySet();
        Collection<String> valuesn = n.values();

        System.out.println(keys.size() + " " + keysn.size());
        System.out.println(entries.size() + " " + entriesn.size());
        System.out.println(values.size() + " " + valuesn.size());
        final CountDownLatch latch = new CountDownLatch(1);
        new Thread() {
            public void run() {
                m.clear();
                latch.countDown();
            }
        }.start();

        latch.await();
        System.out.println(keys.size() + " " + keysn.size());
        System.out.println(entries.size() + " " + entriesn.size());
        System.out.println(values.size() + " " + valuesn.size());
    }
}