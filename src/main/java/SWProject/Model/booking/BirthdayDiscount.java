package SWProject.Model.booking;

public class BirthdayDiscount extends RideDiscountDecorator {
    private static final double BIRTHDAY_DISCOUNT = 0.10;

    public BirthdayDiscount(IRideRequest rideRequest){
        super(rideRequest);
    }

    /*
    @Override
    public double getDiscount() {
        return 0.10 + ride.getDiscount();
    }*/
    
    @Override
    public double getCost(double price) {
        return rideRequest.getCost(price) - (BIRTHDAY_DISCOUNT * price);
    }
}
