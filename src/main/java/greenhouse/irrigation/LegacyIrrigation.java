package greenhouse.irrigation;
import greenhouse.model.GreenhouseData;
// BEFORE refactoring: all algorithms are mixed in one class.
public class LegacyIrrigation {
    public int calculate(IrrigationMode mode, GreenhouseData data) {
        switch (mode) {
            case FIXED: return 10;
            case MOISTURE: return data.soilMoisture() >= 60 ? 0 : (60 - data.soilMoisture()) / 5 + 1;
            case WEATHER:
                if (data.soilMoisture() >= 60) return 0;
                int amount = (60 - data.soilMoisture()) / 5 + 1;
                return data.sunny() ? amount + 4 : Math.max(0, amount - 2);
            default: throw new IllegalArgumentException("Unknown mode");
        }
    }
}
