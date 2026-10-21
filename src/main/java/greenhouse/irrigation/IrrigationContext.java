package greenhouse.irrigation;
import greenhouse.model.GreenhouseData;
import java.util.Objects;
public class IrrigationContext {
    private IrrigationStrategy strategy;
    public IrrigationContext(IrrigationStrategy strategy) { setStrategy(strategy); }
    public void setStrategy(IrrigationStrategy strategy) { this.strategy = Objects.requireNonNull(strategy); }
    public int execute(GreenhouseData data) { return strategy.waterLiters(data); }
}
