package SWProject.Model.users.drivers;

import SWProject.Model.users.UserInfo;

public class RegistrationRequest implements IRegistrationRequest {
    private int id;
	private UserInfo userInfo;
    private boolean isAccepted;

    private static int count = 0;

    public RegistrationRequest(UserInfo info){
        id = count++;
        userInfo = new DriverInfo((DriverInfo)info);
        isAccepted = false;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String toString() {
        return"Registration Request: [ID=" + id + ",driver=" + userInfo.toString() + ", isAccepted=" + isAccepted + "]";
    }

    @Override
    public void setUserInfo(UserInfo info) {
        userInfo = info;
    }

    @Override
    public UserInfo getUserInfo(){
        return userInfo;
    }

    @Override
    public void setAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
        
    }

    @Override
    public boolean isAccepted() {
        return isAccepted;
    }

}

