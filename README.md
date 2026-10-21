# Smart Greenhouse — Assignment 5

## Before refactoring (commit 1)
`LegacyIrrigation` uses `switch` for three watering algorithms. `IrrigationBehaviorTest` checks the behavior.

Run `mvn test`. Next commit replaces the `switch` with Strategy while keeping all four tests.
