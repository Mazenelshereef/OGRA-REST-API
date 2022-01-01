package SWProject.Model.notification;

import SWProject.Model.booking.IOffer;
import SWProject.Model.booking.IRating;
import SWProject.Model.booking.IRideRequest;
import SWProject.Model.storage.SystemData;
import SWProject.Model.users.drivers.IDriver;

public class Notifier implements INotifier {
    private static Notifier instance;
    
    private Notifier(){}

    public static Notifier getInstance(){
        if (instance == null)
            instance = new Notifier();
        return instance;
    }

    @Override
    public void notifyDriversWithRide(IRideRequest rideRequest) {
        for (IDriver driver : SystemData.getInstance().getDriversWithFavouriteArea(rideRequest.getSource())){
            if (driver.isAvailable()){
                driver.recieveNotification("(Ride request): " + rideRequest.toString());
            }
        }
    }

    @Override
    public void notifyDriverWithRating(IRating rating) {
        rating.getItsDriver().recieveNotification("(Rating recieved): " + rating.toString());
    }

    @Override
    public void notifyPassengerWithOffer(IOffer offer) {
        offer.getItsRideRequest().getItsPassenger().recieveNotification("(Offer recieved): " + offer.toString());
    }


}
