package processor;

public class Status {

    /**
     * nazwa zadania
     */
    private String taskName;
    /**
     * progres w procentach
     */
    private Float progress;

    public Float getProgress() {

        if(progress > 100)
            return 100.0F;
        return progress;
    }

    public String getTaskName() {
        return taskName;
    }

    public Status(String taskName, Float progress) {
        this.taskName = taskName;
        this.progress = progress;
    }
}
