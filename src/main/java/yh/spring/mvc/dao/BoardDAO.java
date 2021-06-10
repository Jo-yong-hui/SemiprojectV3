package yh.spring.mvc.dao;

import yh.spring.mvc.vo.Board;

import java.util.List;
import java.util.Map;

public interface BoardDAO {

    int insertBoard(Board b);
    int updateBoard(Board b);
    int deleteBoard(String bdno);

    //목록출력
    List<Board> selectBoard(int snum);
    List<Board> findSelectBoard(Map<String, Object> param);
    Board selectOneBoard(String bdno);

    //게시글 수
    int selectCountBoard();
    int selectCountBoard(Map<String, Object> param);
    int viewCountBoard(String bdno);

}
