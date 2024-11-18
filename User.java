public class User {
    private String userId;
    private String name;
    private String email;
    private String mobile;

    public User(String userId, String name, String email, String mobile){
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    public String getUserId(){
        return userId;
    }

    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getMobile() {
        return mobile;
    }    

}
