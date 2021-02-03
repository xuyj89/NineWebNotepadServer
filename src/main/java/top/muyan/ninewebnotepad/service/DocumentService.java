package top.muyan.ninewebnotepad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import top.muyan.ninewebnotepad.model.MyException;
import top.muyan.ninewebnotepad.model.Result;
import top.muyan.ninewebnotepad.model.db.Document;
import top.muyan.ninewebnotepad.utils.TokenUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
public class DocumentService {
    @Autowired
    private TokenUtils tokenUtils;

    public Result<String> set(String token, Document document){
        Result<String> result = new Result<>();

        tokenUtils.verifyToken(token);

        String noteBookId = document.getNoteBookId();
        String titleId = document.getTitleId();
        String text = document.getText();

        if(!StringUtils.hasText(noteBookId) || !StringUtils.hasText(titleId)){
            throw new MyException("没有选择记事本或标题");
        }

        String noteBookDirPath = "./db/document/"+noteBookId;

        File noteBookDir = new File(noteBookDirPath);
        if(!noteBookDir.exists()){
            noteBookDir.mkdirs();
        }

        File textFile = new File(noteBookDirPath+"/"+noteBookId);
        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(textFile), StandardCharsets.UTF_8)));
            printWriter.print(text);
            printWriter.flush();
            printWriter.close();
        } catch (FileNotFoundException e) {
            throw new MyException("文件写入错误",e);
        }

        return result;
    }

    public Result<Document> get(String token, String noteBookId, String titleId){
        Result<Document> result = new Result<>();

        tokenUtils.verifyToken(token);

        if(!StringUtils.hasText(noteBookId) || !StringUtils.hasText(titleId)){
            throw new MyException("没有选择记事本或标题");
        }

        Document document = new Document();
        document.setNoteBookId(noteBookId);
        document.setTitleId(titleId);

        String noteBookDirPath = "./db/document/"+noteBookId;

        File noteBookDir = new File(noteBookDirPath);
        if(!noteBookDir.exists()){
            noteBookDir.mkdirs();
        }

        File textFile = new File(noteBookDirPath+"/"+noteBookId);
        if(!textFile.exists()){
            document.setText("");
            result.setData(document);
            return result;
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(textFile),StandardCharsets.UTF_8));
            String text = bufferedReader.readLine();
            document.setText(text);
            result.setData(document);
        } catch (IOException e) {
            throw new MyException("文件读取错误",e);
        }

        return result;
    }
}
