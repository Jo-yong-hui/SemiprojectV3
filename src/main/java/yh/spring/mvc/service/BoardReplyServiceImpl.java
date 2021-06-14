package yh.spring.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import yh.spring.mvc.dao.BoardReplyDAO;
import yh.spring.mvc.vo.Reply;

import java.util.List;

@Service("brsrv")
public class BoardReplyServiceImpl implements BoardReplyService{

    @Autowired private BoardReplyDAO brdao;

    @Override
    public List<Reply> readReply(String bdno) {
        return brdao.selectReply(bdno);
    }


    @Override
    public boolean newComment(Reply r) { // 댓글
        boolean isInserted = false;
        if(brdao.insertComment(r) > 0 ) isInserted = true;
        return isInserted;
    }

    @Override
    public boolean newReply(Reply r) {
        if(brdao.insertReply(r) > 0) return true;
        return false;
    }
}
