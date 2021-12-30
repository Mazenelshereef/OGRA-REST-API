package SWProject.classes;

public class AreaDiscount extends RideDiscountDecorator {
    
    public AreaDiscount(IRide ride){
        super(ride);
    }

    @Override
    public double getCost(double price) {
        return ride.getCost(price) - (0.10 * price);
    }
}
