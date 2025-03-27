package pet;

public class HealthStatus {
  private int hunger;
  private int hygiene;
  private int social;
  private int sleep;

  public HealthStatus(int hunger, int hygiene, int social, int sleep) {
    this.hunger = hunger;
    this.hygiene = hygiene;
    this.social = social;
    this.sleep = sleep;
  }

  public int getHunger() {
    return this.hunger;
  }

  public int getHygiene() {
    return this.hygiene;
  }

  public int getSocial() {
    return this.social;
  }

  public int getSleep() {
    return this.sleep;
  }

  public void changeHunger(int changeHungerAmount) {
    if (changeHungerAmount + this.hunger <= 0) {

    }
    if (changeHungerAmount + this.hunger > 100) {
      this.hunger = 100;
    }
    this.hunger += changeHungerAmount;
  }

  public void changeHygiene(int changeHygieneAmount) {
    if (changeHygieneAmount + this.hygiene <= 0) {
      throw new IllegalArgumentException("Hygiene has not been cared for.");
    }
    if (changeHygieneAmount + this.hygiene > 100) {
      this.hygiene = 100;
    }
    this.hygiene += changeHygieneAmount;
  }

  public void changeSocial(int changeSocialAmount) {
    if (changeSocialAmount + this.social <= 0) {
      throw new IllegalArgumentException("Social has not been cared for.");
    }
    if (changeSocialAmount + this.social > 100) {
      this.social = 100;
    }
    this.social += changeSocialAmount;
  }

  public void changeSleep(int changeSleepAmount) {
    if (changeSleepAmount + this.sleep <= 0) {
      throw new IllegalArgumentException("Sleep has not been cared for.");
    }
    if (changeSleepAmount + this.sleep > 100) {
      this.sleep = 100;
    }
    this.sleep += changeSleepAmount;
  }

  @Override
  public String toString() {
    return "HealthStatus{" +
        "hunger=" + (100 - hunger) +
        ", hygiene=" + hygiene +
        ", social=" + social +
        ", sleep=" + sleep +
        '}';
  }
}
