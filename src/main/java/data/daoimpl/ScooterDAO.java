package data.daoimpl;

import business.model.Scooter;
import data.datasource.MockDataStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScooterDAO implements data.dao.ScooterDAO {
    public Integer insert(Scooter entity) {
        entity.setScooterId(MockDataStore.nextScooterId());
        MockDataStore.SCOOTERS.add(entity);
        return entity.getScooterId();
    }
    public boolean update(Scooter entity) { return true; }
    public boolean deleteById(Integer id) { return false; }
    public Optional<Scooter> findById(Integer id) {
        for (Scooter scooter : MockDataStore.SCOOTERS) {
            if (scooter.getScooterId().equals(id)) return Optional.of(scooter);
        }
        return Optional.empty();
    }
    public List<Scooter> findAll() { return new ArrayList<Scooter>(MockDataStore.SCOOTERS); }
    public Optional<Scooter> findByVehicleNumber(String vehicleNumber) {
        for (Scooter scooter : MockDataStore.SCOOTERS) {
            if (scooter.getVehicleNumber().equalsIgnoreCase(vehicleNumber)) return Optional.of(scooter);
        }
        return Optional.empty();
    }
    public List<Scooter> findBySponsorId(int sponsorUserId) {
        List<Scooter> list = new ArrayList<Scooter>();
        for (Scooter scooter : MockDataStore.SCOOTERS) {
            if (scooter.getSponsorUserId() != null && scooter.getSponsorUserId() == sponsorUserId) list.add(scooter);
        }
        return list;
    }
    public List<Scooter> findByStationId(int stationId) {
        List<Scooter> list = new ArrayList<Scooter>();
        for (Scooter scooter : MockDataStore.SCOOTERS) {
            if (scooter.getCurrentStationId() != null && scooter.getCurrentStationId() == stationId) list.add(scooter);
        }
        return list;
    }
}
