package SWProject.classes;

public class AreaDiscount extends RideDiscountDecorator {
    
    public AreaDiscount(IRideRequest rideRequest){
        super(rideRequest);
    }

    @Override
    public double getCost(double price) {
        return rideRequest.getCost(price) - (0.10 * price);
    }
}
