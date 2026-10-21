package greenhouse.ventilation;
import greenhouse.model.GreenhouseData;
import java.util.Objects;
public class VentilationContext {
    private VentilationStrategy strategy;
    public VentilationContext(VentilationStrategy strategy) { setStrategy(strategy); }
    public void setStrategy(VentilationStrategy strategy) { this.strategy = Objects.requireNonNull(strategy); }
    public int execute(GreenhouseData data) { return strategy.fanPercent(data); }
}
