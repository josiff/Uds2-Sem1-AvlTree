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
public class AvlTree {

    private Node root;

    public void insert(Node paNode) {
        boolean flag = false;
        if (root == null) {
            root = paNode;
            return;
        }
        Node node = root;

        while (flag == false) {

            if (node.compare(paNode) > 0) {
                /*do prava*/
                if (node.getRight() == null) {
                    node.setRight(paNode);
                    flag = true;
                    return;
                };
                node = node.getRight();

            } else {
                /*do lava*/
                if (node.getLeft() == null) {
                    node.setLeft(paNode);
                    flag = true;
                    return;
                };
                node = node.getLeft();
            }
        }

        fixTree(paNode);

    }

    private void fixTree(Node paNode) {

        while (paNode != root) {
            paNode = balanceTree(paNode);
        }

        setHeight(paNode);

    }

    private Node balanceTree(Node paNode) {
        int diff = leftHeight(paNode) - rightHeight(paNode);
        if (diff > 1) {
            if (leftHeight(paNode.getLeft()) >= rightHeight(paNode.getLeft())) {
                return rightRotation(paNode); /*LL*/

            } else {
                paNode.setLeft(leftRotation(paNode.getLeft()));
                return rightRotation(paNode); /*LR*/

            }

        }

        if (diff < -1) {

            if (rightHeight(paNode.getRight()) >= leftHeight(paNode.getRight())) {
                return leftRotation(paNode); /*RR*/

            } else {
                paNode.setRight(rightRotation(paNode.getRight()));
                return leftRotation(paNode); /*LR*/

            }

        }

        setHeight(paNode);
        return paNode.getParent();
    }

    /**
     * Rotujem z lavej stradny do pravej
     *
     * @param paNode uzol kde je h > 1
     */
    private Node rightRotation(Node paNode) {
        Node node = paNode.getLeft();
        paNode.setLeft(node.getRight());
        setParent(paNode.getLeft(), paNode);
        node.setRight(paNode);
        setParent(node, paNode.getParent());
        setParent(paNode, node);
        setHeight(paNode);
        setHeight(node);

        return node;

    }

    /**
     * Rotujem z pravej strany do lavej
     *
     * @param paNode uzol kde je h > 1
     */
    private Node leftRotation(Node paNode) {
        Node node = paNode.getRight();
        paNode.setRight(node.getLeft());
        setParent(paNode.getRight(), paNode);
        node.setLeft(paNode);
        setParent(node, paNode.getParent());
        setParent(paNode, node);
        setHeight(paNode);
        setHeight(node);
        return node;

    }

    /**
     * Vrati vysku laveho syna
     *
     * @param paNode
     * @return
     */
    private int leftHeight(Node paNode) {
        return paNode.getLeft() == null ? 0 : paNode.getLeft().getHeight();
    }

    /**
     * Vrati vysku laveho syna
     *
     * @param paNode
     * @return
     */
    private int rightHeight(Node paNode) {
        return paNode.getRight() == null ? 0 : paNode.getRight().getHeight();
    }

    /**
     * Nastavenie urovne/vysky uzla
     *
     * @param paNode
     */
    private void setHeight(Node paNode) {
        int height = Math.max(rightHeight(paNode), leftHeight(paNode));
        paNode.setHeight(height);
    }

    /**
     * Nastavenie parenta ak existuje syn
     *
     * @param soon
     * @param parent
     */
    private void setParent(Node soon, Node parent) {
        if (soon != null) {
            soon.setParent(parent);
        }
    }

}
