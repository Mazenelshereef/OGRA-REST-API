package SWProject.classes;

public class HolidayDiscount extends RideDiscountDecorator {
    
    public HolidayDiscount(IRideRequest rideRequest){
        super(rideRequest);
    }

    @Override
    public double getCost(double price) {
        return rideRequest.getCost(price) - (0.05 * price);
    }
}
