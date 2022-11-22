import java.util.concurrent.*;

public class FTask<T> extends FutureTask<T> {
    String name;
    public static String[] wyniki;
    private static int last_box = 0;

    public FTask(Callable<T> c, String[] wyniki) {
        super(c);
        name = "" + c.toString();
        FTask.wyniki = wyniki;
    }

    protected void done() {
        T result = null;
        try {
            if (Thread.currentThread().isInterrupted()) {
                wyniki[last_box] = (this + "interrupted!");
                last_box++;
                return;
            }
            if (isCancelled()) {
                wyniki[last_box] = (this + "cancelled!");
                last_box++;
                return;
            }
            result = this.get();
            wyniki[last_box] = ("Zadanie " + this + " -> wykonane: " + result);
            last_box++;
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