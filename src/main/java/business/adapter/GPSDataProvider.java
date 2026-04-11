package business.adapter;

public interface GPSDataProvider {
    double getLatitude();
    double getLongitude();
    boolean isInTransit();
    Integer getNearestStationId();
}
