package error404.gfg.healthcare.model;

public class UserModel {
    public UserModel(String _id, String email, String password, String address, String bloodGroup, String firstName, String gender, String lastName, String number, String birthDate, String access_token) {
        this._id = _id;
        Email = email;
        Password = password;
        Address = address;
        BloodGroup = bloodGroup;
        FirstName = firstName;
        Gender = gender;
        LastName = lastName;
        Number = number;
        BirthDate = birthDate;
        this.access_token = access_token;
    }

    private String _id;

    public UserModel() {
    }

    private String Email;
    private String Password;
    private String Address;
    private String BloodGroup;
    private String FirstName;
    private String Gender;
    private String LastName;
    private String Number;
    private String BirthDate;
    private String access_token;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
