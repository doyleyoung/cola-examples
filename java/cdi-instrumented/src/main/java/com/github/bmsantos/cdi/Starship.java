package com.github.bmsantos.cdi;

import javax.inject.Inject;

public class Starship {

  @Inject
  private WarpDrive engine;

  public void start() {
    engine.start();
  }

  public boolean isRunning() {
    return engine.isRunning();
  }
}