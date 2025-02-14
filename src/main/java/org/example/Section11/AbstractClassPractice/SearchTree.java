package org.example.Section11.AbstractClassPractice;

public class SearchTree implements NodeList {
    private ListItem root;

    public SearchTree(ListItem root) {
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return root;
    }

    @Override
    public boolean addItem(ListItem item) {
        if (this.root == null) {
            this.root = item;
            return true;
        }

        ListItem current = this.root;
        while (true) {
            if (current.compareTo(item) == 0) {
                return false;
            }
            if (current.compareTo(item) > 0) {
                //item goes before current
                if (current.previous() == null) {
                    current.setPrevious(item);
                    return true;
                }
                current = current.previous();
            } else if (current.compareTo(item) < 0) {
                //item goes after current
                if (current.next() == null) {
                    current.setNext(item);
                    return true;
                }
                current = current.next();
            }
        }
    }

    @Override
    public boolean removeItem(ListItem item) {
        if (item == null) {
            return false;
        }

        ListItem parent = null;
        ListItem current = this.root;

        while (current != null) {
            if (current.compareTo(item) == 0) {
                performRemoval(current, parent);
                return true;
            }

            parent = current;
            if (current.compareTo(item) > 0) {
                current = current.previous();
            } else {
                current = current.next();
            }
        }
        return false;
    }

    private void performRemoval(ListItem toBeRemoved, ListItem parent) {
        //If item is a leaf node
        if (toBeRemoved.next() == null && toBeRemoved.previous() == null) {
            if (parent == null) {
                this.root = null;
            } else if (parent.next().compareTo(toBeRemoved) == 0) {
                parent.setNext(null);
            } else if (parent.previous().compareTo(toBeRemoved) == 0) {
                parent.setPrevious(null);
            }
            return;
        }

        //If item has only left child
        if (toBeRemoved.next() == null) {
            if (parent == null) {
                this.root = toBeRemoved.previous();
            } else if (parent.next().compareTo(toBeRemoved) == 0) {
                parent.setNext(toBeRemoved.previous());
            } else if (parent.previous().compareTo(toBeRemoved) == 0) {
                parent.setPrevious(toBeRemoved.previous());
            }
            return;
        }

        //If item has only right child
        if (toBeRemoved.previous() == null) {
            if (parent == null) {
                this.root = toBeRemoved.next();
            } else if (parent.next().compareTo(toBeRemoved) == 0) {
                parent.setNext(toBeRemoved.next());
            } else if (parent.previous().compareTo(toBeRemoved) == 0) {
                parent.setPrevious(toBeRemoved.next());
            }
            return;
        }

        //If item has both children
        ListItem nextNodeInOrder = toBeRemoved.next();
        ListItem parentOfNextNodeInOrder = toBeRemoved;
        boolean loopDidRun = false;
        while (nextNodeInOrder.previous() != null) {
            parentOfNextNodeInOrder = nextNodeInOrder;
            nextNodeInOrder = nextNodeInOrder.previous();
            loopDidRun = true;
        }
        if (loopDidRun) {
            parentOfNextNodeInOrder.setPrevious(nextNodeInOrder.next()); //could be null or a node
        } else {
            parentOfNextNodeInOrder.setNext(nextNodeInOrder.next());
        }



        if (parent == null) {
            this.root = nextNodeInOrder;
        } else if (parent.next().compareTo(toBeRemoved) == 0) {
            parent.setNext(nextNodeInOrder);
        } else if (parent.previous().compareTo(toBeRemoved) == 0) {
            parent.setPrevious(nextNodeInOrder);
        }
        nextNodeInOrder.setPrevious(toBeRemoved.previous());
        nextNodeInOrder.setNext(toBeRemoved.next());
    }

    @Override
    public void traverse(ListItem root) {
        if (root == null) {
            return;
        }
        traverse(root.previous());
        System.out.println(root.getValue());
        traverse(root.next());
    }
}
