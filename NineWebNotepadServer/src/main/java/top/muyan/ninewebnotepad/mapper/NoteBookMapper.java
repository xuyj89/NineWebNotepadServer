package top.muyan.ninewebnotepad.mapper;

import org.apache.ibatis.annotations.Param;
import top.muyan.ninewebnotepad.model.db.NoteBook;

import java.util.List;

public interface NoteBookMapper {
    public void add(@Param("noteBook") NoteBook noteBook);
    public void update(@Param("noteBook")NoteBook noteBook);
    public void delete(@Param("id")String id);
    public List<NoteBook> list();
}
