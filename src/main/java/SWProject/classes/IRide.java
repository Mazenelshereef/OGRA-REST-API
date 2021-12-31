package SWProject.classes;

import java.util.ArrayList;

public interface IRide {
    public void addRequest(IRideRequest rideRequest);
    public ArrayList<IRideRequest> getRequests();
}
