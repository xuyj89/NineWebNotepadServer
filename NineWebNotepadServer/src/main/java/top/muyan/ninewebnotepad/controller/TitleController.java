package top.muyan.ninewebnotepad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.muyan.ninewebnotepad.model.Result;
import top.muyan.ninewebnotepad.model.db.Title;
import top.muyan.ninewebnotepad.service.TitleService;

import java.util.List;

@RestController
@RequestMapping("/api/title")
public class TitleController {
    @Autowired
    private TitleService titleService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result<List<Title>> listByNoteBookId(@RequestParam String token, @RequestParam String noteBookId){
        return titleService.listByNoteBookId(token,noteBookId);
    }

    @RequestMapping(value = "/set",method = RequestMethod.POST)
    public Result<String> set(@RequestParam String token, @RequestBody Title title){
        return titleService.set(token,title);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Result<String> delete(@RequestParam String token,@RequestParam String id){
        return titleService.delete(token, id);
    }
}
