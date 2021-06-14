package yh.spring.mvc.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import yh.spring.mvc.vo.Board;

import java.util.List;
import java.util.Map;

@Repository("bdao")
public class BoardDAOImpl implements  BoardDAO{
//SqlSession이란 RDB에 인증을 거친 논리적인 연결 상태를 말하는 것이다.
//sql문을 직접실행할 수 있는 객체가 sqlSession이다.
//Mybatis를 이용하여 DAO를 구현하려면 SqlSession 객체가 필요하다.
    @Autowired private SqlSession sqlSession;

    @Override
    public int insertBoard(Board b) {

        return sqlSession.insert("board.insertBoard", b);
    }

    @Override
    public int updateBoard(Board b) {
        return 0;
    }

    @Override
    public int deleteBoard(String bdno) {
        return 0;
    }

    @Override
    public List<Board> selectBoard(int snum) {
        return sqlSession.selectList("board.selectBoard", snum);
    }

    ////검색한 결과, 검색기능 구현
    @Override
    public List<Board> findSelectBoard(Map<String, Object> param) {
        return sqlSession.selectList("board.findSelect", param);
    }

    @Override
    public Board selectOneBoard(String bdno) {
        return sqlSession.selectOne("board.selectOne",bdno);
    }

    @Override
    public int selectCountBoard() {

        return sqlSession.selectOne("board.countBoard");
    }

    //검색한 결과 총 개수
    @Override
    public int selectCountBoard(Map<String, Object> param) {
        return sqlSession.selectOne("board.findSelectCount", param);
    }

    @Override
    public int viewCountBoard(String bdno) {
        return sqlSession.update("board.viewsBoard", bdno);
    }
}
