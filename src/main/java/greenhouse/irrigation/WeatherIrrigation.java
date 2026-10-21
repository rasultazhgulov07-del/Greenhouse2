package greenhouse.irrigation;
import greenhouse.model.GreenhouseData;
import greenhouse.source.WeatherSource;
public class WeatherIrrigation implements IrrigationStrategy {
    private final WeatherSource weather;
    public WeatherIrrigation(WeatherSource weather) { this.weather = weather; }
    @Override public int waterLiters(GreenhouseData data) {
        if (data.soilMoisture() >= 60) return 0;
        int amount = (60 - data.soilMoisture()) / 5 + 1;
        return weather.isSunny() ? amount + 4 : Math.max(0, amount - 2);
    }
}
