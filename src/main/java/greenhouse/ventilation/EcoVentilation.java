package greenhouse.ventilation;
import greenhouse.model.GreenhouseData;
public class EcoVentilation implements VentilationStrategy {
    @Override public int fanPercent(GreenhouseData data) {
        // Power saving: ventilate only if BOTH indicators are above comfort levels.
        return data.temperature() >= 30 && data.airHumidity() >= 70 ? 40 : 0;
    }
}
