package SWProject.Controllers;
import SWProject.classes.IAdmin;
import SWProject.classes.IDriver;
import SWProject.classes.IPassenger;
import SWProject.classes.IRegistrationRequest;
import SWProject.classes.ISuspendableUser;
import SWProject.classes.SystemData;
import SWProject.classes.Admin;
import SWProject.classes.AdminAuthenticator;
import SWProject.classes.DriverAuthenticator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class AdminController {

    Admin admin; 

        @GetMapping("/admin/Login")
        public void Login(@RequestAttribute String username, @RequestAttribute String password) throws Exception{
            admin= (Admin)AdminAuthenticator.getInstance().login(username, password);
        }

        @GetMapping("/admin/listPendingRegistrations")
        public String listPendingRegistrations(){
            return admin.listPendingRegistrations();
        }

        @PutMapping("/admin/verifyDriverRegistration/{requestNumber}")
        public void verifyDriverRegistration(@PathVariable int requestNumber){
            IRegistrationRequest request= SystemData.getInstance().getRegistrationRequest(requestNumber-1);
            admin.verifyDriverRegistration(request);
           
        }

        @PutMapping("/admin/denyDriverRegistration/{requestNumber}")
        public void denyDriverRegistration(@PathVariable int requestNumber){
            IRegistrationRequest request= SystemData.getInstance().getRegistrationRequest(requestNumber-1);
            admin.denyDriverRegistration(request);
                  
        }

        @PutMapping("/admin/suspendUser/{user}")
        public void suspendUser(@PathVariable String user){
            if(SystemData.getInstance().getDriver(user)!=null){
            IDriver driverToSuspend = SystemData.getInstance().getDriver(user);
            admin.suspendUser(driverToSuspend);
            }
            else if(SystemData.getInstance().getPassenger(user)!=null){
            IPassenger passengerToSuspend = SystemData.getInstance().getPassenger(user);
            admin.suspendUser(passengerToSuspend);
            }
        }
        
        @PutMapping("/admin/suspendUser/{user}")
        public void unsuspendUser(@PathVariable String user){
            if(SystemData.getInstance().getDriver(user)!=null){
                IDriver driverToSuspend = SystemData.getInstance().getDriver(user);
                admin.unsuspendUser(driverToSuspend);
                }
                else if(SystemData.getInstance().getPassenger(user)!=null){
                IPassenger passengerToSuspend = SystemData.getInstance().getPassenger(user);
                admin.unsuspendUser(passengerToSuspend);
                }
        }

        @PostMapping("/admin/addDiscountToArea/{area}")
        public void addDiscountToArea(@PathVariable String area) {
            admin.addDiscountToArea(area);
        }
    
}
