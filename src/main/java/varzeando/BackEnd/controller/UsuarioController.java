package varzeando.BackEnd.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import varzeando.BackEnd.dto.request.RequestCadastro;
import varzeando.BackEnd.dto.request.RequestLogin;
import varzeando.BackEnd.dto.request.RequestNome;
import varzeando.BackEnd.dto.request.RequestSegundoCadastro;
import varzeando.BackEnd.dto.response.ResponseNome;
import varzeando.BackEnd.models.Usuario;
import varzeando.BackEnd.services.UsuarioService;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("usuario")
@Log4j2
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    @PostMapping(path = "/login")
    public ResponseEntity<Usuario> login(@RequestBody RequestLogin requestLogin) throws Exception {
        return new ResponseEntity<>(usuarioService.autenticar(requestLogin),HttpStatus.OK);
    }
    @PostMapping(path = "/cadastro")
    public ResponseEntity<Usuario> cadastro(@RequestBody RequestCadastro requestCadastro) throws ParseException {
        usuarioService.verificaridade(requestCadastro.getDataNascimento());
        return new ResponseEntity<>(usuarioService.salvar(requestCadastro),HttpStatus.CREATED);
    }
 
    @GetMapping
    public ResponseEntity<List<Usuario>> list(){
        return ResponseEntity.ok(usuarioService.listAll());
    }
    @PutMapping(path = "/segundocadastro")
    public ResponseEntity<Usuario> segundocadastro(@RequestBody RequestSegundoCadastro requestSegundoCadastro){
        return new ResponseEntity<>(usuarioService.salvardois(requestSegundoCadastro), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{Id}")
    public ResponseEntity<ResponseNome> getNome(@PathVariable ("Id") Long Id){
        ResponseNome responseNome = new ResponseNome(usuarioService.findUsuario(Id));
        return ResponseEntity.ok(responseNome);
    }
}