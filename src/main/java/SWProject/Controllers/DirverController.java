package SWProject.Controllers;

import SWProject.Controllers.PassengerController.LoginInput;
import SWProject.Model.authentication.DriverAuthenticator;
import SWProject.Model.booking.IRideRequest;
import SWProject.Model.storage.SystemData;
import SWProject.Model.users.drivers.Driver;
import SWProject.Model.users.drivers.DriverInfo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DirverController {
    Driver driver;

    static class RegisterInput{
        public String username, password, mail, mobile, licenseId, nationalId;
        public int dayOfBirth, monthOfBirth, yearOfBirth;
    }

    @PostMapping("/driver/register")
    public boolean Register(@RequestBody RegisterInput r) throws Exception {
        if (!r.mail.equals("0"))
            return DriverAuthenticator.getInstance().register(new DriverInfo(r.username, r.password, r.mail, 
                        r.mobile, r.dayOfBirth, r.monthOfBirth, r.yearOfBirth, r.licenseId, r.nationalId));
        return DriverAuthenticator.getInstance().register(new DriverInfo(r.username, r.password, r.mobile, 
                        r.dayOfBirth, r.monthOfBirth, r.yearOfBirth, r.licenseId, r.nationalId));
    }

    

    @PutMapping("/driver/login")
    public boolean login(@RequestBody LoginInput loginInput) throws Exception {
        driver = (Driver) DriverAuthenticator.getInstance().login(loginInput.username, loginInput.password);
        return true;
    }

    @PutMapping("/driver/logout")
    public boolean logout() throws Exception {
        if (driver == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        driver = null;
        return true;
    }

    @PostMapping("/driver/addFavoriteArea/{area}")
    public boolean addFavoriteArea(@PathVariable String area) throws Exception {
        if (driver == null)
        throw new Exception("ERROR: you should login first before using this feature!");
        driver.addFavoriteArea(area);
        return true;
    }

    @GetMapping("/driver/listRides")
    public String listRidesInFavouriteAreas() throws Exception{
        if (driver == null)
        throw new Exception("ERROR: you should login first before using this feature!");
        return driver.listRidesInFavouriteAreas();
    }

    @PostMapping("/driver/suggestPrice/{id}/{price}")
    public boolean suggestPrice(@PathVariable int rideID, @PathVariable double price) throws Exception {
        if (driver == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        IRideRequest request = SystemData.getInstance().getRideRequestByID(rideID);
        if (request == null)
            throw new Exception("ERROR: there is no request with this ID!");
        driver.suggestPrice(request, price);
        return true;
    }

    @GetMapping("/driver/ratings")
    public String listPassengerRatings() throws Exception {
        if (driver == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        return driver.listPassengersRatings();
    }

    @GetMapping("driver/offers")
    public String viewMyOffers() throws Exception {
        if (driver == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        return driver.viewMyOffers();
    }

    @DeleteMapping("/driver/removeNotification/{notificationNumber}")
    public boolean removeNotification(@PathVariable int notificationNumber) throws Exception{
        if (driver == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        return driver.removeNotification(notificationNumber);
    }

    @GetMapping("/driver/viewNotifications")
    public String viewNotifications() throws Exception{
        if (driver == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        return driver.viewNotifications();
    }

    @PutMapping("/driver/reachUserLocation")
    public boolean reachUserLocation() throws Exception {
        if (driver == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        driver.reachUserLocation();
        return true;
    }

    @PutMapping("/driver/reachUserDestination")
    public boolean reachUserDestination() throws Exception {
        if (driver == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        driver.reachUserDestination();
        return true;
    }
}
