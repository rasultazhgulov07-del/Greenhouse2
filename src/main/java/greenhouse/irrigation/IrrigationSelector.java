package greenhouse.irrigation;
import greenhouse.model.GreenhouseData;
import greenhouse.source.WeatherSource;
public class IrrigationSelector {
    private final WeatherSource weather;
    public IrrigationSelector(WeatherSource weather) { this.weather = weather; }
    public IrrigationStrategy select(GreenhouseData data) {
        if (data.soilMoisture() >= 60) return new MoistureIrrigation(); // wet soil: no watering
        if (data.soilMoisture() < 30) return new WeatherIrrigation(weather); // very dry: consider weather
        return new FixedIrrigation(); // otherwise stable schedule
    }
}
