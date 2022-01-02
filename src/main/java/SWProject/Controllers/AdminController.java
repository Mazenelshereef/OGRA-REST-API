package SWProject.Controllers;
//
import SWProject.Controllers.PassengerController.LoginInput;
import SWProject.Model.authentication.AdminAuthenticator;
import SWProject.Model.storage.SystemData;
import SWProject.Model.users.ISuspendableUser;
import SWProject.Model.users.admin.Admin;
import SWProject.Model.users.drivers.IRegistrationRequest;

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
    public boolean Login(@RequestBody LoginInput loginInput) throws Exception {
        admin = (Admin) AdminAuthenticator.getInstance().login(loginInput.username, loginInput.password);
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
}