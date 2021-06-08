package yh.spring.mvc.dao;

import yh.spring.mvc.service.MemberService;
import yh.spring.mvc.vo.Member;
import yh.spring.mvc.vo.Zipcode;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface MemberDAO {
    
// memberService 인터페이스 참조
// String newMember(Member m);
// String findZipcode(String dong);
// String checkUserid(String uid);
// boolean checkLogin(Member m, HttpSession sess);

    int insertMember(Member m);
    List<Zipcode> selectZipcode(String dong);
    int selectOneUserid(String uid);
    int selectLogin(Member m);

}
