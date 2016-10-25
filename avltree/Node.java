/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avltree;

/**
 *
 * @author Jožko
 */
public class Node {

    private Node left, right, parent;
    private int height;
    private INode data;

    public Node() {
        this.height = 0;
    }
    
    public Node(INode paNode) {
        this.height = 0;
        this.data = paNode;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isLeft() {
        if (this.getParent() == null) {
            return false;
        }

        return this.getParent().getLeft() == this;
    }

    public boolean isRight() {
        if (this.getParent() == null) {
            return false;
        }

        return this.getParent().getRight() == this;
    }

    /**
     * Najde nahradnika ako najpravejsieho laveho syna
     *
     * @param paNode
     * @return nahradnik, inak null
     */
    public Node getNahradnik() {
        if (this.getLeft() != null) {

            Node node = ((Node) this.getLeft());

            while (node.getRight() != null) {//todo
                node = (Node) node.getRight();
            }
            return node;

        } else {
            return null;
        }

    }
/*
    protected int compare(Node paNode) {

        return 0;
    }

    protected void setData(Node paNode) {
    }*/

    @Override
    public String toString() {
        String txt = this.parent == null ? "root " : "";
        return    txt +   "Vyska: " + this.height;

    }

    public INode getData() {
        return data;
    }

    public void setData(INode data) {
        this.data = data;
    }
    
    
    

}
