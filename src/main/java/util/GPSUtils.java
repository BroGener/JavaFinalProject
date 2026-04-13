package util;

import business.model.ChargingStation;
import java.util.List;

public final class GPSUtils {
    private GPSUtils() {}

    // Algonquin College 大概范围
    private static final double BASE_LAT = 45.3483;
    private static final double BASE_LNG = -75.7537;
    private static final double RANGE = 0.003; // 约300米范围

    // 生成随机坐标（在校园范围内）
    public static double[] randomLocation() {
        double lat = BASE_LAT + (Math.random() - 0.5) * RANGE;
        double lng = BASE_LNG + (Math.random() - 0.5) * RANGE;
        return new double[]{lat, lng};
    }

    // 勾股定理找最近站点
    public static int findNearestStation(double lat, double lng, List<ChargingStation> stations) {
        int nearestId = -1;
        double minDist = Double.MAX_VALUE;

        for (ChargingStation station : stations) {
            double[] coords = parseLocation(station.getLocation());
            if (coords == null) continue;

            double dist = Math.sqrt(
                Math.pow(lat - coords[0], 2) +
                Math.pow(lng - coords[1], 2)
            );

            if (dist < minDist) {
                minDist = dist;
                nearestId = station.getStationId();
            }
        }
        return nearestId;
    }

    // 解析 "lat:45.3494,lng:-75.7554" 格式
    public static double[] parseLocation(String location) {
        try {
            String[] parts = location.split(",");
            double lat = Double.parseDouble(parts[0].split(":")[1]);
            double lng = Double.parseDouble(parts[1].split(":")[1]);
            return new double[]{lat, lng};
        } catch (Exception e) {
            return null;
        }
    }
}