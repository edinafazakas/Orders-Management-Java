package model;

/**
 * clasa aceasta are toate variabilele pentru tabelul product
 */

public class Product {
    private int id;
    private String name;
    private int count;
    private int price;
    private String quality;

    public Product() {
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Product(int id, String name, int count, int price) {
        super();
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;

    }

    public Product(String name, int count) {
        super();
        this.name = name;
        this.count = count;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", count=" + count + " price= " + price + "]\n";
    }

}
