package com.github.bmsantos.cdi;

public class WarpDrive implements Engine {
  private boolean isRunning = false;

  public void start() {
    isRunning = true;
  }

  public boolean isRunning() {
    return isRunning;
  }
}
