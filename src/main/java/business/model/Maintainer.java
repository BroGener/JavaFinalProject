package business.model;

public class Maintainer extends User {
    public Maintainer() {
        super();
        setRole("MAINTAINER");
    }

    public Maintainer(Integer userId, String name, String email, String password) {
        super(userId, name, email, password, "MAINTAINER");
    }
}