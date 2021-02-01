package top.muyan.ninewebnotepad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.muyan.ninewebnotepad.mapper.TitleMapper;
import top.muyan.ninewebnotepad.model.MyException;
import top.muyan.ninewebnotepad.model.Result;
import top.muyan.ninewebnotepad.model.db.NoteBook;
import top.muyan.ninewebnotepad.model.db.Title;
import top.muyan.ninewebnotepad.utils.TokenUtils;

import java.util.List;
import java.util.UUID;

@Service
public class TitleService {
    @Autowired
    private TitleMapper titleMapper;
    @Autowired
    private TokenUtils tokenUtils;

    public Result<List<Title>> listByNoteBookId(String token,String noteBookId){
        Result<List<Title>> result = new Result<>();

        tokenUtils.verifyToken(token);

        List<Title> titleList = titleMapper.listByNoteBookId(noteBookId);
        result.setData(titleList);

        return result;
    }

    public Result<String> set(String token, Title title){
        Result<String> result = new Result<>();

        tokenUtils.verifyToken(token);

        String name = title.getName();
        if(!StringUtils.hasText(name)){
            throw new MyException("文章标题不能为空");
        }

        String id = title.getId();
        if(id==null){
            id = UUID.randomUUID().toString();
            List<Title> dbNoteBookList = titleMapper.listByNoteBookId(title.getNoteBookId());
            int maxOrderNo = 0;
            for(Title titleTemp:dbNoteBookList){
                if(titleTemp.getOrderNo()>maxOrderNo){
                    maxOrderNo = titleTemp.getOrderNo();
                }
            }
            title.setId(id);
            title.setOrderNo(maxOrderNo+1);
            titleMapper.add(title);
        }else{
            titleMapper.update(title);
        }

        return result;
    }

    public Result<String> delete(String token,String id){
        Result<String> result = new Result<>();

        tokenUtils.verifyToken(token);

        titleMapper.delete(id);
        return result;
    }
}
