package SWProject.Model.booking;

import java.util.ArrayList;

public class Ride implements IRide {
    private ArrayList<IRideRequest> rideRequests;
    private int noOfPassengers;
    private boolean started, finished;

    public Ride(IRideRequest rideRequest) {
        rideRequests = new ArrayList<>();
        rideRequests.add(rideRequest);
        noOfPassengers = rideRequest.getNoOfPassengers();
        started = false;
        finished = false;
    }

    @Override
    public void addRequest(IRideRequest rideRequest) {
        rideRequests.add(rideRequest);        
    }

    @Override
    public int getNoOfPassengers() {
        return noOfPassengers;
    }

    @Override
    public String getSource() {
        return rideRequests.get(0).getSource();
    }

    @Override
    public String getDestination() {
        return rideRequests.get(0).getDestination();
    }

    @Override
    public boolean isFull() {
        return rideRequests.size() == noOfPassengers;
    }

    @Override
    public void start() {
        started = true;      
        //add this event to all the requests
        for (IRideRequest request : rideRequests) {
            request.addEvent("Captain arrived to user location", "Driver: " 
                            + request.getAcceptedOffer().getItsDriver().getPersonalInfo().getUsername() 
                            + ", Passenger: " + request.getItsPassenger().getPersonalInfo().getUsername());
        }  
    }

    @Override
    public void finish() {
        finished = true;  
         //add this event to all the requests
        for (IRideRequest request : rideRequests) {
            request.addEvent("Captian arrived to user destination", "Driver: " 
                            + request.getAcceptedOffer().getItsDriver().getPersonalInfo().getUsername() 
                            + ", Passenger: " + request.getItsPassenger().getPersonalInfo().getUsername());
        }      
    }

    @Override
    public boolean hasStarted() {
        return started;
    }

    @Override
    public boolean hasFinished() {
        return finished;
    }

    @Override
    public String toString() {
        return "Ride: {" +
                "source='" + rideRequests.get(0).getSource() + '\'' +
                ", distenation='" + rideRequests.get(0).getDestination() + '\'' +
                ", noOfPassengers='" + rideRequests.get(0).getNoOfPassengers() + '\'' +
                ", started='" + started + '\'' +
                ", finished='" + finished + '\'' +
                '}';
    }
}
