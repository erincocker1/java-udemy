package org.example.AbstractClassPractice;

public class Node extends ListItem {

    public Node(Object value) {
        super(value);
    }

    @Override
    ListItem next() {
        return this.rightLink;
    }

    @Override
    ListItem setNext(ListItem nextItem) {
        return this.rightLink = nextItem;
    }

    @Override
    ListItem previous() {
        return this.leftLink;
    }

    @Override
    ListItem setPrevious(ListItem previousItem) {
        return this.leftLink = previousItem;
    }

    @Override
    int compareTo(ListItem otherItem) {
        int thisValue = Integer.parseInt(this.getValue().toString());
        int otherValue = Integer.parseInt(otherItem.getValue().toString());
        return Integer.compare(thisValue, otherValue);
    }
}
