package SWProject.classes;

public interface IRide {
    public void addRequest(IRideRequest rideRequest);
    public int getNoOfPassengers();
    public String getSource();
    public String getDestination();
    public void start();
    public void finish();
    public boolean hasStarted();
    public boolean hasFinished();
    public boolean isFull();
}
