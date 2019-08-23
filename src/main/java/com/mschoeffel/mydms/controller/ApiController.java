package com.mschoeffel.mydms.controller;

import com.mschoeffel.mydms.model.*;
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

    /*----------------------------------------------------------------------------------------------------------------*/
    /* Documents */
    /*----------------------------------------------------------------------------------------------------------------*/

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
        document.setId(id);
        documentService.save(document);

        return ResponseEntity.noContent().build();
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /* Sender */
    /*----------------------------------------------------------------------------------------------------------------*/

    @GetMapping("/senders")
    public List<Sender> getAllSender(){
        return senderService.findAll();
    }

    @RequestMapping(value = "/sender", method = RequestMethod.GET, params = {"id"})
    public Sender getSender(@RequestParam int id){
        return senderService.findById(id);
    }

    @RequestMapping(value = "/sender", method = RequestMethod.DELETE, params = {"id"})
    public void deleteSender(@RequestParam int id){
        senderService.deleteById(id);
    }

    @RequestMapping(value = "/sender", method = RequestMethod.POST)
    public ResponseEntity<Object> createSender(@RequestBody Sender sender){
        Sender savedSender = senderService.save(sender);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(savedSender.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/sender", method = RequestMethod.PUT, params = {"id"})
    public ResponseEntity<Object> updateSender(@RequestBody Sender sender, @RequestParam int id){
        sender.setId(id);
        senderService.save(sender);

        return ResponseEntity.noContent().build();
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /* Tag */
    /*----------------------------------------------------------------------------------------------------------------*/

    @GetMapping("/tags")
    public List<Tag> getAllTags(){
        return tagService.findAll();
    }

    @RequestMapping(value = "/tag", method = RequestMethod.GET, params = {"id"})
    public Tag getTag(@RequestParam String id){
        return tagService.findById(id);
    }

    @RequestMapping(value = "/tag", method = RequestMethod.DELETE, params = {"id"})
    public void deleteTag(@RequestParam String id){
        tagService.deleteById(id);
    }

    @RequestMapping(value = "/tag", method = RequestMethod.POST)
    public ResponseEntity<Object> createTag(@RequestBody Tag tag){
        Tag savedTag = tagService.save(tag);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(savedTag.getTag()).toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/tag", method = RequestMethod.PUT, params = {"id"})
    public ResponseEntity<Object> updateTag(@RequestBody Tag tag, @RequestParam String id){
        tag.setTag(id);
        tagService.save(tag);

        return ResponseEntity.noContent().build();
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /* Type */
    /*----------------------------------------------------------------------------------------------------------------*/

    @GetMapping("/types")
    public List<Type> getAllTypes(){
        return typeService.findAll();
    }

    @RequestMapping(value = "/type", method = RequestMethod.GET, params = {"id"})
    public Type getType(@RequestParam String id){
        return typeService.findById(id);
    }

    @RequestMapping(value = "/type", method = RequestMethod.DELETE, params = {"id"})
    public void deleteType(@RequestParam String id){
        typeService.deleteById(id);
    }

    @RequestMapping(value = "/type", method = RequestMethod.POST)
    public ResponseEntity<Object> createType(@RequestBody Type type){
        Type savedType = typeService.save(type);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(savedType.getShort_name()).toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/type", method = RequestMethod.PUT, params = {"id"})
    public ResponseEntity<Object> updateType(@RequestBody Type type, @RequestParam String id){
        type.setShort_name(id);
        typeService.save(type);

        return ResponseEntity.noContent().build();
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /* User */
    /*----------------------------------------------------------------------------------------------------------------*/

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET, params = {"id"})
    public User getUser(@RequestParam String id){
        return userService.findById(id);
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE, params = {"id"})
    public void deleteUser(@RequestParam String id){
        userService.deleteById(id);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@RequestBody User user){
        User savedUser = userService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(savedUser.getUsername()).toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT, params = {"id"})
    public ResponseEntity<Object> updateUser(@RequestBody User user, @RequestParam String id){
        user.setUsername(id);
        userService.save(user);

        return ResponseEntity.noContent().build();
    }

}
