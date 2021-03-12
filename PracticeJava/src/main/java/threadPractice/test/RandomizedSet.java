package threadPractice.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomizedSet {
  List<Integer> list;
  private Random randomGenerator;
  /** Initialize your data structure here. */
  public RandomizedSet() {
    list = new ArrayList<>();
    randomGenerator = new Random();
  }

  /**
   * Inserts a value to the set. Returns true if the set did not already contain the specified
   * element.
   */
  public boolean insert(int val) {
    return list.add(val);
  }

  /** Removes a value from the set. Returns true if the set contained the specified element. */
  public boolean remove(int val) {
    var contains = list.contains(val);
    Integer data = val;
    list.remove(data);
    return contains;
  }

  /** Get a random element from the set. */
  public int getRandom() {
    Integer index = randomGenerator.nextInt(list.size());
    return list.get(index);
  }
}
