package threadPractice.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo7 {
  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  static class Index {
    int i = 0;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    TreeNode rl = new TreeNode(2);
    TreeNode rr = new TreeNode(3);
    root.left = rl;
    root.right = rr;
    TreeNode rrl = new TreeNode(4);
    TreeNode rrr = new TreeNode(5);
    rr.right = rrr;
    rr.left = rrl;
    preOrder(root);
    String st = serialize(root);
    System.out.println(serialize(root));

    TreeNode x = deserialize(st);
    preOrder(x);

    List<Integer> list = new ArrayList<>();
    list.add(1);
  }

  public static TreeNode deserialize(String data) {
    Index in = new Index();
    String[] st = data.split(" ");
    if (st.length == 0) return null;
    return deserialize(st, in);
  }

  static TreeNode deserialize(String[] st, Index index) {
    if (st[index.i].equals("N")) {
      index.i++;
      return null;
    }

    TreeNode root = new TreeNode(Integer.parseInt(st[index.i++]));
    root.left = deserialize(st, index);
    root.right = deserialize(st, index);

    return root;
  }

  public static String serialize(TreeNode root) {
    StringBuffer sb = new StringBuffer();
    serialize(root, sb);
    return sb.toString();
  }

  static void serialize(TreeNode root, StringBuffer sb) {
    if (root == null) {
      sb.append("N ");
      return;
    }
    sb.append(root.val + " ");
    serialize(root.left, sb);
    serialize(root.right, sb);
  }

  static void preOrder(TreeNode root) {
    if (root == null) return;
    System.out.println(root.val);
    preOrder(root.left);
    preOrder(root.right);
  }
}
