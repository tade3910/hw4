import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class GuessNumberIOTest {

    Random mockRandom = new Random(40);

    @Test
    public void testCorrectGuess() {
        // set up testable input and output channels
        byte[] data = """
                83
                """.getBytes(StandardCharsets.UTF_8);
        BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(data));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(output);
        // redirect the standard channels
        System.setIn(in); System.setOut(out);
        // call method that does standard I/O
        GuessNumber.guessingNumberGame(mockRandom);
        String string = output.toString();
        String expectedOutput = """
                A number is chosen between 1 to 100. Guess the number within 5 trials.
                Guess the number: Congratulations! You guessed the number.
                """; // change to 50
        assertEquals(expectedOutput, string);
        // redirect the standard channels back!
        System.setIn(System.in); System.setOut(System.out);
    }



    @Test
    public void testGuessIsSmallerNumberNumber() {
        // set up testable input and output channels
        byte[] data = """
                1
                1
                1
                1
                1
                """.getBytes(StandardCharsets.UTF_8);
        BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(data));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(output);
        // redirect the standard channels
        System.setIn(in); System.setOut(out);
        // call method that does standard I/O
        GuessNumber.guessingNumberGame(mockRandom);
        String string = output.toString();
        String expectedOutput = """
                A number is chosen between 1 to 100. Guess the number within 5 trials.
                Guess the number: The number is greater than 1
                Guess the number: The number is greater than 1
                Guess the number: The number is greater than 1
                Guess the number: The number is greater than 1
                Guess the number: The number is greater than 1
                You have exhausted 5 trials.
                The number was 83
                """; // change to 50
        assertEquals(expectedOutput, string);
    // redirect the standard channels back!
        System.setIn(System.in); System.setOut(System.out);
    }

    //Failure is that the statement The number is less than `random number` is not outputted
    // on the fifth try when the guess is less than the random number, a possible fault
    // is that the condition to print this statement is for if the number of guesses made
    // 4 or less when the guess made is less than the random number
    @Test
    public void testGuessIsBiggerNumber() {
        // set up testable input and output channels
        byte[] data = """
                100
                100
                100
                100
                100
                """.getBytes(StandardCharsets.UTF_8);
        BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(data));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(output);
        // redirect the standard channels
        System.setIn(in); System.setOut(out);
        // call method that does standard I/O
        GuessNumber.guessingNumberGame(mockRandom);
        String string = output.toString();
        String expectedOutput = """
                A number is chosen between 1 to 100. Guess the number within 5 trials.
                Guess the number: The number is less than 100
                Guess the number: The number is less than 100
                Guess the number: The number is less than 100
                Guess the number: The number is less than 100
                Guess the number: The number is less than 100
                You have exhausted 5 trials.
                The number was 83
                """; // change to 50
        assertEquals(expectedOutput, string);
        // redirect the standard channels back!
        System.setIn(System.in); System.setOut(System.out);
    }

    @Test
    public void testCorrectGuessOnLastNumber() {
        // set up testable input and output channels
        byte[] data = """
                100
                1
                1
                100
                83
                """.getBytes(StandardCharsets.UTF_8);
        BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(data));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(output);
        // redirect the standard channels
        System.setIn(in); System.setOut(out);
        // call method that does standard I/O
        GuessNumber.guessingNumberGame(mockRandom);
        String string = output.toString();
        String expectedOutput = """
                A number is chosen between 1 to 100. Guess the number within 5 trials.
                Guess the number: The number is less than 100
                Guess the number: The number is greater than 1
                Guess the number: The number is greater than 1
                Guess the number: The number is less than 100
                Guess the number: Congratulations! You guessed the number.      
                """; // change to 50
        assertEquals(expectedOutput, string);
        // redirect the standard channels back!
        System.setIn(System.in); System.setOut(System.out);
    }

    @Test
    public void testCorrectGuessOnThirdNumber() {
        // set up testable input and output channels
        byte[] data = """
                100
                1
                83
                """.getBytes(StandardCharsets.UTF_8);
        BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(data));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(output);
        // redirect the standard channels
        System.setIn(in); System.setOut(out);
        // call method that does standard I/O
        GuessNumber.guessingNumberGame(mockRandom);
        String string = output.toString();
        String expectedOutput = """
                A number is chosen between 1 to 100. Guess the number within 5 trials.
                Guess the number: The number is less than 100
                Guess the number: The number is greater than 1
                Guess the number: Congratulations! You guessed the number.      
                """; // change to 50
        assertEquals(expectedOutput, string);
        // redirect the standard channels back!
        System.setIn(System.in); System.setOut(System.out);
    }



    //Failure is when the number returned from the random generator is passed in, the congratulations
    //message is not displayed. A possible fault is that the number being compared to the guesses is
    // different from the number being generated
    @Test
    public void testGuessingNumberGameWithEqualNumberAndOverridenNextInt() {
        Random mockRandom2 = new Random() {
            @Override
            public int nextInt(int bound) {
                return 25; // Fixed number for testing
            }
        };
        byte[] data = """
                25
                """.getBytes(StandardCharsets.UTF_8);
        BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(data));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(output);
        // redirect the standard channels
        System.setIn(in); System.setOut(out);
        // call method that does standard I/O
        GuessNumber.guessingNumberGame(mockRandom2);
        String string = output.toString();
        String expectedOutput = """
                A number is chosen between 1 to 100. Guess the number within 5 trials.
                Guess the number: Congratulations! You guessed the number.
                """;
        assertEquals(expectedOutput, string);
        // redirect the standard channels back!
        System.setIn(System.in); System.setOut(System.out);

    }

}
