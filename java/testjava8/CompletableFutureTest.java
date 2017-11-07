import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

class CompletableFutureTest {
	// (int x, int y) -> x + y
	static Runnable s = () -> System.out.println("HelloJava8World");

	public static void main(String[] args) throws ExecutionException, InterruptedException {
        s.run();

        // test future
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Future<String> result = executor.submit(() -> {
            TimeUnit.SECONDS.sleep(1);
            return "hello future!";
        });
        System.out.println(result.get());

        // test completable future : normal
        CompletableFuture<String> resultCompletableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    // TODO
                }
                return "hello completable future!";
            }
        }, executor);
        System.out.println(resultCompletableFuture.get());

        // test completable future 2 : exception
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                throw new RuntimeException("!!!");
            }
        });
        future.whenComplete((response, exception) -> {
            System.out.println(response);
            System.out.println(exception);
        });
    }
}