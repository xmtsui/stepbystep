import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author tsui
 * @version $Id: TestIterator.java, v 0.1 2017-07-24 16:28 tsui Exp $
 */
public class TestIterator {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("ddd");
        list.add("ddd");
        list.add("ddd");
        Iterator<String> iter = list.iterator();
        System.out.println(list.size());
        while (iter.hasNext()) {
            iter.next();
            iter.remove();
        }
        System.out.println(list.size());
    }
}