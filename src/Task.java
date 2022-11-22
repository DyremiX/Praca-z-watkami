import java.util.concurrent.Callable;

class Task implements Callable<String> {
    private int Duration;
    private String name;

    public Task(int duration, String name) {
        Duration = duration;
        this.name = name;
    }

    public String toString(){
        return name;
    }

    @Override
    public String call() throws Exception {
            try {
                Thread.sleep(Duration);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            Thread.yield();
            return "Done - " + name;
    }
}