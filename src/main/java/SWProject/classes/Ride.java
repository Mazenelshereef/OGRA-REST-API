package SWProject.classes;

import java.util.ArrayList;

public class Ride implements IRide {
    private ArrayList<IRideRequest> rideRequests;

    public Ride(IRideRequest rideRequest) {
        rideRequests = new ArrayList<>();
        rideRequests.add(rideRequest);
    }

    @Override
    public void addRequest(IRideRequest rideRequest) {
        rideRequests.add(rideRequest);        
    }

    @Override
    public ArrayList<IRideRequest> getRequests() {
        return rideRequests;
    }

    @Override
    public String toString() {
        return "Ride: {" +
                "source='" + rideRequests.get(0).getSource() + '\'' +
                ", distenation='" + rideRequests.get(0).getDestination() + '\'' +
                ", noOfPassengers='" + rideRequests.get(0).getNoOfPassengers() + '\'' +
                '}';
    }
}
