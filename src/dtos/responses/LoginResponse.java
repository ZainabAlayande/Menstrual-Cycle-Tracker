package dtos.responses;

public class LoginResponse {

    private String fullName;
    private String emailAddress;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "^^^^^^^^^^^^^^^^^^" +
                "Full Name::: " + fullName + '\'' +
                "Email Address::: " + emailAddress + '\'' +
                '}';
    }
}
