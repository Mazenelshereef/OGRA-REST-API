package SWProject.Controllers;

import SWProject.Controllers.PassengerController.LoginInput;
import SWProject.classes.Driver;
import SWProject.classes.DriverInfo;
import SWProject.classes.SystemData;
import SWProject.classes.DriverAuthenticator;

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
    public void login(@RequestBody LoginInput loginInput) throws Exception {
        driver = (Driver) DriverAuthenticator.getInstance().login(loginInput.username, loginInput.password);
    }

    @PutMapping("/driver/logout")
    public boolean logout() {
        if (driver == null)
            return false;
        driver = null;
        return true;
    }

    @PostMapping("/driver/addFavoriteArea/{area}")
    public void addFavoriteArea(@PathVariable String area) {
        driver.addFavoriteArea(area);
    }

    @GetMapping("/driver/listRides")
    public String listRidesInFavouriteAreas(){
        return driver.listRidesInFavouriteAreas();
    }

    @PostMapping("/driver/suggestPrice/{id}/{price}")
    public void suggestPrice(@PathVariable int rideID, @PathVariable double price) {
        driver.suggestPrice(SystemData.getInstance().getRideRequestByID(rideID), price);
    }

    @GetMapping("/driver/ratings")
    public String listPassengerRatings() {
        return driver.listPassengersRatings();
    }

    @PostMapping("driver/offers")
    public String viewMyOffers() {
        return driver.viewMyOffers();
    }

    @DeleteMapping("/driver/removeNotification/{notificationNumber}")
    public boolean removeNotification(@PathVariable int notificationNumber){
        return driver.removeNotification(notificationNumber);
    }

    @GetMapping("/driver/viewNotifications")
    public String viewNotifications(){
        return driver.viewNotifications();
    }

    @PutMapping("/driver/reachUserLocation")
    public void reachUserLocation() throws Exception {
        driver.reachUserLocation();
    }

    @PutMapping("/driver/reachUserDistination")
    public void reachUserDistination() throws Exception {
        driver.reachUserDistination();
    }
}
