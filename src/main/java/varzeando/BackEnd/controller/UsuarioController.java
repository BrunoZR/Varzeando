package varzeando.BackEnd.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import varzeando.BackEnd.dto.RequestCadastro;
import varzeando.BackEnd.dto.RequestLogin;
import varzeando.BackEnd.dto.RequestSegundoCadastro;
import varzeando.BackEnd.models.Usuario;
import varzeando.BackEnd.services.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("usuario")
@Log4j2
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    @PostMapping(path = "/login")
    public ResponseEntity<Usuario> login(@RequestBody RequestLogin requestLogin){
        if(usuarioService.autenticar(requestLogin))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PostMapping(path = "/cadastro")
    public ResponseEntity<Usuario> cadastro(@RequestBody RequestCadastro requestCadastro){
        return new ResponseEntity<>(usuarioService.salvar(requestCadastro), HttpStatus.CREATED);
    }
 
    @GetMapping
    public ResponseEntity<List<Usuario>> list(){
        return ResponseEntity.ok(usuarioService.listAll());
    }
    @PutMapping(path = "/segundocadastro")
    public ResponseEntity<Usuario> segundocadastro(@RequestBody RequestSegundoCadastro requestSegundoCadastro){
        return new ResponseEntity<>(usuarioService.salvardois(requestSegundoCadastro), HttpStatus.CREATED);
    }
}