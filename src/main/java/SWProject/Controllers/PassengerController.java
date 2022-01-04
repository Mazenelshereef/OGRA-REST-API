package SWProject.Controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import SWProject.Model.authentication.PassengerAuthenticator;
import SWProject.Model.booking.IOffer;
import SWProject.Model.storage.SystemData;
import SWProject.Model.users.passengers.Passenger;
import SWProject.Model.users.passengers.PassengerInfo;

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

    @PutMapping("/passenger/login")
    public boolean login(@RequestParam String username, @RequestParam String password) throws Exception{
        passenger = (Passenger)PassengerAuthenticator.getInstance().login(username, password);
        return true;
    }

    @PutMapping("/passenger/logout")
    public boolean logout() throws Exception {
        if (passenger == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        passenger = null;
        return true;
    }

    @PutMapping("/passenger/addBalance/{amount}")
    public boolean addBalance(@PathVariable double amount) throws Exception{
        if (passenger == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        passenger.addBalance(amount);
        return true;
    } 

    @GetMapping("/passenger/checkBalance")
    public double checkBalance() throws Exception{
        if (passenger == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        return passenger.getBalance();
    }
    
    @PostMapping("/passenger/requestRide/{source}/{destination}/{noOfPassengers}")
    public boolean requestRide(@PathVariable String source, @PathVariable String destination, @PathVariable int noOfPassengers) throws Exception{
        if (passenger == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        passenger.requestRide(source, destination, noOfPassengers);
        return true;
    }

    @GetMapping("/passenger/checkOffers")
    public String checkOffers() throws Exception{
        if (passenger == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        return passenger.checkOffers();
    }

    @GetMapping("/passenger/checkDriverRating/{offerID}")  
    public double checkDriverRating(@PathVariable int offerID) throws Exception{
        if (passenger == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        IOffer offer = SystemData.getInstance().getOfferByID(offerID);
        if (offer == null)
            throw new Exception("ERROR: there is no offer with this ID!");
        return passenger.checkDriverRating(offer.getItsDriver());
    }

    @PutMapping("/passenger/acceptOffer/{offerID}")  
    public boolean acceptOffer(@PathVariable int offerID) throws Exception{
        if (passenger == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        IOffer offer = SystemData.getInstance().getOfferByID(offerID);
        if (offer == null)
            throw new Exception("ERROR: there is no offer with this ID!");
        passenger.acceptOffer(offer);
        return true;
    }

    @PutMapping("/passenger/denyOffer/{offerID}")  
    public boolean denyOffer(@PathVariable int offerID) throws Exception{
        if (passenger == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        IOffer offer = SystemData.getInstance().getOfferByID(offerID);
        if (offer == null)
            throw new Exception("ERROR: there is no offer with this ID!");
        passenger.denyOffer(offer);
        return true;
    }

    @PostMapping("/passenger/rateDriver/{offerID}/{ratingValue}")  
    public boolean rateDriver(@PathVariable int offerID, @PathVariable int ratingValue) throws Exception{
        if (passenger == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        IOffer offer = SystemData.getInstance().getOfferByID(offerID);
        if (offer == null)
            throw new Exception("ERROR: there is no offer with this ID!");
        passenger.rateDriver(offer.getItsDriver(), ratingValue);
        return true;
    }

    @GetMapping("/passenger/viewNotifications")
    public String viewNotifications() throws Exception{
        if (passenger == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        return passenger.viewNotifications();
    }

    @DeleteMapping("/passenger/removeNotification/{notificationNumber}")
    public boolean removeNotification(@PathVariable int notificationNumber) throws Exception{
        if (passenger == null)
            throw new Exception("ERROR: you should login first before using this feature!");
        return passenger.removeNotification(notificationNumber);
    }
    
}
