package data.dao;

import business.model.Scooter;
import java.util.List;
import java.util.Optional;

public interface ScooterDAO extends GenericDAO<Scooter, Integer> {
    Optional<Scooter> findByVehicleNumber(String vehicleNumber) throws Exception;
    List<Scooter> findBySponsorId(int sponsorUserId) throws Exception;
    List<Scooter> findByStationId(int stationId) throws Exception;
}