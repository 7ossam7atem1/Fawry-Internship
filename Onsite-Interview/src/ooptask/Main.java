package ooptask;

public class Main {
    public static void main(String[] args) {
        Car gasCar = CarFactory.createCar(EngineType.GAS);
        gasCar.start();
        gasCar.accelerate();
        gasCar.brake();
        gasCar.stop();

        Car electricCar = CarFactory.createCar(EngineType.ELECTRIC);
        electricCar.start();
        electricCar.accelerate();
        electricCar.stop();

        Car hybridCar = CarFactory.createCar(EngineType.HYBRID);
        hybridCar.start();
        hybridCar.accelerate();
        hybridCar.stop();
    }
}
