package net.verytools.prac.praccontextualserializer;

public class User {

    private String mobile;

    private String mobile2;

    //    @JsonSerialize(using = SimpleMaskMobileSerializer.class)
    @MobileMask(masks = 4)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @MobileMask(masks = 2)
    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }
}
