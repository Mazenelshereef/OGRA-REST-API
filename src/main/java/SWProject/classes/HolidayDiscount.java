package SWProject.classes;

public class HolidayDiscount extends RideDiscountDecorator {
    
    public HolidayDiscount(IRide ride){
        super(ride);
    }

    @Override
    public double getCost(double price) {
        return ride.getCost(price) - (0.05 * price);
    }
}
