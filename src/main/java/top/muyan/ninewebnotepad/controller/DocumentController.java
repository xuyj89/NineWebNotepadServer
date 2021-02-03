package top.muyan.ninewebnotepad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.muyan.ninewebnotepad.model.Result;
import top.muyan.ninewebnotepad.model.db.Document;
import top.muyan.ninewebnotepad.service.DocumentService;

@RestController
@RequestMapping("/api/document")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @RequestMapping(value = "/set",method = RequestMethod.POST)
    public Result<String> set(@RequestParam String token, @RequestBody Document document){
        return documentService.set(token,document);
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public Result<Document> get(@RequestParam String token, @RequestParam String noteBookId,@RequestParam String titleId){
        return documentService.get(token,noteBookId,titleId);
    }
}
