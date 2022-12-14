package dev.jpedrosnts.portifolio.controller.api;

import dev.jpedrosnts.portifolio.dto.form.EmailForm;
import dev.jpedrosnts.portifolio.model.Contato;
import dev.jpedrosnts.portifolio.repository.ContatoRepository;
import dev.jpedrosnts.portifolio.util.EnviarEmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/contato")
public class ContatoControllerApi {

    private final ContatoRepository service;
    private final EnviarEmailUtil emailUtil;
    private final String emailAdmin;

    @Autowired
    public ContatoControllerApi(ContatoRepository service, EnviarEmailUtil emailUtil, @Value("${email.admin}") String emailAdmin) {
        this.service = service;
        this.emailUtil = emailUtil;
        this.emailAdmin = emailAdmin;
    }

    @PostMapping
    public ResponseEntity<Contato> enviar(@Valid @RequestBody EmailForm form) {
        Contato contato = new Contato();
        contato.setEmail(form.getEmail());
        contato.setNome(form.getNome());
        contato.setMensagem(form.getMensagem());
        contato = service.save(contato);
        String assunto = "Portifólio - " + contato.getNome();
        String msg = "De: " + contato.getNome() + "\nEmail: " + contato.getEmail() + "\n\n" + contato.getMensagem();
        emailUtil.enviar(assunto, msg.toString(), new String[]{emailAdmin});
        return ResponseEntity.status(201).body(contato);
    }
}
