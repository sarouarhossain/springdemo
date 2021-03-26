import java.io.FileNotFoundException;

public class Test {
  public static void main(String[] args) {
    //
    try {
      Integer x = 1 / 0;
      System.out.println(1);
    } catch (ArithmeticException fne) {
      fne.printStackTrace();
    } catch (Exception e2) {
      e2.printStackTrace();
    } finally {
      System.out.println(2);
      System.out.println(3);
    }
  }
}
