/**
 * Created by alexgomez on 12/17/15.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public class BFSNode {
  public String name;
  public int value = 0;
  public HashSet<String> items = new HashSet<>();
  public HashSet<String> itemsAcquired = new HashSet<>();
  public BFSNode parent = null;
  public ArrayList<BFSNode> children = new ArrayList<>();
  public double highestRatioTo = 0.0;
  public ArrayList<Double> weights = new ArrayList<>();
  static ArrayList<BFSNode> ALLNODES = new ArrayList<>();
  public double milesVisited = 0.0;
  static String[] itemList;

  BFSNode(String name, Set<String> itemList) {
    System.out.print(name + " has the toys: ");
    for (String item : itemList) {
      System.out.print(item + " ");
      this.items.add(item);
      this.itemsAcquired.add(item);
    }
    System.out.println();
    this.name = name;
    ALLNODES.add(this);
  }

  public void addChild(BFSNode child, double weight) {
    this.weights.add(weight);
    this.children.add(child);
    System.out.println(this.name + " is connected to " + child.name + " with weight " + weight);
  }


  static void bfs(BFSNode root) {
    Queue<BFSNode> q = new LinkedList<>();
    q.add(root);
    //ArrayList<BFSNode> visited;
    int x = 0;
    while (!q.isEmpty()) {
      BFSNode current = q.remove();
      System.out.println("you removed " + current.name);
      for (int i = 0; i < current.children.size(); i++) {
        BFSNode n = current.children.get(i);
        //Union of 2 item lists
        Set<String> temp = new HashSet<>(n.items); // use the copy constructor
        temp.removeAll(current.items);
        //print out all items now acquired
        for (String s : temp) {
          System.out.print(s + " ");
        }

        System.out.print("size is " + temp.size());
        System.out.println();
        double ratio = (temp.size()) / current.weights.get(i);

        System.out.println("ratio is : " + ratio);
        //if this is the root
        if (x == 0) {
          n.highestRatioTo = ratio;
          HashSet<String> temp2 = new HashSet<>(current.itemsAcquired);
          temp2.addAll(n.items);
          n.itemsAcquired = temp2;
          n.parent = current;
          n.milesVisited += current.weights.get(i);
        } else if ((temp.size() + current.highestRatioTo * current.milesVisited) / (current.milesVisited +
                current.weights.get(i)) > n.highestRatioTo) {
          n.highestRatioTo = (temp.size() + current.highestRatioTo *
                  current.milesVisited) / (current.milesVisited +
                  current.weights.get(i));
          n.milesVisited = current.milesVisited +
                  current.weights.get(i);
          HashSet<String> temp2 = new HashSet<>(current.itemsAcquired);
          temp2.addAll(n.items);
          n.itemsAcquired = temp2;
          n.parent = current;
        }
        q.add(n);

      }
      x++;

    }
  }

  static Set<String> randomItems(String toys[]) {
    HashSet<String> randToys = new HashSet<>();
    Random r = new Random();
    for (String toy : toys) {
      if (r.nextBoolean()) {
        randToys.add(toy);
      }
    }
    return randToys;
  }

  static void getBestTrip() {
    double value = 0.0;
    BFSNode bestNode = ALLNODES.get(0);
    for (BFSNode node : ALLNODES) {
      if (value < node.highestRatioTo && node.itemsAcquired.size() == itemList.length) {
        bestNode = node;
        value = node.highestRatioTo;
      }
    }

    BFSNode temp = bestNode;

    System.out.print("You acquired " + bestNode.itemsAcquired.size() +
            " items from the best route: ");
    while (temp.parent != null) {
      System.out.print(temp.name + " from " + temp.parent.name + " ");
      temp = temp.parent;
    }
    System.out.print("And you averaged " + bestNode.highestRatioTo + " items per mile.");

  }


}

