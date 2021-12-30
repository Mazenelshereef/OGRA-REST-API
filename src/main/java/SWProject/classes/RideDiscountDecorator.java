package SWProject.classes;

public abstract class RideDiscountDecorator extends Ride {
    protected IRide ride;

    public RideDiscountDecorator(IRide ride) {
        super(ride.getSource(), ride.getDestination(), ride.getNoOfPassengers(), ride.getItsPassenger());
        this.setPrice(ride.getPrice());
        this.ride = ride;
    } 
}
