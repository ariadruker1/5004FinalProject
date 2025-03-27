package pet;

/**
 * Interface for the game pet where you have to
 * take care of the pet's needs.
 */
public interface PetInterface {

  /**
   * Increments time.
   */
  void step();

  /**
   * making the pet complete actions.
   *
   * @param action to complete
   */
  void interactWith(Action action);

  /**
   * Gets the health status of the pet.
   */
  HealthStatus getHealth();

  /**
   * Gets the pets mood.
   *
   * @return mood of the pet
   */
  MoodEnum getMood();

  /**
   * Sets the mood of the pet.
   *
   * @param mood of the pet
   */
  void setMood(MoodEnum mood);

  /**
   * Checks if the pet is alive.
   *
   * @return true if the pet is alive
   */
  boolean isAlive();

  /**
   * Sends the pet to another home.
   *
   * @return the not live status of pet
   */
  boolean sendToNewHome();
}
