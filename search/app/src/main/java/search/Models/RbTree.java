package search.Models;

import java.util.ArrayList;
import java.util.List;
import search.Utils.GameGimmick;


public class RbTree {
    private Node root;
    private Node TNULL;

    public RbTree() {
        TNULL = new Node(null, null);
        TNULL.setRed(false);
        TNULL.setLeft(TNULL);
        TNULL.setRight(TNULL);
        root = TNULL;
    }

    private void fixInsert(Node node) {
        Node uncle;
        while (node.getParent() != null && node.getParent().isRed()) {
            if (node.getParent() == node.getParent().getParent().getLeft()) {
                uncle = node.getParent().getParent().getRight();
                if (uncle.isRed()) {
                    uncle.setRed(false);
                    node.getParent().setRed(false);
                    node.getParent().getParent().setRed(true);
                    node = node.getParent().getParent();
                } else {
                    if (node == node.getParent().getRight()) {
                        node = node.getParent();
                        leftRotate(node);
                    }
                    node.getParent().setRed(false);
                    node.getParent().getParent().setRed(true);
                    rightRotate(node.getParent().getParent());
                }
            } else {
                uncle = node.getParent().getParent().getLeft();
                if (uncle.isRed()) {
                    uncle.setRed(false);
                    node.getParent().setRed(false);
                    node.getParent().getParent().setRed(true);
                    node = node.getParent().getParent();
                } else {
                    if (node == node.getParent().getLeft()) {
                        node = node.getParent();
                        rightRotate(node);
                    }
                    node.getParent().setRed(false);
                    node.getParent().getParent().setRed(true);
                    leftRotate(node.getParent().getParent());
                }
            }
            if (node == root) {
                break;
            }
        }
        root.setRed(false);
    }

    public void insert(String key, String value) {
        Node node = new Node(key, value);
        node.setParent(null);
        node.setKey(key);
        node.setLeft(TNULL);
        node.setRight(TNULL);
        node.setRed(true);
        node.setValue(value);
    
        Node y = null;
        Node x = root;
    
        while (x != TNULL) {
            y = x;
            int comparison = node.getKey().compareTo(x.getKey());
            if (comparison < 0) {
                x = x.getLeft();
            } else if (comparison > 0) {
                x = x.getRight();
            } else {
                // Perbarui nilai jika kunci sudah ada
                x.setValue(value);
                return;
            }
        }
    
        node.setParent(y);
        if (y == null) {
            root = node;
        } else {
            int comparison = node.getKey().compareTo(y.getKey());
            if (comparison < 0) {
                y.setLeft(node);
            } else {
                y.setRight(node);
            }
        }
        fixInsert(node);
    }
    
    public void insert(String key, String value, GameGimmick gimmick) {
        Node node = new Node(key, value);
        node.setParent(null);
        node.setKey(key);
        node.setLeft(TNULL);
        node.setRight(TNULL);
        node.setRed(true);
        node.setValue(value);
       
        if (gimmick != null) {
            node.gimmick = gimmick;
        }
    
        Node y = null;
        Node x = root;
    
        while (x != TNULL) {
            y = x;
            int comparison = node.getKey().compareTo(x.getKey());
            if (comparison < 0) {
                x = x.getLeft();
            } else if (comparison > 0) {
                x = x.getRight();
            } else {
                // Perbarui nilai dan gimmick jika kunci sudah ada
                x.setValue(value);
                if (gimmick != null) {
                    x.gimmick = gimmick;
                }
                return;
            }
        }
    
        node.setParent(y);
        if (y == null) {
            root = node;
        } else {
            int comparison = node.getKey().compareTo(y.getKey());
            if (comparison < 0) {
                y.setLeft(node);
            } else {
                y.setRight(node);
            }
        }
        fixInsert(node);
    }

    private void leftRotate(Node x) {
        Node y = x.getRight();
        x.setRight(y.getLeft());
        if (y.getLeft() != TNULL) {
            y.getLeft().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            root = y;
        } else if (x == x.getParent().getLeft()) {
            x.getParent().setLeft(y);
        } else {
            x.getParent().setRight(y);
        }
        y.setLeft(x);
        x.setParent(y);
    }

    private void rightRotate(Node y) {
        Node x = y.getLeft();
        y.setLeft(x.getRight());
        if (x.getRight() != TNULL) {
            x.getRight().setParent(y);
        }
        x.setParent(y.getParent());
        if (y.getParent() == null) {
            root = x;
        } else if (y == y.getParent().getLeft()) {
            y.getParent().setLeft(x);
        } else {
            y.getParent().setRight(x);
        }
        x.setRight(y);
        y.setParent(x);
    }

    public List<Node> search(String key) {
        List<Node> result = searchHelper(this.root, key);
        return result;
    }
    
    private List<Node> searchHelper(Node node, String key) {
        List<Node> result = new ArrayList<>();
        
        if (node == TNULL) {
            return result;
        }
        
        String nodeKey = node.getKey().toString().toLowerCase();
        String searchKey = key.toLowerCase();
        
        if (nodeKey.equals(searchKey)) {
            result.add(node);
        }
        else if (nodeKey.contains(searchKey)) {
            result.add(node);
        }
        
        result.addAll(searchHelper(node.getLeft(), key));
        result.addAll(searchHelper(node.getRight(), key));
        
        return result;
    }

    public List<Node> searchByKey(String key) {
        List<Node> result = searchHelperByKey(this.root, key);
        return result;
    }

    private List<Node> searchHelperByKey(Node node, String key) {
        List<Node> result = new ArrayList<>();
        
        if (node == TNULL) {
            return result;
        }
        
        String nodeKey = node.getKey().toString().toLowerCase();
        String searchKey = key.toLowerCase();
        
        if (nodeKey.contains(searchKey)) {
            result.add(node);
        }
        
        result.addAll(searchHelper(node.getLeft(), key));
        result.addAll(searchHelper(node.getRight(), key));
        
        return result;
    }

    public List<Node> searchByValue(String value) {
        List<Node> result = searchHelperByValue(this.root, value);
        return result;
    }

    private List<Node> searchHelperByValue(Node node, String value) {
        List<Node> result = new ArrayList<>();
        
        if (node == TNULL) {
            return result;
        }
        
        String nodeKey = node.getKey().toString().toLowerCase();
        String searchValue = value.toLowerCase();
        
        if (nodeKey.contains(searchValue)) {
            result.add(node);
        }
        
        result.addAll(searchHelper(node.getLeft(), value));
        result.addAll(searchHelper(node.getRight(), value));
        
        return result;
    }
}