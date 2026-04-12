package business.model;

public class Sponsor extends User {
    public Sponsor() {
        super();
        setRole("SPONSOR");
    }

    public Sponsor(Integer userId, String name, String email, String password) {
        super(userId, name, email, password, "SPONSOR");
    }
}