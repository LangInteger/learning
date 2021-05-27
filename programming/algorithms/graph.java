package io.jpower.kcp.example;

import java.util.ArrayList;
import java.util.List;

public class Test {
}

class GraphTraversal {
  ListGraph graph;
  boolean[] visited;

  public GraphTraversal(ListGraph listGraph) {
    this.graph = listGraph;
    visited = new boolean[listGraph.graphs.size()];
  }

  //--------------------------BFS-------------------------
  public void BFS() {
    for (int i = 0; i < graph.graphs.size(); i++) {
      if (!visited[i]) {
        BFSTraversal(i);
      }
    }
  }

  private void BFSTraversal(int current) {
    List<Integer> prev = new ArrayList<>();
    List<Integer> next = new ArrayList<>();

    prev.add(current);

    while (!prev.isEmpty()) {
      for (int nodeIndex : prev) {
        visited[nodeIndex] = true;
        for (int neighbor : graph.graphs.get(current)) {
          if (!visited[neighbor]) {
            next.add(neighbor);
          }
        }
      }

      prev = next;
      next = new ArrayList<>();
    }

  }

  //--------------------------DFS-------------------------

  public void DFS() {
    for (int i = 0; i < graph.graphs.size(); i++) {
      if (!visited[i]) {
        DFSTraversal(i);
      }
    }
  }

  private void DFSTraversal(int current) {
    if (visited[current]) {
      return;
    }

    visited[current] = true;

    List<Integer> neighbors = graph.graphs.get(current);
    for (Integer nextNode : neighbors) {
      if (!visited[nextNode]) {
        DFSTraversal(nextNode);
      }
    }
  }
}

class ListGraph {
  List<List<Integer>> graphs;

  public ListGraph(int v) {
    graphs = new ArrayList<>(v);
    for (int i = 0; i < v; i++) {
      graphs.add(new ArrayList<>());
    }
  }

  public void addEdge(int start, int end) {
    graphs.get(start).add(end);
  }
}

//-----------------判断一棵树是否为对称的-------------------

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode() {
  }

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

class Solution {
  public boolean isSymmetric(TreeNode root) {

    List<TreeNode> prev = new ArrayList<>();
    List<TreeNode> next = new ArrayList<>();

    prev.add(root);

    while (!prev.isEmpty()) {
      for (TreeNode node : prev) {
        if (node != null) {
          next.add(node.left);
          next.add(node.right);
        }
      }

      if (!isSymmetric(next)) {
        return false;
      } else {
        prev = next;
        next = new ArrayList<>();
      }
    }
    return true;
  }

  private boolean isSymmetric(List<TreeNode> next) {
    int head = 0;
    int last = next.size() - 1;
    while (head < last) {
      if (next.get(head) == null && next.get(last) == null) {
        head++;
        last--;
        continue;
      }

      if (next.get(head) == null || next.get(last) == null) {
        return false;
      }

      if (next.get(head).val != next.get(last).val) {
        return false;
      }

      head++;
      last--;
    }
    return true;
  }
}
