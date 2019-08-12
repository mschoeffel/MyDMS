package com.mschoeffel.mydms.controller;

import com.mschoeffel.mydms.model.*;
import com.mschoeffel.mydms.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Controller
@RequestMapping("/web")
public class WebController {

    private UserService userService;
    private TypeService typeService;
    private TagService tagService;
    private SenderService senderService;
    private DocumentService documentService;
    private StorageService storageService;


    /*----------------------------------------------------------------------------------------------------------------*/
    /* Home */
    /*----------------------------------------------------------------------------------------------------------------*/

    public WebController(UserService userService, TypeService typeService, TagService tagService, SenderService senderService, DocumentService documentService, StorageService storageService) {
        this.userService = userService;
        this.typeService = typeService;
        this.tagService = tagService;
        this.senderService = senderService;
        this.documentService = documentService;
        this.storageService = storageService;
    }

    @GetMapping("/home")
    public String showHome(Model model) {
        model.addAttribute("documents", documentService.findAll());
        return "list.html";
    }


    /*----------------------------------------------------------------------------------------------------------------*/
    /* User */
    /*----------------------------------------------------------------------------------------------------------------*/
    @GetMapping("/users")
    public String showUserList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users.html";
    }

    @GetMapping("/user/{username}")
    public String showUser(Model model, @PathVariable String username) {
        model.addAttribute("user", userService.findById(username));
        return "editUser.html";
    }

    @GetMapping("/user")
    public String showNewUser(Model model) {
        User user = new User();
        user.setDate(LocalDate.now());
        model.addAttribute("user", user);
        return "editUser.html";
    }

    @PostMapping("/user/update/{username}")
    public String updateUser(Model model, @ModelAttribute("user") User user, @PathVariable String username) {
        String id;
        if (userService.existsId(user.getUsername())) {
            //Already exists
            User userold = userService.findById(user.getUsername());
            user.setDate(userold.getDate());
            user.setPassword(userold.getPassword());
            user.setUsername(userold.getUsername());
        } else {
            //Need to create new
            user.setDate(LocalDate.now());
        }

        try {
            userService.save(user);
            model.addAttribute("message", "Save Successful");
        } catch (Exception e) {
            model.addAttribute("error", "Error occurred: " + e.getLocalizedMessage());
        }


        model.addAttribute("user", userService.findById(user.getUsername()));
        return "editUser.html";
    }

    @PostMapping("/user/delete/{username}")
    public String deleteUser(Model model, @PathVariable String username) {
        try {
            userService.deleteById(username);
            model.addAttribute("message", "Delete Successful");
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getLocalizedMessage());
        }
        model.addAttribute("users", userService.findAll());
        return "users.html";
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /* Types */
    /*----------------------------------------------------------------------------------------------------------------*/
    @GetMapping("/types")
    public String showTypeList(Model model) {
        model.addAttribute("types", typeService.findAll());
        return "types.html";
    }


    @GetMapping("/type/{typeshort}")
    public String showType(Model model, @PathVariable String typeshort) {
        model.addAttribute("type", typeService.findById(typeshort));
        return "editType.html";
    }

    @GetMapping("/type")
    public String showNewType(Model model) {
        Type type = new Type();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        type.setUser(userService.findById(authentication.getName()));
        type.setDate(LocalDate.now());
        model.addAttribute("type", type);
        return "editType.html";
    }

    @PostMapping("/type/update/{typeshort}")
    public String updateType(Model model, @ModelAttribute("type") Type type, @PathVariable String typeshort) {
        if (typeService.existsId(type.getShort_name())) {
            //Already exists
            Type typeold = typeService.findById(type.getShort_name());
            type.setUser(typeold.getUser());
            type.setShort_name(typeold.getShort_name());
            type.setDate(typeold.getDate());
        } else {
            //Need to create new
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            type.setUser(userService.findById(authentication.getName()));
            type.setDate(LocalDate.now());
        }

        try {
            typeService.save(type);
            model.addAttribute("message", "Save Successful");
        } catch (Exception e) {
            model.addAttribute("error", "Error occurred: " + e.getLocalizedMessage());
        }


        model.addAttribute("type", typeService.findById(type.getShort_name()));
        return "editType.html";
    }

    @PostMapping("/type/delete/{typeshort}")
    public String deleteType(Model model, @PathVariable String typeshort) {
        try {
            typeService.deleteById(typeshort);
            model.addAttribute("message", "Delete Successful");
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getLocalizedMessage());
        }
        model.addAttribute("types", typeService.findAll());
        return "types.html";
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /* Tags */
    /*----------------------------------------------------------------------------------------------------------------*/
    @GetMapping("/tags")
    public String showTagList(Model model) {
        model.addAttribute("tags", tagService.findAll());
        return "tags.html";
    }


    @GetMapping("/tag/{tag}")
    public String showTag(Model model, @PathVariable String tag) {
        model.addAttribute("tag", tagService.findById(tag));
        return "editTag.html";
    }

    @GetMapping("/tag")
    public String showNewTag(Model model) {
        Tag tag = new Tag();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        tag.setUser(userService.findById(authentication.getName()));
        tag.setDate(LocalDate.now());
        model.addAttribute("tag", tag);
        return "editTag.html";
    }

    @PostMapping("/tag/update/{tag_name}")
    public String updateType(Model model, @ModelAttribute("tag") Tag tag, @PathVariable String tag_name) {
        if (tagService.existsId(tag.getTag())) {
            //Already exists
            Tag tagold = tagService.findById(tag.getTag());
            tag.setUser(tagold.getUser());
            tag.setTag(tagold.getTag());
            tag.setDate(tagold.getDate());
        } else {
            //Need to create new
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            tag.setUser(userService.findById(authentication.getName()));
            tag.setDate(LocalDate.now());
        }

        try {
            tagService.save(tag);
            model.addAttribute("message", "Save Successful");
        } catch (Exception e) {
            model.addAttribute("error", "Error occurred: " + e.getLocalizedMessage());
        }


        model.addAttribute("tag", tagService.findById(tag.getTag()));
        return "editTag.html";
    }

    @PostMapping("/tag/delete/{tag}")
    public String deleteTag(Model model, @PathVariable String tag) {
        try {
            tagService.deleteById(tag);
            model.addAttribute("message", "Delete Successful");
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getLocalizedMessage());
        }
        model.addAttribute("tags", tagService.findAll());
        return "tags.html";
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /* Sender */
    /*----------------------------------------------------------------------------------------------------------------*/
    @GetMapping("/senders")
    public String showSenderList(Model model) {
        model.addAttribute("senders", senderService.findAll());
        return "senders.html";
    }


    @GetMapping("/sender/{id}")
    public String showTag(Model model, @PathVariable Integer id) {
        model.addAttribute("sender", senderService.findById(id));
        return "editSender.html";
    }

    @GetMapping("/sender")
    public String showNewSender(Model model) {
        Sender sender = new Sender();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        sender.setUser(userService.findById(authentication.getName()));
        sender.setDate(LocalDate.now());
        model.addAttribute("sender", sender);
        return "editSender.html";
    }

    @PostMapping("/sender/update/{id}")
    public String updateSender(Model model, @ModelAttribute("sender") Sender sender, @PathVariable Integer id) {
        if (senderService.existsId(sender.getId())) {
            //Already exists
            Sender senderold = senderService.findById(sender.getId());
            sender.setUser(senderold.getUser());
            sender.setId(senderold.getId());
            sender.setDate(senderold.getDate());
        } else {
            //Need to create new
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            sender.setUser(userService.findById(authentication.getName()));
            sender.setDate(LocalDate.now());
        }

        try {
            senderService.save(sender);
            model.addAttribute("message", "Save Successful");
        } catch (Exception e) {
            model.addAttribute("error", "Error occurred: " + e.getLocalizedMessage());
        }


        model.addAttribute("sender", senderService.findById(sender.getId()));
        return "editSender.html";
    }

    @PostMapping("/sender/delete/{id}")
    public String deleteSender(Model model, @PathVariable Integer id) {
        try {
            senderService.deleteById(id);
            model.addAttribute("message", "Delete Successful");
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getLocalizedMessage());
        }
        model.addAttribute("senders", senderService.findAll());
        return "senders.html";
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /* Documents */
    /*----------------------------------------------------------------------------------------------------------------*/


    @GetMapping("/document/{id}")
    public String showDocument(Model model, @PathVariable Integer id) {
        model.addAttribute("document", documentService.findById(id));
        model.addAttribute("tags", tagService.findAll());
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("senders", senderService.findAll());
        return "editDocument.html";
    }

    @GetMapping("/document")
    public String showNewDocument(Model model) {
        Document document = new Document();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        document.setUser(userService.findById(authentication.getName()));
        document.setDate(LocalDate.now());
        model.addAttribute("document", document);
        model.addAttribute("tags", tagService.findAll());
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("senders", senderService.findAll());
        return "editDocument.html";
    }

    @PostMapping("/document/update/{id}")
    public String updateDocument(Model model, @ModelAttribute("document") Document document, @RequestParam("uploadfile") MultipartFile uploadfile, @PathVariable Integer id) {
        if (documentService.existsId(document.getId())) {
            //Already exists
            Document documentold = documentService.findById(document.getId());
            document.setUser(documentold.getUser());
            document.setId(documentold.getId());
            document.setDate(documentold.getDate());
        } else {
            //Need to create new
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            document.setUser(userService.findById(authentication.getName()));
            document.setDate(LocalDate.now());
            storageService.store(uploadfile);
        }

        try {
            documentService.save(document);
            model.addAttribute("message", "Save Successful");
        } catch (Exception e) {
            model.addAttribute("error", "Error occurred: " + e.getLocalizedMessage());
        }

        model.addAttribute("document", documentService.findById(document.getId()));
        model.addAttribute("tags", tagService.findAll());
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("senders", senderService.findAll());
        return "editDocument.html";
    }

    @PostMapping("/document/delete/{id}")
    public String deleteDocument(Model model, @PathVariable Integer id) {
        try {
            documentService.deleteById(id);
            model.addAttribute("message", "Delete Successful");
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getLocalizedMessage());
        }
        model.addAttribute("documents", documentService.findAll());
        return "list.html";
    }

    @GetMapping("/document/addTag/{id}/{tag}")
    public String addTagToDocument(Model model, @PathVariable Integer id, @PathVariable String tag){
        Document documentobj = documentService.findById(id);
        Tag tagobj = tagService.findById(tag);
        documentobj.addTag(tagobj);
        try {
            documentService.save(documentobj);
            model.addAttribute("tag_message", "Save Successful");
        } catch (Exception e) {
            model.addAttribute("tag_error", "Error occurred: " + e.getLocalizedMessage());
        }
        return showDocument(model, id);
    }

    @GetMapping("/document/deleteTag/{id}/{tag}")
    public String removeTagFromDocument(Model model, @PathVariable Integer id, @PathVariable String tag){
        Document documentobj = documentService.findById(id);
        Tag tagobj = tagService.findById(tag);
        documentobj.removeTag(tagobj);
        try {
            documentService.save(documentobj);
            model.addAttribute("tag_message", "Save Successful");
        } catch (Exception e) {
            model.addAttribute("tag_error", "Error occurred: " + e.getLocalizedMessage());
        }
        return showDocument(model, id);
    }


}
