/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avltree;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

/**
 *
 * @author Jožko
 */
public class AvlTree {

    private Node root;
    private int count;

    public boolean insert(Node paNode) {
        boolean flag = false;
        boolean result = true;
        if (root == null) {
            root = paNode;
            flag = true;
        }
        Node node = root;

        int cis = 0;

        while (flag == false) {
            cis = ((INode) paNode.getData()).compare(node.getData());

            if (cis == 0) {
                /*todo treba potom dodatocne upravit*/
                flag = true;
                result = false;
            } else if (cis > 0) {
                /*do prava*/
                if (node.getRight() == null) {
                    node.setRight(paNode);
                    setParent(paNode, node);
                    flag = true;

                } else {
                    node = node.getRight();
                }

            } else {
                /*do lava*/
                if (node.getLeft() == null) {
                    node.setLeft(paNode);
                    setParent(paNode, node);
                    flag = true;

                } else {
                    node = node.getLeft();
                }
            }
        }

        if (result) {
            count++;
            fixTree(paNode);

        }

        return result;

    }

    /**
     * Mazanie nodu
     *
     * @param paNode
     * @return
     */
    public boolean remove(Node paNode) {

        paNode = findNode(paNode);

        if (paNode == null) {
            return false;
        }
        Node node = null;

        count--;
        //ak ma oboch potomkov najdem najpravejsieho z laveho syna a idem mazat jeho
        if (paNode.getLeft() != null && paNode.getRight() != null) {

            //najpravejsi z jeho laveho syna  
            node = paNode.getNahradnik();

            paNode.setData(node.getData());
            paNode = node;
        }

        //ak by tu bol nahradnik tak otestujem ci este nema levy sub strom
        //inak ma len jedneho syna tomu nastavim referncie
        node = paNode.getLeft() != null ? paNode.getLeft() : paNode.getRight();

        if (node != null) {
            //ak mazem root
            if (paNode.getParent() == null) {

                node.setParent(null);
                root = node;
                fixTree(root);
                return true;

            }

            if (paNode.isLeft()) {
                paNode.getParent().setLeft(node);

            } else {

                paNode.getParent().setRight(node);

            }
            setParent(node, paNode.getParent());
            fixTree(node);

        } else if (paNode.getParent() == null) {

            // prazdny storm, vymazavam root
            root = null;

        } else {

            //ak je list
            if (paNode.isLeft()) {
                paNode.getParent().setLeft(null);
            } else {
                paNode.getParent().setRight(null);
            }

            fixTree(paNode.getParent());

        }

        return true;

    }

