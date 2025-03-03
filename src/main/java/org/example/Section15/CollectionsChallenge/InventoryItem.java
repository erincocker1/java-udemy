package org.example.Section15.CollectionsChallenge;

public class InventoryItem {
    private final Product product;
    private int qtyTotal;
    private int qtyReserved; //amount in carts but not sold yet
    private final int qtyReorder; //how much to reorder when at qtyLow
    private final int qtyLow;
    private final double salesPrice;

    public InventoryItem(Product product, int qtyTotal, int qtyReserved,
                         int qtyReorder, int qtyLow, double salesPrice) {
        this.product = product;
        this.qtyTotal = qtyTotal;
        this.qtyReserved = qtyReserved;
        this.qtyReorder = qtyReorder;
        this.qtyLow = qtyLow;
        this.salesPrice = salesPrice;
    }

    boolean reserveItem(int amount) {
        if (amount > qtyTotal - qtyReserved) return false;
        qtyReserved += amount;
        return true;
    }

    boolean releaseItem(int amount) {
        if (amount < qtyReserved) return false;
        qtyReserved -= amount;
        return true;
    }

    boolean sellItem(int amount) {
        if (amount < qtyReserved || amount < qtyTotal) return false;
        qtyReserved -= amount;
        qtyTotal -= amount;
        if (qtyTotal <= qtyLow) placeInventoryOrder();
        return true;
    }

    private void placeInventoryOrder() {
        System.out.println("Ordering more " + product);
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public Product getProduct() {
        return product;
    }

    public Category getCategory() {
        return product.category();
    }

    public String getSku() {
        return product.sku();
    }

    public String getName() {
        return product.name();
    }

    @Override
    public String toString() {
        return product.toString();
    }
}
