# Как связаны Assignment 4 и Assignment 5 (Variant 9)

Оба проекта используют одну предметную область **Smart Greenhouse**, но решают разные задачи. В Assignment 4 показан **Facade** и workflow с компенсацией; в Assignment 5 показан **Strategy** и выбор алгоритма. Это **отдельные Maven-проекты**: код Assignment 5 не подключается автоматически к Assignment 4. На защите нельзя утверждать, что стратегии уже встроены в старый `GreenhouseFacade`.

| Assignment 4 | Assignment 5 | Объяснение |
|---|---|---|
| `greenhouse.a.ScheduleService`, `SoilRule` | `IrrigationStrategy`, `IrrigationContext`, `IrrigationSelector` | A4 резервирует график полива при влажности ниже 60; A5 выбирает количество воды в литрах. |
| `greenhouse.b.VentilationService`, `ClimateRule` | `VentilationStrategy`, `VentilationContext`, `VentilationSelector` | A4 запускает вентиляцию и проверяет допустимость температуры 10–40; A5 рассчитывает скорость вентилятора в процентах. |
| `greenhouse.c.NutrientService` | Без изменений | Для Assignment 5 достаточно двух семей стратегий, поэтому третью подсистему не копировали. |
| `GreenhouseFacade`, `AdminFacade`, `JobRepository` | Нет прямого аналога | Facade управляет последовательностью и отменой операций; Strategy заменяет вычислительный алгоритм. |

## Три коротких ответа на защите

**Почему разные проекты?** Преподаватель допускает ту же предметную область; оценка Assignment 5 касается только его собственного кода. Для ясности новый паттерн сделан отдельным демонстрационным приложением.

**Как они могут работать вместе?** Перед созданием графика `ScheduleService` можно вызвать `IrrigationContext.execute(data)` для расчёта количества воды. Перед включением вентиляции можно вызвать `VentilationContext.execute(data)` для выбора процента мощности. **Это возможная интеграция, в текущем коде её нет.**

**Почему логика отличается?** В A4 `SoilRule` только проверяет, можно ли зарезервировать полив; в A5 алгоритм вычисляет объём воды. Аналогично A4 проверяет температуру и включает вентиляцию, A5 вычисляет режим работы вентилятора.

## Что показать преподавателю

1. A4: `GreenhouseFacade.start()` и последовательность A → B → C.
2. A5: `IrrigationStrategy`, три реализации, `IrrigationContext.setStrategy()`.
3. A5: `IrrigationSelector` и `WeatherSource` с fake для тестов.
4. Выполнение `mvn clean test` **на своей машине** и `git log --oneline` в папке A5.
