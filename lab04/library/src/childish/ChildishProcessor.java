package childish;

import processor.Processor;
import processor.Status;
import processor.StatusListener;

public class ChildishProcessor implements Processor {

    private Thread thread = null;
    String result;

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

                    status = new Status("making childish mistakes: " + task + " -> "
                            + stringBuilder + " ", progress);
                    listener.statusChanged(status);
                    progress += taskIncrement;

                    switch (String.valueOf(task.charAt(i))) {

                        case "ą" : {
                            stringBuilder.append("on");
                            break;
                        }


                        case "ę" : {
                            stringBuilder.append("en");
                            break;
                        }

                        case "ż" : {
                            stringBuilder.append("rz");
                            break;
                        }

                        case "d" : {
                            if(String.valueOf(task.charAt(i + 1)).equals("z")){

                                stringBuilder.append("c");
                                i++;
                            }
                            else
                                stringBuilder.append(task.charAt(i));

                            break;
                        }

                        case "o" : {
                            if(String.valueOf(task.charAt(i + 1)).equals("n")){

                                stringBuilder.append("ą");
                                i++;
                            }
                            else
                                stringBuilder.append(task.charAt(i));

                            break;
                        }

                        case "ó" : {
                            stringBuilder.append("u");
                            break;
                        }

                        case "u" : {
                            stringBuilder.append("ó");
                            break;
                        }

                        default : stringBuilder.append(task.charAt(i));
                    }
                }

                progress += taskIncrement;
                result = stringBuilder.toString();
                status = new Status("making childish mistakes: " + task + " -> " + stringBuilder.toString() +
                        " ", progress);
                listener.statusChanged(status); })).start();

            return true;
        }

        return false;
    }

    public String getInfo() {
        return "refactors code by making childlike mistakes";
    }

    @Override
    public String getResult() {
        return result;
    }
}
