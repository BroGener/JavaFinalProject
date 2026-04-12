package business.factory;

import business.model.Maintainer;
import business.model.Sponsor;
import business.model.User;

public final class UserFactory {

    private UserFactory() {
    }

public static User createUser(String role, String name, String email, String password) {
    switch (role.toUpperCase()) {
        case "SPONSOR":
            return new Sponsor(null, name, email, password);
        case "MAINTAINER":
            return new Maintainer(null, name, email, password);
        default:
            return new User(null, name, email, password, "USER");
    }
}
}