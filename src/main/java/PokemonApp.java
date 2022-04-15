import java.io.FileNotFoundException;
import java.io.IOException;

public class PokemonApp {
  public static void main(String[] args) {
    try {
      IPokemonFrontend app = new PokemonFrontend(new PokemonBackend(new PokemonLoader()));
      app.runCommandLoop();
    } catch (IOException ioe) {
      System.out.println("ERROR: data collection failed");
      ioe.printStackTrace();
    }
  }
}
