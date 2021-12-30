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

    public void requestRide(IRide ride){
        //apply discounts first
        if (!SystemData.getInstance().containsRideOfPassenger(ride.getItsPassenger()))
            ride = new FirstRideDiscount(ride);
        if (SystemData.getInstance().containsDiscountArea(ride.getDestination()))
            ride = new AreaDiscount(ride);
        if (ride.getNoOfPassengers() > 1)
            ride = new TwoPassengersDiscount(ride);
        if (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == 6) // Friday (holiday)
            ride = new HolidayDiscount(ride);
        if (Calendar.getInstance().get(Calendar.MONTH) == ride.getItsPassenger().getPersonalInfo().getMonthOfBirth() 
            && Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == ride.getItsPassenger().getPersonalInfo().getDayOfBirth())
            ride = new BirthdayDiscount(ride);
        //then add this ride to the system
        SystemData.getInstance().addRide(ride);
    }

    public boolean acceptOffer(IOffer offer){
        if (offer.getItsRide().getItsPassenger().takeBalance(offer.getItsRide().getCost(offer.getPrice())))
        {
            offer.setAccepted(true);
            offer.getItsRide().setPrice(offer.getPrice());
            offer.getItsDriver().addBalance(offer.getPrice());
            offer.getItsDriver().setCurrentRide(offer.getItsRide());
            offer.getItsRide().addEvent("user accepted the ride", "Passenger: " + offer.getItsRide().getItsPassenger().getPersonalInfo().getUsername());
            //offer.getItsDriver().reachUserLocation(offer.getItsRide());
            //offer.getItsDriver().reachUserDistination(offer.getItsRide());
            return true;
        }
        return false;
    }

    public void denyOffer(IOffer offer){
        offer.setAccepted(false);
    }
}
