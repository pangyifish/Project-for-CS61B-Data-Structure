package lab14;
import lab14lib.*;


public class StrangeBitwiseGenerator implements Generator {
    private int period;
    private int state;
    private int weirdState;

    public StrangeBitwiseGenerator(int period) {
        state = 0;
        this.period = period;
    }

    public double next() {
        state = state + 1;
        weirdState = state & (state>>>7) % period;
        return normalize();
    }

    public double normalize () {
        return ((double)weirdState % period) / (double) period * 2.0 - 1.0;
    }

    public static void main(String[] args) {

        Generator generator = new StrangeBitwiseGenerator(1024);
        GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(generator);
        gav.drawAndPlay(128000, 1000000);

    }
}
