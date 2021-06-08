package yh.spring.mvc.vo;

public class Zipcode {
    protected String SIDO;
    protected String GUGUN;
    protected String DONG;
    protected String RI;
    protected String BUNJI;
    protected String seq;

    public Zipcode(String SIDO, String GUGUN, String DONG, String RI, String BUNJI, String seq) {
        this.SIDO = SIDO;
        this.GUGUN = GUGUN;
        this.DONG = DONG;
        this.RI = RI;
        this.BUNJI = BUNJI;
        this.seq = seq;
    }

    public String getSIDO() {
        return SIDO;
    }

    public void setSIDO(String SIDO) {
        this.SIDO = SIDO;
    }

    public String getGUGUN() {
        return GUGUN;
    }

    public void setGUGUN(String GUGUN) {
        this.GUGUN = GUGUN;
    }

    public String getDONG() {
        return DONG;
    }

    public void setDONG(String DONG) {
        this.DONG = DONG;
    }

    public String getRI() {
        return RI;
    }

    public void setRI(String RI) {
        this.RI = RI;
    }

    public String getBUNJI() {
        return BUNJI;
    }

    public void setBUNJI(String BUNJI) {
        this.BUNJI = BUNJI;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }
}

