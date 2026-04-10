package business.factory;

import business.model.Maintainer;
import business.model.Sponsor;
import business.model.User;

public final class UserFactory {

    private UserFactory() {
    }

    public static User createUser(String role, String name, String email, String password) {
        return switch (role.toUpperCase()) {
            case "SPONSOR" -> new Sponsor(null, name, email, password);
            case "MAINTAINER" -> new Maintainer(null, name, email, password);
            default -> new User(null, name, email, password, "USER");
        };
    }
}