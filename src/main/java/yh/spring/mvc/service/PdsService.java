package yh.spring.mvc.service;

import org.springframework.web.multipart.MultipartFile;
import yh.spring.mvc.vo.Pds;

import java.util.List;

public interface PdsService {

    boolean newPds(Pds p , MultipartFile[] file);

    List<Pds> readPds(String cp);
    int countPds();
    
    Pds readOnePds(String pno); //자료실 본문보기
    Pds readOneFname(String pno, String order); //파일이름 불러오기

    boolean downCountPds(String pno, String order);

}
