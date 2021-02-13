package com.dreamlock.desktop.menu;

public enum MenuChoices {
  NEW_GAME {
    public IMenuChoice getMenuChoice() {
      return new StartNewGameChoice();
    }
  },
  LOAD_GAME {
    public IMenuChoice getMenuChoice() {
      return new StartLoadedGameChoice();
    }
  },
  EXIT {
    public IMenuChoice getMenuChoice() {
      return new EmptyChoice();
    }
  };

  public abstract IMenuChoice getMenuChoice();
}
