package stepshelpers;

import java.util.HashMap;
import java.util.Map;

public class Memory {

  private static final Map<String, Object> map = new HashMap<>();
  private static Memory INSTANCE;

  private Memory() {
  }

  public static void getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new Memory();
    }
  }

  public static void put(String key, Object value) {
    map.put(key, value);
  }

  public static Object get(String key) {
    return map.get(key);
  }

  public static void clear() {
    map.clear();
  }

}
