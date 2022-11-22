import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Wykonwawca<V> {
    private FTask<V>[] lista;
    private int last_task_nr = 0;
    private ExecutorService My_exec; 

    public Wykonwawca(FTask<V>[] lista) {
        this.lista = lista;
        My_exec = Executors.newFixedThreadPool(2);  
    }

    public void Terminate(){
        for(int i = 0 ; i < last_task_nr ; i++){
            this.CancelTask(i, true);
        }
        My_exec.shutdown();
    }

    public void execute(FTask<V> command) {
        My_exec.execute(command);
        lista[last_task_nr] = command;
        last_task_nr++;
    }

    public void list() {
        int i = 0;
        while(lista[i] != null){
        System.out.printf("%d. %s - %s \n",i,lista[i].toString(),lista[i].State());
        i++;
        }
        return;
    }
    public void list(int i) {
        System.out.printf("%d. %s - %s \n",i,lista[i].toString(),lista[i].State());
        return;
    }

    public Object getResult(int i) throws InterruptedException, ExecutionException{
        if(lista[i].isDone()){
        return lista[i].get();
        }
        else{
            return "task is not done yet";
        }
    }

    public void CancelTask(int i, Boolean mayInterruptIfRunning){
        lista[i].cancel(mayInterruptIfRunning);
    }
}