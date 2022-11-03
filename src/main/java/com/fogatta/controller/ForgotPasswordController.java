package com.fogatta.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fogatta.model.Usuario;
import com.fogatta.service.UserNotFoundException;
import com.fogatta.service.UsuarioServicio;
import com.fogatta.utility.Utility;

import net.bytebuddy.utility.RandomString;

@Controller
public class ForgotPasswordController {

    @Autowired
    UsuarioServicio usuarioServicio;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/forgot_password")
    public String showForgotPassword(){
        return "olvidar_password_form";
    }

    @PostMapping("/forgot_password")
    public String processForgotPasswordForm(HttpServletRequest request, Model model){

        String email = request.getParameter("email");
        String token = RandomString.make(45);

        try {

            usuarioServicio.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
            enviarEmail(email, resetPasswordLink);
            model.addAttribute("message", "Hemos enviado un link para cambiar tu contraseña a tu email. Por favor revisa tu correo");

        } catch (UserNotFoundException e) {
            model.addAttribute("error", e.getMessage());
        } catch (UnsupportedEncodingException | MessagingException  ex) {
            model.addAttribute("error", "Error al enviar email.");
        } 

        return "olvidar_password_form";

    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model){

        Usuario user = usuarioServicio.get(token);

        if(user == null){
            model.addAttribute("title", "Cambiar contraseña");
            model.addAttribute("message", "token inválido");
            return "message";
        }

        model.addAttribute("token", token);
        model.addAttribute("pageTitle", "Cambia tu contraseña");

        return "reset_password_form";

    }


    @PostMapping("reset_password")
    public String processResetPassword(HttpServletRequest request, Model model){

        String token = request.getParameter("token");
        String password = request.getParameter("password");

        Usuario user = usuarioServicio.get(token);

        if(user == null){
            model.addAttribute("title", "Cambiar contraseña");
            model.addAttribute("message", "token inválido");
        }else{
            usuarioServicio.updatePassword(user, password);
            model.addAttribute("message", "Haz cambiado tu contraseña de manera satisfactoria");
        }

        return "message";

    }


    private void enviarEmail(String email, String resetPasswordLink) throws UnsupportedEncodingException, MessagingException{

        MimeMessage message = mailSender.createMimeMessage();              
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("fogattateam@gmail.com", "Fogatta Support");
        helper.setTo(email);

        String subject = "Este es el link para cambiar tu contraseña";
        String content = "<p>Hola,</p>"
                + "<p>Has solicitado un cambio de contraseña</p>"
                + "<p>Ingresa en el link debajo para cambiar tu contaseña</p>"
                + "<p><a href=\"" + resetPasswordLink + "\">Cambiar mi contraseña</a></p>"
                + "<p>Ignore este mensaja si recuerda su constraseña, o no ha realizado una solicitud para cambiarla</p>";
        
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);

    }


}