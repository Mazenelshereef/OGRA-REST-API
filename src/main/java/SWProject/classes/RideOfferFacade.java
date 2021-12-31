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

    public void makeOffer(IOffer offer){
        offer.getItsRideRequest().addEvent("Captain added a price", "Driver: " 
                                    + offer.getItsDriver().getPersonalInfo().getUsername() 
                                    + ", Price: " + offer.getPrice());
        SystemData.getInstance().addOffer(offer);
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

    public void acceptOffer(IOffer offer) throws Exception{
        //in case the user accepted another offer
        if (offer.getItsRideRequest().getAcceptedOffer() != null)
            throw new Exception("Error: you have already accepted another offer for this ride!");
        //check if the ride is full or not and suitable for this passenger
        if (offer.getItsDriver().getCurrentRide() != null)
        {
            //in case the ride source and destination are distinct from this request
            if (!offer.getItsRideRequest().getSource().equals(offer.getItsDriver().getCurrentRide().getSource()) 
            || !offer.getItsRideRequest().getDestination().equals(offer.getItsDriver().getCurrentRide().getDestination()))
                throw new Exception("Error: this driver is currently handling another ride!");
            //in case the noOfPassengers requested is less than this ride noOfPassengers
            if (offer.getItsRideRequest().getNoOfPassengers() < offer.getItsDriver().getCurrentRide().getNoOfPassengers())
                throw new Exception("Error: this ride has more than " + offer.getItsRideRequest().getNoOfPassengers() + " passengers!");
            //in case the ride is full
            if (offer.getItsDriver().getCurrentRide().isFull())
                throw new Exception("Error: this ride is now full!");
        }
        // we reach here if the ride is not full
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
            offer.accept();
            offer.getItsRideRequest().setPrice(offer.getPrice());
            offer.getItsDriver().addBalance(offer.getPrice());
            offer.getItsRideRequest().addEvent("user accepted the ride", "Passenger: " + offer.getItsRideRequest().getItsPassenger().getPersonalInfo().getUsername());
            return;
        }
        //we reach here if the passenger doesn't have enough balance.
        throw new Exception("ERROR: You don't have enough balance!");
    }

    public void denyOffer(IOffer offer){
        offer.deny();;
    }
}
