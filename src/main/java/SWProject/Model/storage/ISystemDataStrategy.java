package SWProject.Model.storage;

import java.util.ArrayList;
import SWProject.Model.users.admin.*;
import SWProject.Model.users.passengers.*;
import SWProject.Model.users.drivers.*;
import SWProject.Model.booking.*;

public interface ISystemDataStrategy {
    public String displayAllRegistrations();
    public String displayAllRideRequests();
    public boolean addRegistrationRequest(IRegistrationRequest registrationRequest);
    public boolean addDriver(IDriver driver);
    public boolean addPassenger(IPassenger passenger);
    public boolean addOffer(IOffer offer);
    public boolean addRideRequest(IRideRequest rideRequest);
    public boolean addRide(IRide ride);
    public boolean addRating(IRating rating);
    public boolean addDiscountArea(String area);
    public IRegistrationRequest getRegistrationRequestById(int id);
    public IRegistrationRequest getRegistrationRequest(String username);
    public IRideRequest getRideRequest(int index);
    public IDriver getDriver(String username);
    public IPassenger getPassenger(String username);
    public IAdmin getAdmin(String username);
    public ArrayList<IDriver> getDriversWithFavouriteArea(String area);
    public ArrayList<IOffer> getOffersOfPassenger(IPassenger passenger);
    public ArrayList<IOffer> getOffersOfDriver(IDriver driver);
    public ArrayList<IRating> gerRatingsOfDriver(IDriver driver);
    public ArrayList<IRideRequest> getRidesOfDriverFavouriteAreas(IDriver driver);
    public IRideRequest getRideRequestByID(int id);
    public IOffer getOfferByID(int id);
    public boolean removeRegisrationRequest(IRegistrationRequest registrationRequest);
    public boolean removeDiscountArea(String area);
    public boolean containsRideOfPassenger(IPassenger passenger);
    public boolean containsDiscountArea(String destination);
}
