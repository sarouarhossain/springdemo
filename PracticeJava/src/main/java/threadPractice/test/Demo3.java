package threadPractice.test;

public class Demo3 {
  public static void main(String[] args) {
    //
    String str = "42";
    System.out.println(calculate(str));
  }

  public static int calculate(String s) {
    s = s.replaceAll(" ", "");
    // "/" check
    StringBuilder ns = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '/') {
        var x1 = s.charAt(i - 1) - '0';
        var x2 = s.charAt(i + 1) - '0';
        var x3 = x1 / x2;
        i++;
        ns.deleteCharAt(ns.length() - 1);
        ns.append(x3);
      } else {
        ns.append(s.charAt(i));
      }
    }
    s = ns.toString();

    ns = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '*') {
        var x1 = s.charAt(i - 1) - '0';
        var x2 = s.charAt(i + 1) - '0';
        var x3 = x1 * x2;
        i++;
        ns.deleteCharAt(ns.length() - 1);
        ns.append(x3);
      } else {
        ns.append(s.charAt(i));
      }
    }
    s = ns.toString();

    int pos = 0;
    int neg = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '+') {
        var x2 = s.charAt(i + 1) - '0';
        i++;
        pos += x2;
      } else if (s.charAt(i) == '-') {
        var x2 = s.charAt(i + 1) - '0';
        i++;
        neg += x2;
      } else {
        pos = pos * 10 + s.charAt(i) - '0';
      }
    }

    return pos - neg;
  }
}
