package SWProject.classes;

public class HolidayDiscount extends RideDiscountDecorator {
    private static final double HOLIDAY_DISCOUNT = 0.05;
    
    public HolidayDiscount(IRideRequest rideRequest){
        super(rideRequest);
    }

    @Override
    public double getCost(double price) {
        return rideRequest.getCost(price) - (HOLIDAY_DISCOUNT * price);
    }
}
