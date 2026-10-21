# Smart Greenhouse — Assignment 5 (Strategy Pattern)

Java 17+, Maven, JUnit 5. Independent project; does not modify Assignment 4.

**Связь с предыдущей работой:** см. `ASSIGNMENT_4_CONNECTION_RU.md` (сопоставление с реальными классами Assignment 4).

## Quick start

- Open the root folder in IntelliJ IDEA as a Maven project.
- Run `mvn clean test` to execute all JUnit 5 tests.
- Run `mvn exec:java` to start the demonstration, or run `greenhouse.Main` in IntelliJ.
- The demonstration uses one shared input for all six strategies, manual choice, automatic Selector choice, and `setStrategy()` swaps.

## Strategy families

**Irrigation:** `FixedIrrigation` always returns 10 liters; `MoistureIrrigation` computes water from soil moisture; `WeatherIrrigation` adjusts moisture-based water with weather data through `WeatherSource`.

**Ventilation:** `TemperatureVentilation` uses temperature thresholds; `HumidityVentilation` uses humidity thresholds; `EcoVentilation` saves energy by running only when both temperature and humidity are high.

Selectors use distinct situation-based rules. `IrrigationSelector`: wet soil (>=60) -> moisture; very dry (<30) -> weather; otherwise -> fixed. `VentilationSelector`: hot (>=35 C) -> temperature; otherwise very humid (>=80%) -> humidity; otherwise -> eco. Conditions are in selectors, **not** contexts.

## Class responsibility table (each line is one sentence, maximum 25 words)

| Class / interface | Single responsibility |
|---|---|
| `GreenhouseData` | Stores validated greenhouse sensor readings. |
| `IrrigationStrategy` | Defines water calculation behavior. |
| `FixedIrrigation` | Provides a fixed watering volume. |
| `MoistureIrrigation` | Computes watering volume from soil moisture. |
| `WeatherIrrigation` | Adjusts watering volume using weather observations. |
| `IrrigationContext` | Delegates watering calculation to an interchangeable strategy. |
| `IrrigationSelector` | Selects watering behavior from soil conditions. |
| `IrrigationService` | Preserves the original irrigation behavior test interface. |
| `IrrigationMode` | Names supported watering modes. |
| `VentilationStrategy` | Defines fan speed calculation behavior. |
| `TemperatureVentilation` | Controls fan speed from temperature. |
| `HumidityVentilation` | Controls fan speed from atmospheric humidity. |
| `EcoVentilation` | Reduces fan use during comfortable conditions. |
| `VentilationContext` | Delegates fan calculation to an interchangeable strategy. |
| `VentilationSelector` | Selects fan behavior from greenhouse conditions. |
| `WeatherSource` | Defines an abstraction for weather observations. |
| `SimulatedWeatherSource` | Supplies simulated sunny weather observations. |
| `Main` | Demonstrates manual strategy selection, automatic selection, runtime replacement. |
| `IrrigationBehaviorTest` | Verifies irrigation behavior across refactoring commits. |
| `IrrigationStrategyTest` | Tests watering algorithms through JUnit. |
| `VentilationStrategyTest` | Tests fan algorithms through JUnit. |
| `SelectorTest` | Tests all automatic selection rules. |

## Why Strategy, not State or Template Method?

This design uses **Strategy** because the greenhouse operator needs a family of interchangeable algorithms for one task. Every irrigation algorithm implements `IrrigationStrategy`, while every ventilation algorithm implements `VentilationStrategy`. `IrrigationContext` holds a strategy reference, calls its operation, and allows changing that reference through `setStrategy()`. This means the same greenhouse input can be processed using different algorithms at runtime without editing context code. Adding a new option such as `NightIrrigation` only requires implementing `IrrigationStrategy` and changing the optional Selector policy, not changing the context. This is the Open/Closed Principle applied to algorithm execution.

**State** looks similar structurally, but its purpose is different: an object's behavior changes according to its lifecycle state, often with transitions controlled by states. Here a person or a Selector chooses the algorithm; a strategy does not represent lifecycle stages like NEW, ACTIVE, or COMPLETED. **Template Method** would use inheritance: a superclass defines the algorithm skeleton, then subclasses override steps. Our design uses composition through interfaces instead. There is no shared fixed algorithm skeleton in a base class. This keeps the six algorithms independent, small, and easy to explain during the defense. The Selector's decisions are a separate concern, so the context does not inspect concrete types.

## External data isolation

`WeatherIrrigation` depends only on `WeatherSource`, never on a weather API. Production demo: `SimulatedWeatherSource(true)`; unit test: a fake lambda `() -> true` with a fixed return value. Swapping the data source requires no change to `WeatherIrrigation`.

## Git refactoring history

- **Before:** [`f019b4a7f722600f2893f94285a5b96b56450d77`](../../commit/f019b4a7f722600f2893f94285a5b96b56450d77) — `LegacyIrrigation` contains all three algorithms in a `switch`; first four behavior tests exist.
- **After:** [`HEAD`](../../commit/HEAD) — irrigation uses Strategy; same four behavior tests remain unchanged.

The ZIP contains the full `.git` directory. Use `git log --oneline` and `git show f019b4a7f722600f2893f94285a5b96b56450d77:src/main/java/greenhouse/irrigation/LegacyIrrigation.java` to inspect the initial approach. The relative commit links in this README work after publishing the repository to GitHub; until then, use the included Git commit hashes locally. No remote repository has been created.

## Tests

`IrrigationBehaviorTest`: 4 unchanged tests in both commits. `IrrigationStrategyTest`: all 3 strategies, fake weather, runtime switching, JUnit 5 parameterized test. `VentilationStrategyTest`: all 3 strategies, runtime switching, second parameterized test. `SelectorTest`: all 6 selection rules, including prioritization.

## Demo defense

1. Run `Main` and show the output of 6 strategies.
2. Explain `IrrigationStrategy` interface and `IrrigationContext.setStrategy()`.
3. Open `IrrigationSelector` to show where selection decisions belong.
4. Open `WeatherSource` to demonstrate fake external data injection.
5. Open test files and run `mvn test`.
6. Explain `git log --oneline` and the before/after change.

See `DEFENSE_GUIDE_RU.md` for simple explanation and live-coding exercises. See `UML.puml` for the class diagram.
