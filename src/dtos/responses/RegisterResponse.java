package dtos.responses;

public class RegisterResponse {

    private String id;
    private String fullName;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return """
                ^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                Id: %s
                Full Name: %s
                Email: %s
                """.formatted(getId(), getFullName(), getEmail());
    }



}
