package frc.robot;
public class Stopwatch {
    private long startTime;
    private long endTime;
    private boolean isRunning;

    public Stopwatch() {
        reset();
    }
    
    public void start() {
        if(!isRunning){
            startTime = System.currentTimeMillis();
            isRunning = true;
        }
    }

    public void stop(){
        if(isRunning){
            endTime = System.currentTimeMillis();
            isRunning = false;
        }
    }

    public void reset(){
        startTime = 0;
        endTime = 0;
        isRunning = false;
    }

    public long getElapsedTimeMilliseconds() {
        if(isRunning){
            return System.currentTimeMillis() - startTime;
        } else {
            return (endTime - startTime) / 10000L;
        }
    }

    public boolean isRunning(){
        return isRunning;
    }
}
