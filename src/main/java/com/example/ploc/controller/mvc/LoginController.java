package com.example.ploc.controller.mvc;

import com.example.ploc.domain.IdPhotoFile;
import com.example.ploc.domain.Identity;
import com.example.ploc.domain.Login;
import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.login.LoginDTO;
import com.example.ploc.dto.login.LoginEditDTO;
import com.example.ploc.dto.login.LoginFormDTO;
import com.example.ploc.dto.login.LoginSaveDTO;
import com.example.ploc.exception.UserException;
import com.example.ploc.service.LoginService;
import com.example.ploc.service.TeacherService;
import com.example.ploc.service.file.IdPhotoFileStore;
import com.example.ploc.service.web.validator.LoginValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final LoginService loginService;
    private final TeacherService teacherService;
    private final LoginValidator loginValidator;
    private final IdPhotoFileStore idPhotoFileStore;

    @ModelAttribute("identityTypes")
    public Identity[] identityTypes() {
        return Identity.values();
    }

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("login", new LoginDTO(null, null));
        model.addAttribute("loginError",null);
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(defaultValue="/") String redirectURL,
                        @ModelAttribute("login") LoginDTO loginDTO, BindingResult bindingResult,
                        HttpSession session, Model model){
        Login login;
        try{
            login = loginService.join(loginDTO);
        }catch(UserException e) {
            model.addAttribute("loginError",e.getMessage());
            return "login";
        }

        session.setAttribute("loginId", login.getId());
        session.setAttribute("userName", login.getName());
        return "redirect:" + redirectURL;
    }

    @GetMapping("/login/signup")
    public String signUpForm(Model model){
        model.addAttribute("login",new LoginFormDTO());
        model.addAttribute("id",null);
        model.addAttribute("duplicate",null);
        return "signup";
    }

    @PostMapping("/login/signup")
    public String signUp(@Valid @ModelAttribute("login") LoginFormDTO loginFormDTO,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) throws IOException {
        if(loginFormDTO.getIdentity() == null){
            bindingResult.rejectValue("identity", "required");
        }

        if(loginFormDTO.getIdentity() == null || loginFormDTO.getIdentity().equals(Identity.TEACHER))
        {
            if(!StringUtils.hasText(loginFormDTO.getUniversity()) || !StringUtils.hasText(loginFormDTO.getSubject()))
            {
                loginValidator.validate(loginFormDTO, bindingResult);
            }
        }

        if (bindingResult.hasErrors()) {
            return "signup";
        }

        if(loginFormDTO.getIdentity().equals(Identity.STUDENT)) {
            try{
                Login login = loginService.create(loginFormDTO);
            }catch(UserException e){
                model.addAttribute("duplicate", e.getMessage());
                return "signup";
            }
        }
        else if(loginFormDTO.getIdentity().equals(Identity.TEACHER)){
           try{
               Teacher login = teacherService.create(loginFormDTO);
           }catch(UserException e){
               model.addAttribute("duplicate", e.getMessage());
               return "signup";
           }
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/";
    }

    @GetMapping("/withdrawal")
    public String withdrawal(HttpSession session){
        loginService.withdrawal((Long)session.getAttribute("loginId"));
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/login/edit")
    public String editForm(HttpSession session, Model model)
    {
        Long loginId = (Long)session.getAttribute("loginId");
        LoginEditDTO user = loginService.loginDetail(loginId);
        model.addAttribute("login",user);
        model.addAttribute("id",loginId);
        model.addAttribute("remove",false);
        model.addAttribute("duplicate",null);
        return "edit";
    }

    @PostMapping("/login/edit")
    public String edit(@Valid @ModelAttribute("login") LoginEditDTO loginEditDTO, BindingResult bindingResult,
                       @RequestParam("changeFile") MultipartFile changeFile,
                       @RequestParam(value="remove", defaultValue="false" ,required=false) Boolean remove,
                       Model model, HttpSession session) throws IOException {
        Long loginId = (Long)session.getAttribute("loginId");
        try{
            loginService.duplicateUserId(loginEditDTO.getUserId(), loginId);
        }catch(UserException e){
            model.addAttribute("duplicate", e.getMessage());
            return "edit";
        }

        if(bindingResult.hasErrors())
        {
            return "edit";
        }

        if(loginEditDTO.getIdentity() == Identity.STUDENT)
            loginService.edit(loginId, loginEditDTO);
        else
            teacherService.edit(loginId, loginEditDTO, changeFile, remove);
        return "redirect:/";
    }

    @ResponseBody
    @GetMapping("/idPhoto/{id}")
    public Resource downloadFile(@PathVariable Long id) throws MalformedURLException {
        IdPhotoFile idPhotoFile = idPhotoFileStore.findByUserId(id);
        return new UrlResource("file:" + idPhotoFileStore.getFilePath(idPhotoFile));
    }

   @GetMapping("/attach/{id}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long id) throws MalformedURLException {
       IdPhotoFile idPhotoFile = idPhotoFileStore.findByUserId(id);
       UrlResource resource = new UrlResource("file:" + idPhotoFileStore.getFilePath(idPhotoFile));

        String encodeUploadFileName = UriUtils.encode(idPhotoFile.getUpLoadFileName(), StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodeUploadFileName + "\"";

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }
}
