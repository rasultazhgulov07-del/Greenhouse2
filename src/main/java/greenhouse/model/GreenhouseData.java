package greenhouse.model;
public record GreenhouseData(int soilMoisture, int temperature, int airHumidity, boolean sunny) {
    public GreenhouseData {
        if (soilMoisture < 0 || soilMoisture > 100 || airHumidity < 0 || airHumidity > 100)
            throw new IllegalArgumentException("Moisture must be between 0 and 100");
    }
}
