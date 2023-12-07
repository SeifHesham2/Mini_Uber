public class Complaints {
    private int complaintID;
    private int tripID;
    private String description;
    private boolean opened;

    public Complaints() {
    }

    public Complaints(int complaintID, int tripID, String description, boolean opened) {
        this.complaintID = complaintID;
        this.tripID = tripID;
        this.description = description;
        this.opened = opened;
    }

    public int getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(int complaintID) {
        this.complaintID = complaintID;
    }

    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }
}
