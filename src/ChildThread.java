public class ChildThread extends Thread{

    //private long startTime = System.currentTimeMillis();
    //private int duration;
    private final int  step;
    private final int id;
    private final StopThread stop;
    private long result = 0;
    public ChildThread(int id, int step,StopThread stop){
    this.step = step;
    this.id = id;

        this.stop = stop;
    }
    @Override
    public void run() {
        boolean isCanStop = false;
        long count=0;
//        while(System.currentTimeMillis() - startTime < duration){
//            count++;
//            result += step;
//        }
        do{
            count++;
            result +=step;
            isCanStop = stop.isCanStop(id-1);
        }while(!isCanStop);
        System.out.println(id + " - " + result + " - " + count);
    }
}
