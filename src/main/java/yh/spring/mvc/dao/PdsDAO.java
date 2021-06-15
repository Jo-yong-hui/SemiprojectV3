package yh.spring.mvc.dao;

import yh.spring.mvc.vo.Pds;

import java.util.List;
import java.util.Map;

public interface PdsDAO {

    int insertPds(Pds p);
    List<Pds> selectPds(int snum);
    int selectCountPds();

    //pno는 글번호
    Pds selectOnePds(String pno);
    Pds selectOneFname(Map<String, String> param);
    int downCountPds(Map<String, String> param);


    void updateRecmd(String pno);

    String selectPrvpno(String pno);

    String selecNxtpno(String pno);

    void deletePds(String pno);
}
