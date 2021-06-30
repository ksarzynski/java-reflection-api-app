package processor;

public class MyStatusListener implements StatusListener {

    public void statusChanged(Status status) {
        System.out.println(status.getTaskName() + ':' + status.getProgress().shortValue());
    }
}
