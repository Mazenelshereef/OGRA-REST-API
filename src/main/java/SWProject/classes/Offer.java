package SWProject.classes;

public class Offer implements IOffer {
    private IRideRequest itsRideRequest;
    private IDriver itsDriver;
    private double price;
    private boolean isAccepted, isDenied;

    public Offer(double price, IDriver itsDriver, IRideRequest itsRideRequest){
        this.price = price;
        this.itsDriver = itsDriver;
        this.itsRideRequest = itsRideRequest;
        isAccepted = false;
        isDenied = false;
    }

    @Override
    public IDriver getItsDriver() {
        return itsDriver;
    }

    @Override
    public void setItsDriver(IDriver itsDriver) {
        this.itsDriver = itsDriver;
    }

    @Override
    public IRideRequest getItsRideRequest() {
        return itsRideRequest;
    }

    @Override
    public void setItsRideRequest(IRideRequest rideRequest) {
        itsRideRequest = rideRequest;      
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean isAccepted() {
        return isAccepted;
    }

    @Override
    public void accept() {
        this.isAccepted = true;
    }

    @Override
    public boolean isDenied() {
        return isDenied;
    }

    @Override
    public void deny() {
        isDenied = false;        
    }

    @Override
    public String toString() {
        return "Offer [ride= from\"" + itsRideRequest.getSource() + "\" to \"" + itsRideRequest.getDestination() + "\", itsDriver=" + itsDriver.getPersonalInfo().getUsername() + ", price="
                + price + ", cost=" + itsRideRequest.getCost(price) + ", isAccepted=" + isAccepted + "]";
    }
}
