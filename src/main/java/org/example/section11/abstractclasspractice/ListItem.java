package org.example.section11.abstractclasspractice;
// 11/02/25 Section 11 Exercise 47
public abstract class ListItem {
    protected ListItem rightLink;
    protected ListItem leftLink;
    protected Object value;

    public ListItem(Object value) {
        this.value = value;
    }

    abstract ListItem next();
    abstract ListItem setNext(ListItem nextItem);
    abstract ListItem previous();
    abstract ListItem setPrevious(ListItem previousItem);
    abstract int compareTo(ListItem otherItem);

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
