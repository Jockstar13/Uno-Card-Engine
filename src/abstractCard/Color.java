package abstractCard;

public enum Color {
  BLUE, GREEN, RED, YELLOW;

  public static iterator<Color> getIterator(){
    return new themeColorIterator();
  }
  private static class themeColorIterator implements iterator<Color>{
    private int pos=0;
    @Override
    public Color next() {
      return Color.values()[pos++];
    }

    @Override
    public boolean hasNext() {
      return pos<Color.values().length;
    }
  }
}

