package business.adapter;

public class ExternalGPSDevice {
    public String fetchCoordinates() {
        return "45.3499,-75.7561";
    }

    public boolean moving() {
        return false;
    }

    public int stationCode() {
        return 1;
    }
}