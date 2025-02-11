package org.example;

import org.example.AbstractClassPractice.*;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

//        NodeList tree = new SearchTree(node3);
//        tree.addItem(node1);
//        tree.addItem(node2);
//        tree.addItem(node4);
//        tree.addItem(node5);
//
//        tree.traverse(tree.getRoot());
//        System.out.println();
//        tree.removeItem(node3);
//        tree.traverse(tree.getRoot());

        Node node6 = new Node(6);
        NodeList linkedList = new MyLinkedList(node1);
        linkedList.addItem(node2);
        linkedList.addItem(node5);
        linkedList.addItem(node4);
        linkedList.addItem(node3);
        linkedList.traverse(linkedList.getRoot());
        System.out.println();

        linkedList.removeItem(node1);
        linkedList.traverse(linkedList.getRoot());
        System.out.println();

        linkedList.removeItem(node6);
        linkedList.traverse(linkedList.getRoot());
        System.out.println();

        linkedList.removeItem(node5);
        linkedList.traverse(linkedList.getRoot());
        System.out.println();

        linkedList.removeItem(node2);
        linkedList.traverse(linkedList.getRoot());
        System.out.println();

        linkedList.removeItem(node3);
        linkedList.traverse(linkedList.getRoot());
        System.out.println();

        linkedList.removeItem(node4);
        linkedList.traverse(linkedList.getRoot());
        System.out.println();

        linkedList.removeItem(node4);
        linkedList.traverse(linkedList.getRoot());

    }
}
