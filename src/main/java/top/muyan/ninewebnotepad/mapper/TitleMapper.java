package top.muyan.ninewebnotepad.mapper;

import org.apache.ibatis.annotations.Param;
import top.muyan.ninewebnotepad.model.db.Title;

import java.util.List;

public interface TitleMapper {
    public void add(@Param("title")Title title);
    public void update(@Param("title")Title title);
    public void delete(@Param("id")String id);
    public List<Title> listByNoteBookId(@Param("noteBookId")String noteBookId);
    public void batchDelete(@Param("idList")List<String> idList);
}
