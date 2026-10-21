# Защита Assignment 5 — простыми словами

## Что сказать за 30 секунд

Моя тема — Smart Greenhouse. Я использовал паттерн Strategy для двух задач: полива и вентиляции. В каждой задаче три разных алгоритма. Context вызывает выбранный алгоритм через interface, поэтому алгоритм можно поменять во время работы. Selector автоматически выбирает стратегию по показаниям датчиков. Погодные данные скрыты за WeatherSource. Также есть тесты и Git-история до и после refactoring.

## Показываем код

1. `IrrigationStrategy` — один метод `waterLiters`.
2. `FixedIrrigation`, `MoistureIrrigation`, `WeatherIrrigation` — три способа расчёта.
3. `IrrigationContext` — не выбирает стратегию и не знает её тип; только вызывает метод.
4. `IrrigationSelector` — выбирает алгоритм по влажности почвы.
5. `WeatherSource` — интерфейс; вместо API в тестах fake `() -> true`.
6. `Main` — результаты одинаковых входных данных для всех стратегий и смена в runtime.
7. `git log --oneline` — два этапа: плохой switch и рефакторинг.

## Обычные вопросы

**Почему Strategy?** Можно менять алгоритмы, не меняя Context.

**Почему не State?** У нас нет состояния жизненного цикла объекта: мы выбираем способ расчёта.

**Почему не Template Method?** У нас composition через interface, не наследование шаблона.

**Что такое Open/Closed Principle?** Можно добавить новый класс алгоритма, не правя Context.

**Где допустим if?** В Selector для выбора, внутри самих алгоритмов для вычислений; в Context нельзя выбирать стратегию через if/switch.

**Для чего нужны тесты?** Проверяют расчёты каждой стратегии, правила Selector и отсутствие изменений после refactoring.

## Live coding: попробуй сам до защиты

**Задание 1. Добавь новую стратегию.** Создай `NoIrrigation implements IrrigationStrategy`, возвращает 0. В `Main` покажи `new IrrigationContext(new NoIrrigation()).execute(data)`.

**Задание 2. Измени Selector.** В `IrrigationSelector` замени границу очень сухой почвы `< 30` на `< 35`. Добавь тест для влажности 32.

**Задание 3. Поменяй источник данных.** Вместо `new SimulatedWeatherSource(true)` передай `() -> false`. Объясни изменение результата `WeatherIrrigation`.

## Команды (терминал IntelliJ)

```bash
mvn clean test
mvn exec:java
git log --oneline
```

При запуске через IntelliJ: открыть `Main.java`, нажать зелёный треугольник у `main`.

## Важное про GitHub

В ZIP есть локальная Git-история. После создания личного пустого репозитория на GitHub выполни:

```bash
git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO.git
git push -u origin main
```

После push относительные commit-ссылки в README будут вести на реальные страницы GitHub.
