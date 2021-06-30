package emotes;

import java.util.Random;
import processor.Processor;
import processor.Status;
import processor.StatusListener;

public class EmotesProcessor implements Processor {

    private Thread thread = null;
    String result;
    int min = 0;
    int max = 30;

    @Override
    public boolean submitTask(String task, StatusListener listener) {

        if(thread == null || thread.getState()==Thread.State.TERMINATED) {
            (thread = new Thread(() -> {

                final float taskIncrement = (float) (100.0 / task.length());
                float progress = 0;
                final StringBuilder stringBuilder = new StringBuilder();
                Status status;

                for(int i = 0; i < task.length(); i++){

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    status = new Status("adding emotes: " + task + " -> "
                            + stringBuilder + " ", progress);
                    listener.statusChanged(status);
                    progress += taskIncrement;

                    if(String.valueOf(task.charAt(i)).equals(" ")){

                        Random rand = new Random();
                        int randomNum = rand.nextInt((max - min) + 1) + min;


                        if(randomNum == 0){
                            stringBuilder.append(" :) ");
                        }

                        else if(randomNum == 1){
                            stringBuilder.append(" :D ");
                        }

                        else if(randomNum == 2){
                            stringBuilder.append(" ;) ");
                        }

                        else if(randomNum == 3){
                            stringBuilder.append(" :P ");
                        }

                        else if(randomNum == 4){
                            stringBuilder.append(" >:( ");
                        }

                        else
                            stringBuilder.append(String.valueOf(task.charAt(i)));
                    }
                    else
                        stringBuilder.append(String.valueOf(task.charAt(i)));
                }

                progress += taskIncrement;
                result = stringBuilder.toString();
                status = new Status("adding emotes: " + task + " -> " + stringBuilder.toString() +
                        " ", progress);
                listener.statusChanged(status); })).start();

            return true;
        }

        return false;
    }

    public String getInfo() {
        return "refactors code by adding emotes";
    }

    @Override
    public String getResult() {
        return result;
    }
}
