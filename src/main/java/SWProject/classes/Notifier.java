package SWProject.classes;

public class Notifier implements INotifier {
    private static Notifier instance;
    
    private Notifier(){}

    public static Notifier getInstance(){
        if (instance == null)
            instance = new Notifier();
        return instance;
    }

    @Override
    public void notifyDriversWithRide(IRide ride) {
        for (IDriver driver : SystemData.getInstance().getDriversWithFavouriteArea(ride.getSource())){
            if (driver.getCurrentRide()==null){
            driver.recieveNotification("(Ride request): " + ride.toString());
            }
        }
    }

    @Override
    public void notifyDriverWithRating(IRating rating) {
        rating.getItsDriver().recieveNotification("(Rating recieved): " + rating.toString());
        rating.getItsDriver().updateAverageRating();
    }

    @Override
    public void notifyPassengerWithOffer(IOffer offer) {
        offer.getItsRide().getItsPassenger().recieveNotification("(Offer recieved): " + offer.toString());
    }


}
