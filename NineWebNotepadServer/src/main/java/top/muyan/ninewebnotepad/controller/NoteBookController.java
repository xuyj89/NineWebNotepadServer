package top.muyan.ninewebnotepad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.muyan.ninewebnotepad.model.Result;
import top.muyan.ninewebnotepad.model.db.NoteBook;
import top.muyan.ninewebnotepad.service.NoteBookService;

import java.util.List;

@RestController
@RequestMapping("/api/noteBook")
public class NoteBookController {
    @Autowired
    private NoteBookService noteBookService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result<List<NoteBook>> list(@RequestParam String token){
        return noteBookService.list(token);
    }

    @RequestMapping(value = "/set",method = RequestMethod.POST)
    public Result<String> set(@RequestParam String token,@RequestBody NoteBook noteBook){
        return noteBookService.set(token,noteBook);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Result<String> delete(@RequestParam String token,@RequestParam String id){
        return noteBookService.delete(token, id);
    }
}
