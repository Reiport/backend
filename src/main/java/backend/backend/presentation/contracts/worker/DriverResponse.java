package backend.backend.presentation.contracts.worker;

public class DriverResponse {

    private Boolean hasAdr;
    private Boolean hasCam;
    private String cc;
    private Boolean isWorking;
    private WorkerResponse guest;

    public DriverResponse() {
    }

    public DriverResponse(Boolean hasAdr, Boolean hasCam, String cc, Boolean isWorking, WorkerResponse guest) {
        this.hasAdr = hasAdr;
        this.hasCam = hasCam;
        this.cc = cc;
        this.isWorking = isWorking;
        this.guest = guest;
    }

    public Boolean getHasAdr() {
        return hasAdr;
    }

    public void setHasAdr(Boolean hasAdr) {
        this.hasAdr = hasAdr;
    }

    public Boolean getHasCam() {
        return hasCam;
    }

    public void setHasCam(Boolean hasCam) {
        this.hasCam = hasCam;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public Boolean getIsWorking() {
        return isWorking;
    }

    public void setIsWorking(Boolean isWorking) {
        this.isWorking = isWorking;
    }

    public WorkerResponse getGuest() {
        return guest;
    }

    public void setGuest(WorkerResponse guest) {
        this.guest = guest;
    }

}
