package SWProject.Controllers;

import SWProject.classes.ISuspendableUser;
import SWProject.classes.SystemData;
import SWProject.Controllers.PassengerController.LoginInput;
import SWProject.classes.Admin;
import SWProject.classes.AdminAuthenticator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    Admin admin;

    @PutMapping("/admin/login")
    public void Login(@RequestBody LoginInput loginInput) throws Exception {
        admin = (Admin) AdminAuthenticator.getInstance().login(loginInput.username, loginInput.password);
    }

    @GetMapping("/admin/listPendingRegistrations")
    public String listPendingRegistrations() {
        return admin.listPendingRegistrations();
    }

    @PutMapping("/admin/verifyDriverRegistration/{requestID}")
    public void verifyDriverRegistration(@PathVariable int requestID) {
        admin.verifyDriverRegistration(SystemData.getInstance().getRegistrationRequestById(requestID));

    }

    @PutMapping("/admin/denyDriverRegistration/{requestID}")
    public void denyDriverRegistration(@PathVariable int requestID) {
        admin.denyDriverRegistration(SystemData.getInstance().getRegistrationRequestById(requestID));

    }

    @PutMapping("/admin/suspendUser/{username}")
    public void suspendUser(@PathVariable String username) throws Exception {
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
    }

    @PutMapping("/admin/unsuspendUser/{username}")
    public void unsuspendUser(@PathVariable String username) throws Exception {
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
    }

    @PostMapping("/admin/addDiscountToArea/{area}")
    public void addDiscountToArea(@PathVariable String area) {
        admin.addDiscountToArea(area);
    }

    @GetMapping("/admin/listAllRideRequests")
    public String listAllRideRequests(){
        return admin.listAllRideRequests();
    }

    @GetMapping("/admin/showEventsOnRide/{rideID}")
    public String showEventsOnRide(@PathVariable int rideID) {
        return admin.showEventsOnRide(SystemData.getInstance().getRideRequestByID(rideID));
    }

}
