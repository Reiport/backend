package backend.backend.domain.entities;

public class Statistics {

    private long execution;
    private long suspended;
    private long delivered;
    private long completed;
    private long schedualed;
    private long payed;

    public Statistics() {
    }

    public Statistics(long execution, long suspended, long delivered, long completed, long schedualed, long payed) {
        this.execution = execution;
        this.suspended = suspended;
        this.delivered = delivered;
        this.completed = completed;
        this.schedualed = schedualed;
        this.payed = payed;
    }

    public long getExecution() {
        return execution;
    }

    public void setExecution(long execution) {
        this.execution = execution;
    }

    public long getSuspended() {
        return suspended;
    }

    public void setSuspended(long suspended) {
        this.suspended = suspended;
    }

    public long getDelivered() {
        return delivered;
    }

    public void setDelivered(long delivered) {
        this.delivered = delivered;
    }

    public long getCompleted() {
        return completed;
    }

    public void setCompleted(long completed) {
        this.completed = completed;
    }

    public long getSchedualed() {
        return schedualed;
    }

    public void setSchedualed(long schedualed) {
        this.schedualed = schedualed;
    }

    public long getPayed() {
        return payed;
    }

    public void setPayed(long payed) {
        this.payed = payed;
    }

}
