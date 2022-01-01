package SWProject.Model.booking;

public class FirstRideDiscount extends RideDiscountDecorator {
    private static final double FIRST_RIDE_DISCOUNT = 0.10;

    public FirstRideDiscount(IRideRequest rideRequest){
        super(rideRequest);
    }

    /*
    @Override
    public double getDiscount() {
        return 0.10 + ride.getDiscount();
    }*/

    @Override
    public double getCost(double price) {
        return rideRequest.getCost(price) - (FIRST_RIDE_DISCOUNT * price);
    }
    
}
