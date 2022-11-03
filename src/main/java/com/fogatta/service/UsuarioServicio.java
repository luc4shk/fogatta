package com.fogatta.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fogatta.model.Rol;
import com.fogatta.model.Usuario;
import com.fogatta.repositorios.UsuarioRepositorio;

import net.bytebuddy.utility.RandomString;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio repo;

    @Autowired
    JavaMailSender mailSender;

    public void register(Usuario usuario, String siteURL) throws UnsupportedEncodingException, MessagingException{
        
        encondedPassword(usuario);
        usuario.setRol(Rol.USER);

        String randomCode = RandomString.make(64);
        usuario.setVerificationCode(randomCode);

        usuario.setEnabled(false);

        repo.save(usuario);

        sendVerificationEmail(usuario, siteURL);

    }

    private void sendVerificationEmail(Usuario usuario, String siteURL) throws UnsupportedEncodingException, MessagingException{

        String asunto = "Favor verificar su registro";
        String autor = "Fogatta Team";
        String mailContent = "<p>Estimado " + usuario.getNombre() + " " + usuario.getApellido() + ",</p>";
        mailContent += "<p>Dar click en el link de abajo para verificar su registro:</p>";

        String verifyURL = siteURL + "/verify?code=" + usuario.getVerificationCode();

        mailContent += "<h3><a href=\"" + verifyURL + "\">VERIFICAR</a></h3>";

        mailContent += "<p>Muchas Gracias<br>The Fogatta team</p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("fogattateam@gmail.com", autor);
        helper.setTo(usuario.getEmail());
        helper.setSubject(asunto);

        helper.setText(mailContent, true);
        mailSender.send(message);

    }

    private void encondedPassword(Usuario usuario){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(usuario.getPassword());
        usuario.setPassword(encodedPassword);

    }

    public boolean isEmailUnique(String email){

        Usuario existUser = repo.findByEmail(email);
        return existUser == null;

    }

    public Usuario getUserByEmail(String email){
        return repo.findByEmail(email);
    }

    @Transactional 
    public boolean verify(String verificationCode){
        Usuario user = repo.findByVerificationCode(verificationCode);

        // verifica si el usuario ya fue verificado
        if (user == null || user.isEnabled()) {
            return false;
        } else {
            repo.enable(user.getId());
            return true;
        }
    }

    public void updateResetPasswordToken(String token, String email) throws UserNotFoundException{

        Usuario usuario = repo.findByEmail(email);

        if(usuario != null){
            usuario.setResetPasswordToken(token);
            repo.save(usuario);
        }
        else{
            throw new UserNotFoundException("No se pudo encontrar ningun usuario con el email " + email);
        }

    }

    public Usuario get(String resetPasswordToken){
        return repo.findByResetPasswordToken(resetPasswordToken);
    }

    public void updatePassword(Usuario usuario, String newPassword){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(newPassword);
        usuario.setPassword(encodedPassword);

        usuario.setResetPasswordToken(null);
        repo.save(usuario);
        
    }
    
}
