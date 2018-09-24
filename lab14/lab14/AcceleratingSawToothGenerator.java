package lab14;

import lab14lib.*;

public class AcceleratingSawToothGenerator implements Generator {
    private int period;
    private int state;
    private double factor;
    private int accumulate;


    public AcceleratingSawToothGenerator(int period, double factor) {
        this.period = period;
        state = 0;
        this.factor = factor;
        accumulate = 0;
    }


    @Override
    public double next() {
        state = state + 1;
        if ((state - accumulate) % period == 0) {
            accumulate += period;
            period = (int) (period * factor);
        }

        return normalize();
    }

    public double normalize () {
        return ((double)(state - accumulate) / (double) period * 2.0 - 1.0);
    }

    public static void main(String[] args) {
        Generator generator = new AcceleratingSawToothGenerator(200, 1.1);
        GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(generator);
        gav.drawAndPlay(4096, 1000000);
    }
}
