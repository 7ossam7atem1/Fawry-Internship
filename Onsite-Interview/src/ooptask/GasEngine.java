package ooptask;

public class GasEngine implements Engine {
    private int speed;

    @Override
    public void start() {
        System.out.println("Gas engine starting...");
    }

    @Override
    public void stop() {
        System.out.println("Gas engine stopping...");
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
        System.out.println("Gas engine speed set to " + speed);
    }
}

