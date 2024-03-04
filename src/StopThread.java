import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StopThread implements Runnable {
    private final int[] delays;
    private volatile boolean[] isCompleted;

    public StopThread(int[] arrDelays) {
        this.delays = arrDelays;
        isCompleted = new boolean[delays.length];
    }

    @Override
    public void run() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(delays.length);
        for (int i = 0; i < delays.length; i++) {
            final int index = i;
            executorService.schedule(() -> {

                long startTime = System.currentTimeMillis();
                System.out.println("Затримка почалась для індексу " + (index+1));

                 //Імітуємо затримку
                try {
                    Thread.sleep(delays[index]);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                long endTime = System.currentTimeMillis();
                System.out.println("Затримка завершилась для індексу " + (index+1) +
                        ". Затратило часу: " + (endTime - startTime) + " мілісекунд");

                isCompleted[index] = true;

            }, delays[i], TimeUnit.MICROSECONDS);
        }
        executorService.shutdown();
    }

    synchronized public boolean isCanStop(int index) {
        return isCompleted[index];
    }
}