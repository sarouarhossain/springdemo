package threadPractice.test;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFinder {
  /** initialize your data structure here. */
  PriorityQueue<Integer> pqUpper;

  PriorityQueue<Integer> pqLower;

  public MedianFinder() {
    pqUpper = new PriorityQueue<>();
    pqLower = new PriorityQueue<>(Comparator.reverseOrder());
  }

  public void addNum(int num) {
    if (pqLower.isEmpty() || pqLower.peek() >= num) {
      pqLower.add(num);
    } else {
      pqUpper.add(num);
    }

    if (pqLower.size() > pqUpper.size() + 1) {
      pqUpper.add(pqLower.poll());
    } else if (pqLower.size() < pqUpper.size()) {
      pqLower.add(pqUpper.poll());
    }
  }

  public double findMedian() {
    if (pqLower.size() == pqUpper.size()) {
      return (pqLower.peek() + pqUpper.peek()) / 2d;
    } else {
      return pqLower.peek();
    }
  }

  public static void main(String[] args) {
    //
    PriorityQueue<Integer> pq = new PriorityQueue<>((n1, n2) -> n2 - n1);
    pq.add(2);
    pq.add(3);
    pq.add(1);

    System.out.println(pq.peek());
    System.out.println(pq);
  }
}
