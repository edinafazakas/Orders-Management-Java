package model;

/**
 * clasa aceasta are toate variabilele pentru tabelul order1
 */
public class Order {
    private int id;
    private int clientId;
    private int productId;
    private int productCount;
    private int productPrice;

    public Order() {
    }

    public Order(int id, int clientName, int productName, int productCount, int productPrice) {
        super();
        this.id = id;
        this.clientId = clientName;
        this.productId = productName;
        this.productCount = productCount;
        this.productPrice = productPrice;
    }

    public int getId() {
        return id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductId() {
        return productId;
    }

    public int getProductCount() {
        return productCount;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public Order(int clientId, int productId, int productCount, int productPrice) {
        super();
        this.clientId = clientId;
        this.productId = productId;
        this.productCount = productCount;
        this.productPrice = productPrice;
    }



    @Override
    public String toString() {
        return "Order [id=" + id + ", clientId=" + clientId + ", productId= " + productId + ", productCount=" + productCount + ", productPrice=" + productPrice
                + "]\n";
    }

}
