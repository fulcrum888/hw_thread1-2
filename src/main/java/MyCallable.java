import java.util.Random;
import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
    int messageCount;
    public MyCallable(String name, int messageCount) {
        Thread.currentThread().setName(name);
        this.messageCount = messageCount;
    }

    @Override
    public Integer call() {

        Random random = new Random();
        int messagesSent = 0;
        try {

            for (int i = 0; i < messageCount; i++) {
                System.out.printf("The thread %s is now working\n", Thread.currentThread().getId());
                Thread.sleep(random.nextInt(1000) + 2000);
                messagesSent++;
            }
        } catch (InterruptedException err) {return messagesSent;}
        return messagesSent;
    }
}
