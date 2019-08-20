package com.mschoeffel.mydms.controller;

import com.mschoeffel.mydms.model.Document;
import com.mschoeffel.mydms.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private UserService userService;
    private TypeService typeService;
    private TagService tagService;
    private SenderService senderService;
    private DocumentService documentService;
    private StorageService storageService;

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test() {
        return "hello world";
    }

    public ApiController(UserService userService, TypeService typeService, TagService tagService, SenderService senderService, DocumentService documentService, StorageService storageService) {
        this.userService = userService;
        this.typeService = typeService;
        this.tagService = tagService;
        this.senderService = senderService;
        this.documentService = documentService;
        this.storageService = storageService;
    }

    @GetMapping("/documents")
    public List<Document> getAllDocuments(){
        return documentService.findAll();
    }

    @RequestMapping(value = "/document", method = RequestMethod.GET, params = {"id"})
    public Document getDocument(@RequestParam int id){
        return documentService.findById(id);
    }

    @RequestMapping(value = "/documents", method = RequestMethod.DELETE, params = {"id"})
    public void deleteDocument(@RequestParam int id){
        documentService.deleteById(id);
    }

    @RequestMapping(value = "/documents", method = RequestMethod.POST)
    public ResponseEntity<Object> createDocument(@RequestBody Document document){
        Document savedDocument = documentService.save(document);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(savedDocument.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/documents", method = RequestMethod.PUT, params = {"id"})
    public ResponseEntity<Object> updateDocument(@RequestBody Document document, @RequestParam int id){
        Document getDocument = documentService.findById(id);

        document.setId(id);
        documentService.save(document);

        return ResponseEntity.noContent().build();
    }

}
