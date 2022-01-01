package SWProject.Model.notification;

import SWProject.Model.booking.IOffer;
import SWProject.Model.booking.IRating;
import SWProject.Model.booking.IRideRequest;

public interface INotifier {
    public void notifyDriversWithRide(IRideRequest rideRequest);
    public void notifyDriverWithRating(IRating rating);
    public void notifyPassengerWithOffer(IOffer offer);
}
