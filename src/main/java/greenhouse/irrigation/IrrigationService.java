package greenhouse.irrigation;
import greenhouse.model.GreenhouseData;
import greenhouse.source.SimulatedWeatherSource;
// Compatibility facade: preserves the exact same four tests in both Git commits.
public class IrrigationService {
    public int calculate(IrrigationMode mode, GreenhouseData data) {
        IrrigationStrategy strategy = switch (mode) {
            case FIXED -> new FixedIrrigation();
            case MOISTURE -> new MoistureIrrigation();
            case WEATHER -> new WeatherIrrigation(new SimulatedWeatherSource(data.sunny()));
        };
        return new IrrigationContext(strategy).execute(data);
    }
}
