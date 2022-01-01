package SWProject.Model.booking;

public class AreaDiscount extends RideDiscountDecorator {
    private static final double AREA_DISCOUNT = 0.10;
    
    public AreaDiscount(IRideRequest rideRequest){
        super(rideRequest);
    }

    @Override
    public double getCost(double price) {
        return rideRequest.getCost(price) - (AREA_DISCOUNT * price);
    }
}
