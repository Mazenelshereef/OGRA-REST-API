package SWProject.classes;

import java.util.Calendar;

public class RideOfferFacade {
    private static RideOfferFacade instance;

    private RideOfferFacade(){}

    public static RideOfferFacade getInstance(){
        if (instance == null)
            instance = new RideOfferFacade();
        return instance;
    }

    public void requestRide(IRideRequest rideRequest){
        //apply discounts first
        if (!SystemData.getInstance().containsRideOfPassenger(rideRequest.getItsPassenger()))
            rideRequest = new FirstRideDiscount(rideRequest);
        if (SystemData.getInstance().containsDiscountArea(rideRequest.getDestination()))
            rideRequest = new AreaDiscount(rideRequest);
        if (rideRequest.getNoOfPassengers() > 1)
            rideRequest = new TwoPassengersDiscount(rideRequest);
        if (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == 6) // Friday (holiday)
            rideRequest = new HolidayDiscount(rideRequest);
        if (Calendar.getInstance().get(Calendar.MONTH) == rideRequest.getItsPassenger().getPersonalInfo().getMonthOfBirth() 
            && Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == rideRequest.getItsPassenger().getPersonalInfo().getDayOfBirth())
            rideRequest = new BirthdayDiscount(rideRequest);
        //then add this ride request to the system
        SystemData.getInstance().addRideRequest(rideRequest);
    }

    public boolean acceptOffer(IOffer offer){

        if (offer.getItsRideRequest().getItsPassenger().takeBalance(offer.getItsRideRequest().getCost(offer.getPrice())))
        {
            //in case there is no ride object created yet: 
            if (offer.getItsDriver().getCurrentRide() == null)
            {
                //create ride object with this request
                Ride ride = new Ride(offer.getItsRideRequest());
                //assign this ride to the driver's current ride
                offer.getItsDriver().setCurrentRide(ride);
                //add this ride to the system
                SystemData.getInstance().addRide(ride);
            }
            //in case there is already a ride object 
            else
            {
                //add this request to that ride
                offer.getItsDriver().getCurrentRide().addRequest(offer.getItsRideRequest());
            }
            offer.setAccepted(true);
            offer.getItsRideRequest().setPrice(offer.getPrice());
            offer.getItsDriver().addBalance(offer.getPrice());
            offer.getItsRideRequest().addEvent("user accepted the ride", "Passenger: " + offer.getItsRideRequest().getItsPassenger().getPersonalInfo().getUsername());
            return true;
        }
        return false;
    }

    public void denyOffer(IOffer offer){
        offer.setAccepted(false);
    }
}
