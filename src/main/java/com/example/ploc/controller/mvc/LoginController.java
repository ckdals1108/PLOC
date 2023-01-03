package com.example.ploc.controller.mvc;

import com.example.ploc.domain.Identity;
import com.example.ploc.domain.Login;
import com.example.ploc.domain.Teacher;
import com.example.ploc.dto.LoginDTO;
import com.example.ploc.dto.LoginEditDTO;
import com.example.ploc.dto.LoginFormDTO;
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
    HttpSession session;

    @ModelAttribute("identityTypes")
    public Identity[] identityTypes() {
        return Identity.values();
    }

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(defaultValue="/") String redirectURL,
                        @ModelAttribute LoginDTO loginDTO,
                        HttpServletRequest request){
        Login login = loginService.join(loginDTO);
        session = request.getSession();
        session.setAttribute("loginId", login.getId());
        return "redirect:" + redirectURL;
    }

    @GetMapping("/login/signup")
    public String signUpForm(Model model){
        model.addAttribute("login",new LoginFormDTO());
        model.addAttribute("id",null);
        return "signup";
    }

    @PostMapping("/login/signup")
    public String signUp(@Valid @ModelAttribute("login") LoginFormDTO loginFormDTO,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
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
            Login login = loginService.create(loginFormDTO.getLogin(loginFormDTO));
        }
        else {
            Teacher login = teacherService.create(loginFormDTO);
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/";
    }

    @GetMapping("/withdrawal")
    public String withdrawal(HttpServletRequest request){
        session = request.getSession(false);
        Long loginId = (Long)session.getAttribute("loginId");
        loginService.withdrawal(loginId);
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/login/edit")
    public String editForm(HttpServletRequest request, Model model)
    {
        Long loginId = (Long)request.getSession(false).getAttribute("loginId");
        LoginEditDTO user = loginService.loginDetail(loginId);
        model.addAttribute("login",user);
        model.addAttribute("id",loginId);
        return "edit";
    }

    @ResponseBody
    @GetMapping("/idPhoto/{fileName}")
    public Resource downloadFile(@PathVariable String fileName) throws MalformedURLException {
        return new UrlResource("file:" + idPhotoFileStore.getFullPath(fileName));
    }

    @GetMapping("/attach/{id}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long id) throws MalformedURLException {
        Teacher teacher = teacherService.findWithIdPhotoFile(id);
        String upLoadFileName = teacher.getIdPhotoFile().getUpLoadFileName();
        String storeFileName = teacher.getIdPhotoFile().getStoreFileName();
        String filePath = teacher.getIdPhotoFile().getFilePath();

        UrlResource resource = new UrlResource("file:" + filePath + storeFileName);

        String encodeUploadFileName = UriUtils.encode(upLoadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodeUploadFileName + "\"";
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }

    @PostMapping("/login/edit")
    public String edit(@ModelAttribute LoginFormDTO loginFormDTO,
                       HttpSession session,
                       Model model){
        Long loginId = (Long)session.getAttribute("loginId");
        loginService.loginEdit(loginId, loginFormDTO);
        return "redirect:/";
    }
}
