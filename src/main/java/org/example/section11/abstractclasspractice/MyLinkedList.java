package org.example.section11.abstractclasspractice;

public class MyLinkedList implements NodeList{
    private ListItem root;

    public MyLinkedList(ListItem root) {
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return root;
    }

    @Override
    public boolean addItem(ListItem item) {
        ListItem current = this.root;
        if (current.compareTo(item) > 0) {
            current.setPrevious(item);
            item.setNext(current);
            this.root = item;
            return true;
        }

        while (current.next() != null) {
            if (current.compareTo(item) == 0) {
                return false;
            }
            if (current.compareTo(item) < 0 && current.next().compareTo(item) > 0) {
                ListItem next = current.next();
                current.setNext(item);
                item.setPrevious(current);
                item.setNext(next);
                next.setPrevious(item);
                return true;
            }
            current = current.next();
        }

        if (current.compareTo(item) < 0) {
            current.setNext(item);
            return true;
        }

        return false;
    }

    @Override
    public boolean removeItem(ListItem item) {
        if (this.root == null || item == null) {
            return false;
        }

        ListItem current = this.root;
        while (current != null) {
            if (current.compareTo(item) == 0) {
                if (current.previous() != null) {
                    current.previous().setNext(current.next());
                } else {
                    this.root = current.next();
                }
                if (current.next() != null) {
                    current.next().setPrevious(current.previous());
                }
                return true;
            }
            current = current.next();
        }
        return false;
    }

    @Override
    public void traverse(ListItem root) {
        if (root == null) {
            System.out.println("The list is empty");
            return;
        }

        ListItem current = root;
        while (current != null) {
            System.out.println(current.getValue());
            current = current.next();
        }
    }
}
