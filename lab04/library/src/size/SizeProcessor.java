package size;

import java.util.Random;
import processor.Processor;
import processor.Status;
import processor.StatusListener;

public class SizeProcessor implements Processor {

    private Thread thread = null;
    String result;
    int min = 1;
    int max = 2;

    @Override
    public boolean submitTask(String task, StatusListener listener) {

        if(thread == null || thread.getState()==Thread.State.TERMINATED) {
            (thread = new Thread(() -> {

                final float taskIncrement = (float) (100.0 / task.length());
                float progress = 0;
                final StringBuilder stringBuilder = new StringBuilder();
                Status status;
                Random rand = new Random();

                for(int i = 0; i < task.length(); i++){

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    status = new Status("changing letters sizes: " + task + " -> "
                            + stringBuilder + " ", progress);
                    listener.statusChanged(status);
                    progress += taskIncrement;

                    int randomNum = rand.nextInt((max - min) + 1) + min;
                    if(randomNum % 2 == 0){
                        stringBuilder.append(String.valueOf(task.charAt(i)).toUpperCase());
                    }
                    else{
                        stringBuilder.append(task.charAt(i));
                    }
                }

                progress += taskIncrement;
                result = stringBuilder.toString();
                status = new Status("changing letters sizes:: " + task + " -> " + stringBuilder.toString() +
                        " ", progress);
                listener.statusChanged(status); })).start();

            return true;
        }

        return false;
    }

    public String getInfo() {
        return "refactors code by changing letters sizes:";
    }

    @Override
    public String getResult() {
        return result;
    }
}
