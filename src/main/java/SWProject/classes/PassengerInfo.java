package SWProject.classes;

public class PassengerInfo extends UserInfo {

    public PassengerInfo(PassengerInfo obj){
        this.username = obj.username;
        this.password = obj.password;
        this.email = obj.email;
        this.mobileNumber = obj.mobileNumber;
        this.isSuspended = obj.isSuspended;
        this.dayOfBirth = obj.dayOfBirth;
        this.monthOfBirth = obj.monthOfBirth;
        this.yearOfBirth = obj.yearOfBirth;
    }

    public PassengerInfo(String username, String password, String email, String mobileNumber
                        , int dayOfBirth, int monthOfBirth, int yearOfBirth){
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.isSuspended = false;
        this.dayOfBirth = dayOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.yearOfBirth = yearOfBirth;
    }

    //to make email optional
    public PassengerInfo(String username, String password, String mobileNumber
                        , int dayOfBirth, int monthOfBirth, int yearOfBirth){
        this.username = username;
        this.password = password;
        this.email = "*not determined*";
        this.mobileNumber = mobileNumber;
        this.isSuspended = false;
        this.dayOfBirth = dayOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.yearOfBirth = yearOfBirth;
    }

    public PassengerInfo(){
        this.email = "*not determined*";
        this.isSuspended = false;
    }

    @Override
    public String toString() {
        return "Passenger info: [username=" + username + ", email=" + email 
            + ", mobile number=" + mobileNumber + ", isSuspended=" + isSuspended 
            + "DateOfBirth=" + dayOfBirth + "/" + monthOfBirth + '/' + yearOfBirth + "]";
    }
}
