import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
    int numberOfThreads = 3;
    int[] steps = FillArraySteps(numberOfThreads);

    int[] threadStopTime = FillRandomTime(numberOfThreads);
      //int[] threadStopTime = {3000,5000,2000};
        StopThread stopThread = new StopThread(threadStopTime);
        new Thread(stopThread).start();
    for(int i = 1; i <= numberOfThreads;i++ ){
        new ChildThread(i,steps[i-1],stopThread).start();
    }
 }
 public static int[] FillArraySteps(int n){
        int[] steps = new int[n];
        for(int i = 0 ; i < steps.length;i++){
            steps[i] = i+1;
        }
        return steps;
 }
 public static int[] FillRandomTime(int n){
        int[] arrayTimes = new int[n];
        Random random = new Random();
        for(int i = 0 ; i < n; i++)
        {
            arrayTimes[i] = random.nextInt(6)+1;
            arrayTimes[i] *= 1000;
        }
        return arrayTimes;
 }
}
