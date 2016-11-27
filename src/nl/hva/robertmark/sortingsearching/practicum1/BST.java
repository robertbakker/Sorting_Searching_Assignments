package nl.hva.robertmark.sortingsearching.practicum1;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Robert
 */
public class BST<Key extends Comparable<Key>, Value> {

    private Node root;               // root of BST

    private class Node {

        private Key key;              // key
        private Value val;            // associated value
        private Node left, right;     // links to subtrees
        private int N;                // # nodes in subtree rooted here

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.N;
        }
    }

    public List<Value> get(Key key) {
        List<Value> list = new ArrayList<Value>();

        Node i = get(root, key);
        if (i != null) {
            list.add(i.val);
        }
        while (i != null) {
            i = get(i.left, key);
            if (i != null) {
                list.add(i.val);
            }
        }

        return list;
    }

    private Node get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            return get(x.left, key);
        } else if (key.compareTo(x.key) > 0) {
            return get(x.right, key);
        } else {
            return x;
        }
    }

//    public Value get(Key key) {
//        return get(root, key);
//    }
//
//    private Value get(Node x, Key key) {
//        if (x == null) {
//            return null;
//        }
//        int cmp = key.compareTo(x.key);
//        if (cmp < 0) {
//            return get(x.left, key);
//        } else if (cmp > 0) {
//            return get(x.right, key);
//        } else {
//            return x.val;
//        }
//    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) {
            return new Node(key, val, 1);
        }
        if (key.compareTo(x.key) < 0) {
            x.left = put(x.left, key, val);
        } else if (key.compareTo(x.key) > 0) {
            x.right = put(x.right, key, val);
        } else {
            // Met duplicaten voeg toe aan de linkerzijde
            x.left = put(x.left, key, val);
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null) {
            return 0;
        }

        if (key.compareTo(x.key) < 0) {
            return rank(key, x.left);
        } else if (key.compareTo(x.key) > 0) {
            return 1 + size(x.left) + rank(key, x.right);
        } else {
            return size(x.left);
        }
    }
}
