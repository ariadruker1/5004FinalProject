import java.util.Scanner;
import pet.PetInterfaceImpl;
import pet.PetInterface;
import pet.Action;
import pet.MoodEnum;
import pet.HealthStatus;

/**
 * Main class for the pet simulation.
 */
public class main {
  public static void main(String[] args) {
    PetInterface pet = new PetInterfaceImpl();
    Scanner scanner = new Scanner(System.in);
    printMenu();
    String command = "";

    while (!command.equals("x") || !pet.isAlive()) {
      try {
        printAction();
        command = scanner.nextLine().toLowerCase().trim();
        while (!command.equals("p") && !command.equals("f")
            && !command.equals("c") && !command.equals("s")
            && !command.equals("l") && !command.equals("q")
            && !command.equals("x")) {
          if (command.equals("x")) {
            break;
          }
          System.out.println("Not one of the actions. Please try again.");
          printAction();
          command = scanner.nextLine().toLowerCase().trim();
        }
        switch (command) {
          case "p":
            pet.interactWith(Action.PET);
            break;
          case "f":
            pet.interactWith(Action.FEED);
            break;
          case "c":
            pet.interactWith(Action.CLEAN);
            break;
          case "s":
            pet.interactWith(Action.SLEEP);
            break;
          case "l":
            pet.interactWith(Action.LOUDTOY);
            break;
          case "q":
            pet.interactWith(Action.QUIETTOY);
            break;
        }
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
      System.out.println("Health Status: " + pet.getHealth());
      System.out.println("Mood: " + pet.getMood());
    }
    printEndOfGame();
  }


  /**
   * Prints the menu of commands for the Virtual Pet Care application.
   */
  private static void printMenu() {
    System.out.println("Welcome to Virtual Pet Care! Type a command:");
    System.out.println("Commands:");
    System.out.println("p: Pet");
    System.out.println("f: Feed");
    System.out.println("c: Clean");
    System.out.println("s: Sleep");
    System.out.println("l: Loud Toy");
    System.out.println("q: Quiet Toy");
    System.out.println("x: Exit the game");
    System.out.println("What would you like to do?");
  }

  /**
   * Prints the action options for the user.
   */
  private static void printAction() {
    System.out.println(
        "\nAction options {p: pet, f: feed, c: clean, s: sleep, l: loud toy, q: quiet toy, x: exit}");
  }

  private static void printEndOfGame() {
    System.out.println("Your pet has been sent to another home. Thanks for playing!");
  }
}
