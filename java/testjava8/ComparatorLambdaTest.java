import java.util.Arrays;

// FunctionInterface.
// There is no function type in java8, the only thing you can do is to use lambda expression to do function interface transform
public class ComparatorLambdaTest {
	public static void main(String[] args){
		String[] words = {"haha", "h", "heheheh"};
		Arrays.sort(words, (first, second) -> Integer.compare(first.length(), second.length()));
		System.out.println(Arrays.toString(words));
	}
}