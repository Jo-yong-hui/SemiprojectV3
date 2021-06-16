package yh.spring.mvc.service;

import org.springframework.web.multipart.MultipartFile;
import yh.spring.mvc.vo.Gallery;

import java.util.List;

public interface GalleryService {

    boolean newGallery(Gallery g, MultipartFile[] img);
    List<Gallery> readGallery(String cp);
    Gallery readOneGallery(String gno);


}
