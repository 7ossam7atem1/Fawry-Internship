package ooptask;

public class CarFactory {
    public static Car createCar(EngineType type) {
        Car car = new Car();
        installEngine(car, type);
        return car;
    }

    private static Engine createEngine(EngineType type) {
        switch (type) {
            case GAS:
                return new GasEngine();
            case ELECTRIC:
                return new ElectricEngine();
            case HYBRID:
                return new HybridEngine();
            default:
                throw new IllegalArgumentException("Unknown engine type: " + type);
        }
    }

    private static void installEngine(Car car, EngineType type) {
        car.setEngine(createEngine(type));
    }
}

