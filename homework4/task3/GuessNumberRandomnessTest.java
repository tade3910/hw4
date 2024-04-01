import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GuessNumberRandomnessTest {


    @Test
    public void testRandomNumberDistribution() {
        int[] frequencyMap = new int[100];
        for (int i = 0; i < 100; i++) {
            frequencyMap[i] = 0; //Initialize frequencyMap to all 0s
        }

        Random rnd = new Random(0);
        for (int i = 0; i < 30000; i++) {
            byte[] data = """
                1
                1
                1
                1
                1
                """.getBytes(StandardCharsets.UTF_8);
            BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(data));
            // redirect the standard channels
            System.setIn(in); System.setOut(new PrintStream(new ByteArrayOutputStream()));
            // call method that does standard I/O
            int randomNumber = GuessNumber.guessingNumberGame(rnd);
            // redirect the standard channels back!
            System.setIn(System.in); System.setOut(System.out);
            frequencyMap[randomNumber - 1]++; // O index generated number
        }

        int minFrequency = frequencyMap[0], maxFrequency = frequencyMap[0];

        for (int frequency : frequencyMap) {
            minFrequency = Math.min(frequency, minFrequency);
            maxFrequency = Math.max(frequency, maxFrequency);
        }


        assertTrue(maxFrequency <= 1.5 * minFrequency);
    }

}
