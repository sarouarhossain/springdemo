package threadPractice.test;

public class Demo4 {
  public static void main(String[] args) {
    //
    int res = 0;
    for (int i = 1; i <= 30000; i++) {
      if (i % 3 == 0) {
        res += i;
      }
    }
    System.out.println("Res one: " + res);

    int res1 = 0;
    for (int i = 3; i <= 30000; i += 3) {
      res1 += i;
    }
    System.out.println("Res two: " + res1);

    int res2 = 3 * ((10000 * 10001) / 2);
    System.out.println("Res three: " + res2);
  }
}
