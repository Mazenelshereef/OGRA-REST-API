package SWProject.classes;

public interface IAdmin extends IUser{
    public void setUsername(String username);
    public String getUsername();
    public void setPassword(String password);
    public String getPassword();
    public void verifyDriverRegistration(IRegistrationRequest request);
    public void denyDriverRegistration(IRegistrationRequest request);
    public void suspendUser(ISuspendableUser user);
    public void unsuspendUser(ISuspendableUser user);
    public String listPendingRegistrations();
    public void addDiscountToArea(String area);
}
