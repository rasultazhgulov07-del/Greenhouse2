package greenhouse.source;
public class SimulatedWeatherSource implements WeatherSource {
    private final boolean sunny;
    public SimulatedWeatherSource(boolean sunny) { this.sunny = sunny; }
    @Override public boolean isSunny() { return sunny; }
}
