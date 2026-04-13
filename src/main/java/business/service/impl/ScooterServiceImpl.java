package business.service.impl;

import business.model.Scooter;
import business.service.ScooterService;
import data.datasource.DAOFactory;
import java.util.List;
import java.util.Optional;

public class ScooterServiceImpl implements ScooterService {

    public int registerScooter(Scooter scooter) throws Exception {
        return DAOFactory.getScooterDAO().insert(scooter);
    }

    public boolean updateScooter(Scooter scooter) throws Exception {
        return DAOFactory.getScooterDAO().update(scooter);
    }

    public Optional<Scooter> getScooterById(int scooterId) throws Exception {
        return DAOFactory.getScooterDAO().findById(scooterId);
    }

    public List<Scooter> getAllScooters() throws Exception {
        return DAOFactory.getScooterDAO().findAll();
    }

    public List<Scooter> getScootersBySponsor(int sponsorUserId) throws Exception {
        return DAOFactory.getScooterDAO().findBySponsorId(sponsorUserId);
    }

    public void updateScooterStatus(int scooterId, String status) throws Exception {
        DAOFactory.getScooterDAO().findById(scooterId).ifPresent(s -> {
            s.setStatus(status);
            try {
                DAOFactory.getScooterDAO().update(s);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public List<Scooter> getScootersByStation(int stationId) throws Exception {
        return DAOFactory.getScooterDAO().findByStationId(stationId);
    }
}
