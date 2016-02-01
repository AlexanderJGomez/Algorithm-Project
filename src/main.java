import java.util.HashSet;
import java.util.Random;

/**
 * Created by alexgomez on 12/17/15.
 */
class main {

  public static void main(String[] args) {
    BFSNode root = new BFSNode("Home", new HashSet<>());
    String toyList[] = {"car", "lego", "actionfigure", "watergun", "gamecube", "xbox", "ps4"};
    BFSNode.itemList = toyList;
    BFSNode toysRus = new BFSNode("Toys R US", BFSNode.randomItems(toyList));
    BFSNode target = new BFSNode("Target", BFSNode.randomItems(toyList));
    BFSNode peters = new BFSNode("Peter's Toys", BFSNode.randomItems(toyList));
    BFSNode toyShop = new BFSNode("Toy Shop", BFSNode.randomItems(toyList));
    BFSNode gamestop = new BFSNode("GameStop", BFSNode.randomItems(toyList));
    BFSNode marshalls = new BFSNode("Marshall's", BFSNode.randomItems(toyList));
    BFSNode hobbyShop = new BFSNode("Hobby Shop", BFSNode.randomItems(toyList));
    Random r = new Random();
    root.addChild(toysRus, r.nextInt(3) + 1);
    root.addChild(target, r.nextInt(3) + 1);
    toysRus.addChild(peters, r.nextInt(3) + 1);
    toysRus.addChild(toyShop, r.nextInt(3) + 1);
    target.addChild(gamestop, r.nextInt(3) + 1);
    peters.addChild(toyShop, r.nextInt(3) + 1);
    target.addChild(toyShop, r.nextInt(3) + 1);
    peters.addChild(marshalls, r.nextInt(3) + 1);
    toyShop.addChild(hobbyShop, r.nextInt(3) + 1);
    gamestop.addChild(marshalls, r.nextInt(3) + 1);
    gamestop.addChild(hobbyShop, r.nextInt(3) + 1);
    BFSNode.bfs(root);
    BFSNode.getBestTrip();


  }

}

//1 Breadth-First-Search(Graph, root):
//        2
//        3     for each node n in Graph:
//        4         n.distance = INFINITY  n.value = 0; n.items = set{given items}
//        5         n.parent = x; n.edges = {list of edges to}; n.itemsAcquired = list(empt
//        n.highestRatioTo = 0.0
//        6
//        7     create empty queue Q
//        8
//        9     root.distance = 0
//        10     Q.enqueue(root)
//        11
//        12     while Q is not empty:
//        13
//        14         current = Q.dequeue()
//        15
//        16         for each node n that is adjacent to current:
//        17             int ratio = (this.itemsAcquired.intersectionWith(n.items).size) / edgeWeight for Curr to n)
//        18
//        19                 n.parent = current
//        if(ratio  > n.highestRatioTo) {
//        n.itemsAcquired = current.items.union(n.items)
//        n.highestRatioTo = ratio;
//
//        }
//        20                 Q.enqueue(n)
//        21  Last, topologically sort directed graph

