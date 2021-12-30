package SWProject.classes;

public interface ILoginAuthenticator {
    public IUser login(String username, String password) throws Exception;
    //public IUser search(String username) throws Exception;
}
