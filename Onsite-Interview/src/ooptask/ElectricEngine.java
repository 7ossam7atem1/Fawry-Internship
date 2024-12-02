package ooptask;

public class ElectricEngine implements Engine {
    private int speed;

    @Override
    public void start() {
        System.out.println("Electric engine starting...");
    }

    @Override
    public void stop() {
        System.out.println("Electric engine stopping...");
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
        System.out.println("Electric engine speed set to " + speed);
    }
}
