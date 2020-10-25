import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final int minMessageCount = 5;
        final int maxMessageCount = 10;
        final int threadsCount = 4;
        List<MyCallable> myCallables= new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < threadsCount; i++) {
            myCallables.add(new MyCallable("Поток " + i,
                    minMessageCount + random.nextInt(maxMessageCount - minMessageCount +1)));
        }

        final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        System.out.println("Started invoking all");
        final List<Future<Integer>> tasksAll = threadPool.invokeAll(myCallables);
        for (Future<Integer> task : tasksAll) {
            System.out.printf("Messages sent: %s\n", task.get());
        }

        System.out.println("Started invoking any");
        int result = threadPool.invokeAny(myCallables);
        System.out.printf("Messages sent: %s\n", result);

        threadPool.shutdown();
    }
}