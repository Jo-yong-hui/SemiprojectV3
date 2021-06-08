package yh.spring.mvc.service;

import yh.spring.mvc.vo.Member;

import javax.servlet.http.HttpSession;

public interface MemberService {

    String newMember(Member m);
    String findZipcode(String dong);
    String checkUserid(String uid);
    boolean checkLogin(Member m, HttpSession sess);

}
