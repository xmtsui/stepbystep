import java.util.concurrent.ConcurrentSkipListMap;
import java.util.Comparator;
import java.util.Random;

/**
 * test concurrent skip list
 */
class ConcurrentSkipListMapTest {
	public static void main(String[] args) {
        ConcurrentSkipListMap<Long, Long> map1 = new ConcurrentSkipListMap<Long, Long>();
        ConcurrentSkipListMap<String, String> map2 = new ConcurrentSkipListMap<String, String>(new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                return Long.parseLong(str1) > Long.parseLong(str2) ? 1 : -1;
            }
        });

        Random random = new Random();

        long start = System.currentTimeMillis();
		for(long i=0; i<20000; ++i){
            long rand = random.nextLong();
            map1.put(rand, i);
        }
		long end = System.currentTimeMillis();
		long time = end - start;

		long start1 = System.currentTimeMillis();
		for(long i=0; i<20000; ++i){
            long rand = random.nextLong();
            map2.put(String.valueOf(rand), String.valueOf(i));
        }
		long end1 = System.currentTimeMillis();
		long time1 = end1 - start1;

		System.out.println(time + "\t|\t" + time1);
    }
}