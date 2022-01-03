package SWProject.Controllers;

import SWProject.Model.authentication.AdminAuthenticator;
import SWProject.Model.storage.SystemData;
import SWProject.Model.users.ISuspendableUser;
import SWProject.Model.users.admin.Admin;
import SWProject.Model.users.drivers.IRegistrationRequest;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    Admin admin;

    @PutMapping("/admin/login")
    public boolean login(@RequestParam String username,@RequestParam String password) throws Exception {
        admin = (Admin) AdminAuthenticator.getInstance().login(username, password);
        return true;
    }

    @PutMapping("/admin/logout")
    public boolean logout() throws Exception {
        if (admin == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        admin = null;
        return true;
    }

    @GetMapping("/admin/listPendingRegistrations")
    public String listPendingRegistrations() throws Exception {
        if (admin == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        return admin.listPendingRegistrations();
    }

    @PutMapping("/admin/verifyRequest/{requestID}")
    public boolean verifyDriverRegistration(@PathVariable int requestID) throws Exception {
        if (admin == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        IRegistrationRequest request = SystemData.getInstance().getRegistrationRequestById(requestID);
        if (request == null)
            throw new Exception("ERROR: there is no request with this ID!");
        admin.verifyDriverRegistration(request);
        return true;

    }

    @PutMapping("/admin/denyRequest/{requestID}")
    public boolean denyDriverRegistration(@PathVariable int requestID) throws Exception {
        if (admin == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        IRegistrationRequest request = SystemData.getInstance().getRegistrationRequestById(requestID);
        if (request == null)
            throw new Exception("ERROR: there is no request with this ID!");
        admin.denyDriverRegistration(request);
        return true;
    }

    @PutMapping("/admin/suspend/{username}")
    public boolean suspendUser(@PathVariable String username) throws Exception {
        if (admin == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        ISuspendableUser userToSuspend;
        if (SystemData.getInstance().getDriver(username) != null) {
            userToSuspend = SystemData.getInstance().getDriver(username);
        } else if (SystemData.getInstance().getPassenger(username) != null) {
            userToSuspend = SystemData.getInstance().getPassenger(username);
        }
        else {
            throw new Exception("ERROR: there is no user with this username!");
        }
        admin.suspendUser(userToSuspend);
        return true;
    }

    @PutMapping("/admin/unsuspend/{username}")
    public boolean unsuspendUser(@PathVariable String username) throws Exception {
        if (admin == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        ISuspendableUser userToUnsuspend;
        if (SystemData.getInstance().getDriver(username) != null) {
            userToUnsuspend = SystemData.getInstance().getDriver(username);
        } else if (SystemData.getInstance().getPassenger(username) != null) {
            userToUnsuspend = SystemData.getInstance().getPassenger(username);
        }
        else {
            throw new Exception("ERROR: there is no user with this username!");
        }
        admin.unsuspendUser(userToUnsuspend);
        return true;
    }

    @PostMapping("/admin/addDiscountToArea/{area}")
    public boolean addDiscountToArea(@PathVariable String area) throws Exception {
        if (admin == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        admin.addDiscountToArea(area);
        return true;
    }

    @GetMapping("/admin/listAllRideRequests")
    public String listAllRideRequests() throws Exception{
        if (admin == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        return admin.listAllRideRequests();
    }

    @GetMapping("/admin/showEventsOnRide/{rideID}")
    public String showEventsOnRide(@PathVariable int rideID) throws Exception {
        if (admin == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        return admin.showEventsOnRide(SystemData.getInstance().getRideRequestByID(rideID));
    }

}
