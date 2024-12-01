package search.Utils;

import java.io.IOException;
import java.util.Random;

public class GameGimmick implements GimmickAction {
    // @SuppressWarnings("resource")
    @Override
    public String gimmick(String key) {
        // Implement the gimmick for "random" node
        StringBuilder result = new StringBuilder();
        Random random = new java.util.Random();

        if (key.equals("dice")) {
            result.append("Rolling the dice...\n");
            int diceRoll = random.nextInt(6) + 1; // Generates a number between 1 and 6
            result.append("You rolled a: ").append(diceRoll).append("\n");
        } else if (key.equals("kalkulator")) {
            result.append("Welcome to the Calculator!\n");
            result.append("Calculator functionality is not supported in GUI.\n");
        } else if (key.equals("coin")) {
            result.append("Flipping the coin...\n");
            boolean flipResult = random.nextBoolean(); // Randomly returns true or false
            result.append("The coin landed on: ").append(flipResult ? "Heads" : "Tails").append("\n");
        } else if (key.equals("close")) {
            result.append("Attempting to close Visual Studio Code...\n");
            String command;
            String osName = System.getProperty("os.name").toLowerCase();
    
            if (osName.contains("windows")) {
                command = "taskkill /IM Code.exe /F";
            } else if (osName.contains("mac")) {
                command = "osascript -e 'quit app \"Visual Studio Code\"'";
            } else { // Linux
                command = "pkill code";
            }
    
            try {
                Runtime.getRuntime().exec(command);
                result.append("Command executed successfully.\n");
            } catch (IOException e) {
                result.append("Failed to execute command: ").append(e.getMessage()).append("\n");
            }
        } else {
            result.append("Unknown key: ").append(key).append("\n");
        }
    
        return result.toString();
    }
}