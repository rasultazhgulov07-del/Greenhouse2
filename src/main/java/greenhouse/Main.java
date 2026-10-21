package greenhouse;
import greenhouse.model.GreenhouseData;
import greenhouse.irrigation.*;
import greenhouse.ventilation.*;
import greenhouse.source.*;
public class Main {
    public static void main(String[] args) {
        GreenhouseData data = new GreenhouseData(20, 36, 82, true);
        WeatherSource weather = new SimulatedWeatherSource(true);
        System.out.println("=== SMART GREENHOUSE: STRATEGY DEMO ===");
        System.out.println("Sensor input: soil=20%, temperature=36C, air humidity=82%, sunny=true");
        System.out.println("1) Same input / ALL irrigation strategies:");
        IrrigationStrategy[] irrigation = {new FixedIrrigation(), new MoistureIrrigation(), new WeatherIrrigation(weather)};
        for (IrrigationStrategy s : irrigation) System.out.println("  " + s.getClass().getSimpleName() + ": " + s.waterLiters(data) + " liters");
        System.out.println("2) Same input / ALL ventilation strategies:");
        VentilationStrategy[] ventilation = {new TemperatureVentilation(), new HumidityVentilation(), new EcoVentilation()};
        for (VentilationStrategy s : ventilation) System.out.println("  " + s.getClass().getSimpleName() + ": " + s.fanPercent(data) + "% fan");
        System.out.println("3) Manual selection:");
        IrrigationContext water = new IrrigationContext(new FixedIrrigation());
        System.out.println("  Manual Fixed: " + water.execute(data) + " liters");
        System.out.println("4) Runtime swap via setStrategy():");
        water.setStrategy(new MoistureIrrigation());
        System.out.println("  Switched to Moisture: " + water.execute(data) + " liters");
        System.out.println("5) Automatic selection (Selector):");
        IrrigationStrategy automatic = new IrrigationSelector(weather).select(data);
        water.setStrategy(automatic);
        System.out.println("  Selected " + automatic.getClass().getSimpleName() + ": " + water.execute(data) + " liters");
        VentilationContext fan = new VentilationContext(new EcoVentilation());
        System.out.println("  Manual Eco: " + fan.execute(data) + "% fan");
        fan.setStrategy(new TemperatureVentilation());
        System.out.println("  Switched to Temperature: " + fan.execute(data) + "% fan");
        VentilationStrategy automaticFan = new VentilationSelector().select(data);
        fan.setStrategy(automaticFan);
        System.out.println("  Automatically selected " + automaticFan.getClass().getSimpleName() + ": " + fan.execute(data) + "% fan");
    }
}
