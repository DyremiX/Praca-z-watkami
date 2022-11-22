import java.util.concurrent.*;

public class FTask<T> extends FutureTask<T> {
    String name;

    public FTask(Callable<T> c) {
        super(c);
        name = "" + c.toString();
    }

    protected void done() {
        T result = null;
        try {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println(this + "interrupted!");
                return;
            }
            if (isCancelled()) {
                System.out.println(this + "cancelled!");
                return;
            }
            result = this.get();
            System.out.println("Zadanie " + this + " -> wykonane: " + result);
            return;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    protected String State() {
        return super.state().toString();
    }

    public String toString() {
    return "FTask [" + name + "]";
    }
}