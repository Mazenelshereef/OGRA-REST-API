package SWProject.classes;

public class BirthdayDiscount extends RideDiscountDecorator {

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
        return rideRequest.getCost(price) - (0.10 * price);
    }
}
