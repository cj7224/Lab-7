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
        return null;
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
            successor.left = x.left;
            successor.right = x.right;
            x = successor;
            delete(min(x.right).key);
        }
        return x;
    }

    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();
        System.out.println(bst.isEmpty());
        System.out.println(bst.size());
        bst.put(3, "a");
        bst.put(1, "b");
        bst.put(2, "c");
        bst.put(4, "d");
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
    }
}
