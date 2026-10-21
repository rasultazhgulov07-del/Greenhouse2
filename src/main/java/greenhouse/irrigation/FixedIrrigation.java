package greenhouse.irrigation;
import greenhouse.model.GreenhouseData;
public class FixedIrrigation implements IrrigationStrategy {
    @Override public int waterLiters(GreenhouseData data) { return 10; }
}
