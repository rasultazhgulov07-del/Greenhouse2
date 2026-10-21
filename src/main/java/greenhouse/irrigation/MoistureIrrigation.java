package greenhouse.irrigation;
import greenhouse.model.GreenhouseData;
public class MoistureIrrigation implements IrrigationStrategy {
    @Override public int waterLiters(GreenhouseData data) {
        if (data.soilMoisture() >= 60) return 0;
        return (60 - data.soilMoisture()) / 5 + 1;
    }
}
