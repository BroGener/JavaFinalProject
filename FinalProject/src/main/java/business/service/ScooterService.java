package business.service;

import business.model.Scooter;
import java.util.List;
import java.util.Optional;

public interface ScooterService {
    int registerScooter(Scooter scooter) throws Exception;
    boolean updateScooter(Scooter scooter) throws Exception;
    Optional<Scooter> getScooterById(int scooterId) throws Exception;
    List<Scooter> getAllScooters() throws Exception;
    List<Scooter> getScootersBySponsor(int sponsorUserId) throws Exception;
}