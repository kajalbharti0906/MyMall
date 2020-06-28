package learncodeonline.in.mymall.cart;

public class CartItemModel {

    public static final int CART_ITEM = 0;
    public static final int TOTAL_AMOUNT = 1;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    //////////cart item
    private String productID;
    private String productImage;
    private String productTitle;
    private String productPrice;
    private String cuttedPrice;
    private Long freeCoupon;
    private Long productQuantity;
    private Long offersApplied;
    private Long couponsApplied;

    public CartItemModel(int type, String productID, String productImage, String productTitle, String productPrice, String cuttedPrice, Long freeCoupon, Long productQuantity, Long offersApplied, Long couponsApplied) {
        this.type = type;
        this.productID = productID;
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.cuttedPrice = cuttedPrice;
        this.freeCoupon = freeCoupon;
        this.productQuantity = productQuantity;
        this.offersApplied = offersApplied;
        this.couponsApplied = couponsApplied;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getCuttedPrice() {
        return cuttedPrice;
    }

    public void setCuttedPrice(String cuttedPrice) {
        this.cuttedPrice = cuttedPrice;
    }

    public Long getFreeCoupon() {
        return freeCoupon;
    }

    public void setFreeCoupon(Long freeCoupon) {
        this.freeCoupon = freeCoupon;
    }

    public Long getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Long productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Long getOffersApplied() {
        return offersApplied;
    }

    public void setOffersApplied(Long offersApplied) {
        this.offersApplied = offersApplied;
    }

    public Long getCouponsApplied() {
        return couponsApplied;
    }

    public void setCouponsApplied(Long couponsApplied) {
        this.couponsApplied = couponsApplied;
    }

    public String getProductId() {
        return productID;
    }

    public void setProductId(String productId) {
        this.productID = productId;
    }
    //////////cart item

    //////////cart total

    public CartItemModel(int type) {
        this.type = type;
    }

    //////////cart total
}
