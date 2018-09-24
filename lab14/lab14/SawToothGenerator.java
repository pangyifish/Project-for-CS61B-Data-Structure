package lab14;
import lab14lib.*;

public class SawToothGenerator implements Generator{
    private int period;
    private int state;

    public SawToothGenerator(int period) {
        state = 0;
        this.period = period;
    }

    public double next() {
        state = state + 1;
        return normalize();
    }

    public double normalize () {
        return ((double)state % period) / (double) period * 2.0 - 1.0;
    }

    public static void main(String[] args) {

        Generator generator = new SawToothGenerator(512);
        GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(generator);
        gav.drawAndPlay(4096, 1000000);

    }
}
