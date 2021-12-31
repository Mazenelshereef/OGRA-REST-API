package SWProject.Controllers;


import SWProject.classes.Driver;
import SWProject.classes.DriverInfo;
import SWProject.classes.DriverAuthenticator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DirverController {
    Driver driver ;
    DriverInfo info ;

    @PostMapping("/driver/login")
    public boolean Register(@PathVariable String username , @PathVariable String Email , @PathVariable String password , 
        @PathVariable String mobileNumber , @PathVariable String licenseId ,@PathVariable String nationalId) throws Exception{

        info.setUsername(username);
        if (!Email.equals("0")){
            info.setEmail(Email);
        }
        info.setPassword(password);
        info.setMobileNumber(mobileNumber);
        info.setLicenseId(licenseId);
        info.setNationalId(nationalId);

        return DriverAuthenticator.getInstance().register(info) ;
    }



    

}
