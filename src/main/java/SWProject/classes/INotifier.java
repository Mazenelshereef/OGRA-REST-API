package SWProject.classes;

public interface INotifier {
    public void notifyDriversWithRide(IRideRequest rideRequest);
    public void notifyDriverWithRating(IRating rating);
    public void notifyPassengerWithOffer(IOffer offer);
}
