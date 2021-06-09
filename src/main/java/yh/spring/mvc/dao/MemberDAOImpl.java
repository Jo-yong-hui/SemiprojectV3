package yh.spring.mvc.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import yh.spring.mvc.vo.Member;
import yh.spring.mvc.vo.Zipcode;

import java.util.List;

@Repository("mdao")
public class MemberDAOImpl implements MemberDAO {
    @Autowired private SqlSession sqlSession;

    @Override
    public int insertMember(Member m) {
        return sqlSession.insert("member.insertmember", m);
    }

    @Override
    public List<Zipcode> selectZipcode(String dong) {
        return sqlSession.selectList("member.selectZipcode", dong);
    }

    @Override
    public int selectOneUserid(String uid) {
        return sqlSession.selectOne("member.checkUserid",uid);
    }

    @Override
    public int selectLogin(Member m) {
        return 0;
    }
}
