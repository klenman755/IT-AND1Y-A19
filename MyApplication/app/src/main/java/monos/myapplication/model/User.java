package monos.myapplication.model;

public class User {
    private String name;
    private String quickPassword;
    public User(String name, String quickPassword) {
        this.name = name;
        this.quickPassword = quickPassword;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getQuickPassword() {
        return quickPassword;
    }
    public void setQuickPassword(String quickPassword) {
        this.quickPassword = quickPassword;
    }



}