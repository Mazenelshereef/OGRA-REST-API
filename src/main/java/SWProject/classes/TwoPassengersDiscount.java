package SWProject.classes;

public class TwoPassengersDiscount extends RideDiscountDecorator {
    
    public TwoPassengersDiscount(IRideRequest rideRequest){
        super(rideRequest);
    }

    @Override
    public double getCost(double price) {
        return rideRequest.getCost(price) - (0.05 * price);
    }
}
