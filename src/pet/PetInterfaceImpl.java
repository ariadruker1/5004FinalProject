package pet;

public class PetInterfaceImpl implements PetInterface {
  HealthStatus healthStatus;
  MoodEnum mood;
  boolean isAlive;
  int overStimulatedThreshold = 30;
  int calmThreshold = 80;
  int startHealth = 40;
  int Compensation = 100;
  int stepAmount = 5;
  int smallChange = 10;
  int mediumChange = 20;
  int largeChange = 30;

  public PetInterfaceImpl() {
    this.healthStatus =
        new HealthStatus((Compensation - startHealth), startHealth, startHealth, startHealth);
    this.mood = MoodEnum.CALM;
    this.isAlive = true;
  }

  @Override
  public void step() {
    healthStatus.changeSleep(-stepAmount);
    healthStatus.changeHunger(stepAmount);
    healthStatus.changeHygiene(-stepAmount);
    healthStatus.changeSocial(-stepAmount);
    if (healthStatus.getHunger() > (Compensation - overStimulatedThreshold) ||
        healthStatus.getSleep() < overStimulatedThreshold ||
        healthStatus.getHygiene() < overStimulatedThreshold ||
        healthStatus.getSocial() < overStimulatedThreshold) {
      mood = MoodEnum.OVERSTIMULATED;
    } else if (healthStatus.getSleep() < calmThreshold ||
        healthStatus.getHunger() < calmThreshold ||
        healthStatus.getHygiene() < calmThreshold || healthStatus.getSocial() < calmThreshold) {
      mood = MoodEnum.CALM;
    } else {
      mood = MoodEnum.EXCITED;
    }
  }

  @Override
  public void interactWith(Action action) {
    try {
      if (mood == MoodEnum.CALM) {
        switch (action) {
          case FEED:
            healthStatus.changeHunger(-mediumChange);
            healthStatus.changeSocial(stepAmount); //offsets social decrease
            healthStatus.changeSleep(stepAmount); //offsets sleep decrease
            break;
          case CLEAN:
            healthStatus.changeHygiene(+mediumChange);
            healthStatus.changeSleep(stepAmount); //offsets sleep decrease
            break;
          case PET:
            healthStatus.changeSocial(+mediumChange);
            healthStatus.changeHunger(-stepAmount); //offsets hunger increase
            break;
          case SLEEP:
            healthStatus.changeSleep(+smallChange);
            healthStatus.changeHygiene(stepAmount); //offsets hygiene decrease
            break;
          case LOUDTOY:
            healthStatus.changeSocial(+largeChange);
            healthStatus.changeHunger(stepAmount); //increases hunger
            healthStatus.changeSleep(-stepAmount); //increases sleepiness
            break;
          case QUIETTOY:
            healthStatus.changeSocial(+mediumChange);
            healthStatus.changeSleep(stepAmount); //offsets sleep decrease
            healthStatus.changeSocial(stepAmount); //offsets social decrease
            break;
        }
      } else if (mood == MoodEnum.EXCITED) {
        switch (action) {
          case FEED:
            healthStatus.changeHunger(-smallChange);
            healthStatus.changeSocial(stepAmount); //offsets social decrease
            healthStatus.changeSleep(stepAmount); //offsets sleep decrease
            break;
          case CLEAN:
            healthStatus.changeHygiene(smallChange);
            healthStatus.changeSleep(stepAmount); //offsets sleep decrease
            break;
          case PET:
            if (healthStatus.getSocial() == Compensation) {
              healthStatus.changeSocial(-calmThreshold);
            } else {
              healthStatus.changeSocial(smallChange);
            }
            break;
          case SLEEP:
            healthStatus.changeSleep(mediumChange);
            break;
          case LOUDTOY:
            healthStatus.changeSocial(overStimulatedThreshold);
            break;
          case QUIETTOY:
            healthStatus.changeSocial(calmThreshold);
            break;
        }
      } else { //OVERSTIMULATED
        switch (action) {
          case FEED:
            healthStatus.changeHunger(smallChange);
            break;
          case CLEAN:
            healthStatus.changeHygiene(mediumChange);
            break;
          case PET:
            healthStatus.changeSocial(-stepAmount);
            break;
          case SLEEP:
            healthStatus.changeSleep(largeChange);
            break;
          case LOUDTOY:
            healthStatus.changeSocial(-mediumChange);
            break;
          case QUIETTOY:
            healthStatus.changeSocial(largeChange);
            break;
        }
      }
      step();
      if (healthStatus.getHunger() == 0 || healthStatus.getHygiene() == 0 ||
          healthStatus.getSocial() == 0 || healthStatus.getSleep() == 0) {
        sendToNewHome();
      }
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public HealthStatus getHealth() {
    return healthStatus;
  }

  @Override
  public boolean isAlive() {
    return this.isAlive;
  }

  @Override
  public boolean sendToNewHome() {
    return !isAlive;
  }

  @Override
  public MoodEnum getMood() {
    return this.mood;
  }

  @Override
  public void setMood(MoodEnum mood) {
    this.mood = mood;
  }
}
