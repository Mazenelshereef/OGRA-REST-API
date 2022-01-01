package SWProject.Model.users.admin;

import SWProject.Model.authentication.DriverAuthenticator;
import SWProject.Model.booking.IRideRequest;
import SWProject.Model.storage.SystemData;
import SWProject.Model.users.ISuspendableUser;
import SWProject.Model.users.drivers.IRegistrationRequest;

public class Admin implements IAdmin {
    String username, password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String listPendingRegistrations() {
        return SystemData.getInstance().displayAllRegistrations();
    }

    @Override
    public void verifyDriverRegistration(IRegistrationRequest request) {
        request.setAccepted(true);
        DriverAuthenticator.getInstance().recieveRequestResponce(request);
    }

    @Override
    public void denyDriverRegistration(IRegistrationRequest request) {
        request.setAccepted(false);
        DriverAuthenticator.getInstance().recieveRequestResponce(request);
    }

    @Override
    public void suspendUser(ISuspendableUser user) {
        user.setSuspended(true);
    }

    @Override
    public void unsuspendUser(ISuspendableUser user) {
        user.setSuspended(false);
    }

    @Override
    public void addDiscountToArea(String area) {
        SystemData.getInstance().addDiscountArea(area);
    }

    @Override
    public String showEventsOnRide(IRideRequest rideRequest) {
        return rideRequest.showEvents();
    }

    @Override
    public String listAllRideRequests() {
        return SystemData.getInstance().displayAllRideRequests();
    }
}
