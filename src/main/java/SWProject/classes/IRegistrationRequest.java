package SWProject.classes;

public interface IRegistrationRequest {
    public int getID();
    public void setUserInfo(UserInfo info);
    public UserInfo getUserInfo();
    public void setAccepted(boolean isAccepted);
    public boolean isAccepted();
    @Override
    String toString();
}
