package SWProject.classes;

public class BirthdayDiscount extends RideDiscountDecorator {

    public BirthdayDiscount(IRide ride){
        super(ride);
    }

    /*
    @Override
    public double getDiscount() {
        return 0.10 + ride.getDiscount();
    }*/
    
    @Override
    public double getCost(double price) {
        return ride.getCost(price) - (0.10 * price);
    }
}
