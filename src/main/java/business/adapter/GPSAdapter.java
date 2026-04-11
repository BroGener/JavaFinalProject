package business.adapter;

public class GPSAdapter implements GPSDataProvider {

    private final ExternalGPSDevice externalGPSDevice;

    public GPSAdapter(ExternalGPSDevice externalGPSDevice) {
        this.externalGPSDevice = externalGPSDevice;
    }

    @Override
    public double getLatitude() {
        String[] parts = externalGPSDevice.fetchCoordinates().split(",");
        return Double.parseDouble(parts[0]);
    }

    @Override
    public double getLongitude() {
        String[] parts = externalGPSDevice.fetchCoordinates().split(",");
        return Double.parseDouble(parts[1]);
    }

    @Override
    public boolean isInTransit() {
        return externalGPSDevice.moving();
    }

    @Override
    public Integer getNearestStationId() {
        return externalGPSDevice.stationCode();
    }
}
