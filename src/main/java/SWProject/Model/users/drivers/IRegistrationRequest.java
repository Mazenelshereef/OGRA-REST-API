package SWProject.Model.users.drivers;

import SWProject.Model.users.UserInfo;

public interface IRegistrationRequest {
    public int getID();
    public void setUserInfo(UserInfo info);
    public UserInfo getUserInfo();
    public void setAccepted(boolean isAccepted);
    public boolean isAccepted();
    @Override
    String toString();
}
