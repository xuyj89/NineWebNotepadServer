package top.muyan.ninewebnotepad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.muyan.ninewebnotepad.mapper.NoteBookMapper;
import top.muyan.ninewebnotepad.model.MyException;
import top.muyan.ninewebnotepad.model.Result;
import top.muyan.ninewebnotepad.model.db.NoteBook;
import top.muyan.ninewebnotepad.utils.TokenUtils;

import java.util.List;
import java.util.UUID;

@Service
public class NoteBookService {
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private NoteBookMapper noteBookMapper;

    public Result<List<NoteBook>> list(String token){
        Result<List<NoteBook>> result = new Result<>();
        tokenUtils.verifyToken(token);

        result.setData(noteBookMapper.list());
        return result;
    }

    public Result<String> set(String token,NoteBook noteBook){
        Result<String> result = new Result<>();

        tokenUtils.verifyToken(token);

        String name = noteBook.getName();
        if(!StringUtils.hasText(name)){
            throw new MyException("记事本名不能为空");
        }

        String id = noteBook.getId();
        if(id==null){
            id = UUID.randomUUID().toString();
            List<NoteBook> dbNoteBookList = noteBookMapper.list();
            int maxOrderNo = 0;
            for(NoteBook noteBookTemp:dbNoteBookList){
                if(noteBookTemp.getOrderNo()>maxOrderNo){
                    maxOrderNo = noteBookTemp.getOrderNo();
                }
            }
            noteBook.setId(id);
            noteBook.setOrderNo(maxOrderNo+1);
            noteBookMapper.add(noteBook);
        }else{
            noteBookMapper.update(noteBook);
        }

        return result;
    }

    public Result<String> delete(String token,String id){
        Result<String> result = new Result<>();

        tokenUtils.verifyToken(token);

        noteBookMapper.delete(id);



        return result;
    }
}
