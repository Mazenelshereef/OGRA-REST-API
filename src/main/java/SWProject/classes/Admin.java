package SWProject.classes;

public class Admin implements IAdmin {
        String username, password;

        Admin(String username , String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public  String getUsername() {
            return username;
        }

        @Override
        public String getPassword() {
            return password;
        }
        
        //returns false if there are no requests, true otherwise.
        @Override
        public String listPendingRegistrations(){
            return SystemData.getInstance().displayAllRegistrations();
        }

        @Override
        public void verifyDriverRegistration(IRegistrationRequest request){
            request.setAccepted(true);
            DriverAuthenticator.getInstance().recieveRequestResponce(request);
        }

        @Override
        public void denyDriverRegistration(IRegistrationRequest request){
            request.setAccepted(false);
            DriverAuthenticator.getInstance().recieveRequestResponce(request);        }

        @Override
        public void suspendUser(ISuspendableUser user){
            user.setSuspended(true);
        }
        
        @Override
        public void unsuspendUser(ISuspendableUser user){
            user.setSuspended(false);
        }

        @Override
        public void addDiscountToArea(String area) {
            SystemData.getInstance().addDiscountArea(area);
        }
}
