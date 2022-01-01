package SWProject.Controllers;

import SWProject.classes.Passenger;
import SWProject.classes.IDriver;
import SWProject.classes.PassengerAuthenticator;
import SWProject.classes.PassengerInfo;
import SWProject.classes.SystemData;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class PassengerController {
    Passenger passenger;

    static class RegisterInput{
        public String username, password, mail, mobile;
        public int dayOfBirth, monthOfBirth, yearOfBirth;
    }
    
    @PostMapping("/passenger/register")
    public boolean Register(@RequestBody RegisterInput registerInput) throws Exception{
        if (!registerInput.mail.equals("0"))
            return PassengerAuthenticator.getInstance().register(new PassengerInfo(registerInput.username, 
                                            registerInput.password, registerInput.mail, registerInput.mobile, 
                                            registerInput.dayOfBirth, registerInput.monthOfBirth, 
                                            registerInput.yearOfBirth));
        return PassengerAuthenticator.getInstance().register(new PassengerInfo(registerInput.username, 
                                            registerInput.password, registerInput.mobile, registerInput.dayOfBirth, 
                                            registerInput.monthOfBirth, registerInput.yearOfBirth));
    }

    static class LoginInput{
        public String username, password;
    }

    @PutMapping("/passenger/login")
    public void login(@RequestBody LoginInput loginInput) throws Exception{
        passenger = (Passenger)PassengerAuthenticator.getInstance().login(loginInput.username, loginInput.password);
    }

    @PutMapping("/passenger/logout")
    public boolean logout() {
        if (passenger == null)
            return false;
        passenger = null;
        return true;
    }

    @PutMapping("/passenger/addBalance/{amount}")
    public void addBalance(@PathVariable double amount){
        passenger.addBalance(amount);
    } 
    
    @PostMapping("/passenger/requestRide/{s}/{d}/{noOfPassengers}")
    public void requestRide(@PathVariable String source, @PathVariable String destination, @PathVariable int noOfPassengers){
        passenger.requestRide(source, destination, noOfPassengers);
    }

    @GetMapping("/passenger/checkOffers")
    public String checkOffers(){
        return passenger.checkOffers();
    }

    @GetMapping("/passenger/checkDriverRating/{offerID}")  
    public double checkDriverRating(@PathVariable int offerID){
    return passenger.checkDriverRating(SystemData.getInstance().getOfferByID(offerID).getItsDriver());

    }

    @PutMapping("/passenger/acceptOffer/{offerID}")  
    public void acceptOffer(@PathVariable int offerID) throws Exception{
        passenger.acceptOffer(SystemData.getInstance().getOfferByID(offerID));

    }

    @PutMapping("/passenger/denyOffer/{offerID}")  
    public void denyOffer(@PathVariable int offerID){
        passenger.denyOffer(SystemData.getInstance().getOfferByID(offerID));
    }

    @PutMapping("/passenger/rateDriver/{offerID}/{ratingValue}")  
    public void rateDriver(@PathVariable int offerID, @PathVariable int ratingValue) throws Exception{
        IDriver driverOfOffer = SystemData.getInstance().getOfferByID(offerID).getItsDriver();
        passenger.rateDriver(driverOfOffer, ratingValue);

    }

    @GetMapping("/passenger/viewNotifications")
    public String viewNotifications(){
        return passenger.viewNotifications();
    }

    @DeleteMapping("/passenger/removeNotification/{notificationNumber}")
    public boolean removeNotification(@PathVariable int notificationNumber){
        return passenger.removeNotification(notificationNumber);
    }
    
}
