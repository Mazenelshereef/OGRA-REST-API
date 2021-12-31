package SWProject.classes;

public abstract class RideDiscountDecorator extends RideRequest {
    protected IRideRequest rideRequest;

    public RideDiscountDecorator(IRideRequest rideRequest) {
        super(rideRequest.getSource(), rideRequest.getDestination(), rideRequest.getNoOfPassengers(), rideRequest.getItsPassenger());
        this.setPrice(rideRequest.getPrice());
        this.rideRequest = rideRequest;
    } 
}
