package SWProject.classes;

public abstract class UserInfo {

        protected String username, mobileNumber, email, password;
        protected int yearOfBirth, monthOfBirth, dayOfBirth;
        protected boolean isSuspended;

        public void setEmail(String email) {
            this.email = email;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setSuspended(boolean suspended) {
            this.isSuspended = suspended;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setDayOfBirth(int dayOfBirth) {
            this.dayOfBirth = dayOfBirth;
        }

        public void setMonthOfBirth(int monthOfBirth) {
            this.monthOfBirth = monthOfBirth;
        }

        public void setYearOfBirth(int yearOfBirth) {
            this.yearOfBirth = yearOfBirth;
        }

        public String getEmail() {
            return email;
        }

        public String getMobileNumber() {
            return mobileNumber;
        }

        public String getPassword() {
            return password;
        }

        public String getUsername() {
            return username;
        }

        public boolean getSuspended() {
            return isSuspended;
        }

        public int getDayOfBirth() {
            return dayOfBirth;
        }

        public int getMonthOfBirth() {
            return monthOfBirth;
        }

        public int getYearOfBirth() {
            return yearOfBirth;
        }
    }

