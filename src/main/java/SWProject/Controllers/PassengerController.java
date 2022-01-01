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

class RegisterInput{
    public String username, password, mail, mobile;
    public int dayOfBirth, monthOfBirth, yearOfBirth;
}

class LoginInput{
    public String username, password;
}

@RestController
public class PassengerController {
    Passenger passenger;
    
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

    @PutMapping("/passenger/Login")
    public void Login(@RequestBody LoginInput loginInput) throws Exception{
        passenger = (Passenger)PassengerAuthenticator.getInstance().login(loginInput.username, loginInput.password);
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

    @GetMapping("/passenger/checkDriverRating/{offerNumber}")  
    public double checkDriverRating(@PathVariable int offerNumber){
    return passenger.checkDriverRating(SystemData.getInstance().getOffersOfPassenger(passenger).get(offerNumber-1).getItsDriver());

    }

    @PutMapping("/passenger/acceptOffer/{offerNumber}")  
    public void acceptOffer(@PathVariable int offerNumber) throws Exception{
        passenger.acceptOffer(SystemData.getInstance().getOffersOfPassenger(passenger).get(offerNumber-1));

    }

    @PutMapping("/passenger/denyOffer/{offerNumber}")  
    public void denyOffer(@PathVariable int offerNumber){
        passenger.denyOffer(SystemData.getInstance().getOffersOfPassenger(passenger).get(offerNumber-1));

    }

    @PutMapping("/passenger/rateDriver/{offerNumber}/{ratingValue}")  
    public void rateDriver(@PathVariable int offerNumber, @PathVariable int ratingValue) throws Exception{
        IDriver driverOfOffer = SystemData.getInstance().getOffersOfPassenger(passenger).get(offerNumber-1).getItsDriver();
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
