package ooptask;

public class Car {
    private Engine engine;
    private int speed = 0;

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void start() {
        engine.start();
    }

    public void stop() {
        engine.stop();
    }

    public void accelerate() {
        if (this.speed < 200) {
            this.speed += 20;
        }
    }

    public void brake() {
        if (this.speed > 0) {
            this.speed -= 20;
        }
    }
}
