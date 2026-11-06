public class BST<Key extends Comparable<Key>, Value> {
    private Node root;
    private int N;
    private class Node {
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }
    public BST() {
        root = null;
        N = 0;
    }

    public Value get(Key key) {
        return get(root,key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) {
            N++;
            return new Node(key, val);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else if (cmp == 0) x.val = val;
        return x;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return N;
    }

    public BST treeify(Key[] keys) {
        BST<Key, Integer> bst = new BST<>();
        for (Key key : keys) {
            bst.put(key, 0);
        }
        return bst;
    }

    public Node min() {
        return min(root);
    }

    private Node min(Node x) {
        if (x.left != null) return min(x.left);
        else return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.left == null) {
                N--;
                return x.right;
            }
            if (x.right == null) {
                N--;
                return x.left;
            }
            Node successor = min(x.right);
            x.key = successor.key;
            x.val = successor.val;
            x.right = delete(x.right, successor.key);
        }
        return x;
    }

    public void printKeys() {
        printKeys(root);
    }

    private void printKeys(Node x) {
        if (x == null) return;
        printKeys(x.left);
        System.out.println(x.key);
        printKeys(x.right);
    }

    public void balance() {
        Node[] nodes = new BST.Node[N];
        storeNodes(root, nodes, 0);
        root = balance(nodes, 0, N - 1);
    }

    private int storeNodes(Node x, Node[] nodes, int i) {
        if (x == null) return i;

        i = storeNodes(x.left, nodes, i);
        nodes[i] = x;
        i++;
        return storeNodes(x.right, nodes, i);
    }

    private Node balance(Node[] nodes, int low, int high) {
        if (low > high) return null;

        int mid = low + (high - low) / 2;
        Node x = nodes[mid];
        x.left = balance(nodes, low, mid - 1);
        x.right = balance(nodes, mid + 1, high);
        return x;
    }

    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();
        System.out.println(bst.isEmpty());
        System.out.println(bst.size());
        bst.put(3, "a");
        bst.put(1, "b");
        bst.put(2, "c");
        bst.put(5, "e");
        System.out.println(bst.size());
        System.out.println(bst.contains(1));
        System.out.println(bst.contains(2));
        System.out.println(bst.isEmpty());
        System.out.println(bst.get(3));
        System.out.println(bst.get(2));
        System.out.println(bst.contains(5));
        bst.delete(3);
        System.out.println(bst.contains(3));
        System.out.println(bst.size());
        Integer[] keys = new Integer[5];
        for (int i = 0; i < keys.length; i++) {
            keys[i] = i;
        }
        System.out.println(bst.treeify(keys));
        bst.printKeys();
        bst.balance();
    }
}
