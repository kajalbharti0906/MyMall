package learncodeonline.in.mymall.reward;

public class RewardModel {
    private String title;
    private String validity;
    private String couponBody;

    public RewardModel(String title, String validity, String couponBody) {
        this.title = title;
        this.validity = validity;
        this.couponBody = couponBody;
    }

    public String getCouponBody() {
        return couponBody;
    }

    public void setCouponBody(String couponBody) {
        this.couponBody = couponBody;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
