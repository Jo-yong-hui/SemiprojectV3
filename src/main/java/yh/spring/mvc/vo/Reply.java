package yh.spring.mvc.vo;

public class Reply {

    protected String rno;
    protected String reply;
    protected String userid;
    protected String regdate;
    protected String bdno;
    protected String rpno;

    public Reply(String rno, String reply, String userid, String regdate, String bdno, String rpno) {
        this.rno = rno;
        this.reply = reply;
        this.userid = userid;
        this.regdate = regdate;
        this.bdno = bdno;
        this.rpno = rpno;
    }

    public String getRno() {
        return rno;
    }

    public void setRno(String rno) {
        this.rno = rno;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getBdno() {
        return bdno;
    }

    public void setBdno(String bdno) {
        this.bdno = bdno;
    }

    public String getRpno() {
        return rpno;
    }

    public void setRpno(String rpno) {
        this.rpno = rpno;
    }
}