    private void fixTree(Node paNode) {

        while (paNode != null) {

            paNode = balanceTree(paNode);
            // System.out.println(paNode);

        }
        /*setHeight(paNode);
         System.out.println(paNode);
         System.out.println("---------");*/

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

        //ak som prehadzoval koren treba upravit aj ref.
        if (node.getParent() == null) {
            root = node;
        } else {
            if (paNode.isLeft()) {
                node.getParent().setLeft(node);
            } else {
                node.getParent().setRight(node);

            }
        }
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
        setParent(paNode.getRight(), paNode);//chyba
        node.setLeft(paNode);
        setParent(node, paNode.getParent());

        //ak som prehadzoval koren treba upravit aj ref.
        if (node.getParent() == null) {
            root = node;
        } else {
            if (paNode.isLeft()) {
                node.getParent().setLeft(node);
            } else {
                node.getParent().setRight(node);
            }
        }
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
    public int leftHeight(Node paNode) {
        return paNode.getLeft() == null ? 0 : paNode.getLeft().getHeight();
    }

    /**
     * Vrati vysku laveho syna
     *
     * @param paNode
     * @return
     */
    public int rightHeight(Node paNode) {
        return paNode.getRight() == null ? 0 : paNode.getRight().getHeight();
    }

    /**
     * Nastavenie urovne/vysky uzla
     *
     * @param paNode
     */
    private void setHeight(Node paNode) {
        int height = Math.max(rightHeight(paNode), leftHeight(paNode)) + 1;
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

    public Node findNode(Node paNode) {
        Node node = root;
        if (node != null) {

            boolean flag = false;
            int cis = 0;
            while (flag == false) {

                cis = ((INode) paNode.getData()).compare(node.getData());

                if (cis == 0) {
                    flag = true;
                } else if (cis < 0) {
                    if (node.getLeft() == null) {
                        flag = true;
                        node = null;

                    } else {
                        node = node.getLeft();
                    }
                } else if (cis > 0) {
                    if (node.getRight() == null) {
                        flag = true;
                        node = null;

                    } else {
                        node = node.getRight();
                    }
                }
            }
        }

        return node;
    }

    /**
     * InOrder prehliadka
     *
     * @param root
     */
    public ArrayList inOrder(Node root) {
        ArrayList<INode> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        int count = 0;
        Stack<Node> stack = new Stack<Node>();

        while (!stack.isEmpty() || root != null) {

            if (root != null) {
                stack.push(root);
                root = root.getLeft();
            } else {
                Node n = stack.pop();
                list.add(n.getData());
                count++;
                //System.out.printf("%s, %n", n.toString());
                root = n.getRight();
            }

        }
        return list;

    }

    /**
     * InOrder prehliadka ak nenajde vrati nasledujuce vrcholy
     *
     * @param paNode
     * @param pocet - kolko nasledujucich ma pridat
     * @return
     */
    public ArrayList inOrderMatch(Node paNode, int pocet) {
        ArrayList<INode> list = new ArrayList<>();

        Node node = root;
        if (node != null) {

            boolean flag = false;
            int cis = 0;
            cis = ((INode) paNode.getData()).compare(node.getData());
            while (flag == false) {

                if (cis == 0) {
                    flag = true;
                    list.add(node.getData());
                    return list;

                } else if (cis < 0) {
                    if (node.getLeft() == null) {
                        flag = true;
                    } else {
                        node = node.getLeft();
                    }
                } else if (cis > 0) {
                    if (node.getRight() == null) {
                        flag = true;

                    } else {
                        node = node.getRight();
                    }
                }

                cis = ((INode) paNode.getData()).compare(node.getData());
            }

            Node right = null;

            if (cis < 0) {
                if (node != null) {
                    list.add(node.getData());
                    list = inOrder(node);
                }

            }

            //kym nie dostatocny pocet alebo kym nie null
            while (list.size() < pocet && node != null) {
                if (node.getParent() != null) {
                    cis = ((INode) node.getParent().getData()).compare(node.getData());
                    if (cis < 0) {
                        node = node.getParent();

                    } else {

                        node = node.getParent();
                        list.add(node.getData());
                        right = node.getRight();

                        if (right != null) {
                            if (list.size() < pocet) {
                                list = inOrder(right, list, pocet);
                            }
                        }
                    }
                } else {
                    node = null;
                }
            }

        }
        return list;
    }

    /**
     * In order od vrchola
     *
     * @param paNode
     * @param list
     * @return
     */
    public ArrayList inOrder(Node paNode, ArrayList list, int pocet) {

        if (paNode == null) {
            return list;
        }

        int count = 0;
        Stack<Node> stack = new Stack<Node>();

        while (!stack.isEmpty() || paNode != null) {

            if (paNode != null) {
                stack.push(paNode);
                paNode = paNode.getLeft();
            } else {
                Node n = stack.pop();
                list.add(n.getData());
                count++;
                //System.out.printf("%s, %n", n.toString());
                paNode = n.getRight();
                /**
                 * ak som dosiahol pocet tak to stopnem
                 */
                if (list.size() >= pocet) {
                    paNode = null;
                    stack.removeAllElements();

                }

            }

        }
        return list;

    }

    /**
     * Vrati array list pre Table
     *
     * @return
     */
    public ArrayList getTableRows() {

        return inOrder(root);

    }

    public Node getRoot() {
        return root;
    }

    public int getCount() {
        return count;
    }

   

}
