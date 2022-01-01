package SWProject.Model.booking;

public class TwoPassengersDiscount extends RideDiscountDecorator {
    private static final double TWO_PASSENGER_DISCOUNT = 0.05;
    
    public TwoPassengersDiscount(IRideRequest rideRequest){
        super(rideRequest);
    }

    @Override
    public double getCost(double price) {
        return rideRequest.getCost(price) - (TWO_PASSENGER_DISCOUNT * price);
    }
}
