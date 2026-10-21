package greenhouse.irrigation;
import greenhouse.model.GreenhouseData;
public class IrrigationService {
    private final LegacyIrrigation legacy = new LegacyIrrigation();
    public int calculate(IrrigationMode mode, GreenhouseData data) { return legacy.calculate(mode, data); }
}
