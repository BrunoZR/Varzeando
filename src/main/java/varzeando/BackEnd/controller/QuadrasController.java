package varzeando.BackEnd.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import varzeando.BackEnd.dto.request.RequestQuadras;
import varzeando.BackEnd.dto.response.ResponseQuadra;
import varzeando.BackEnd.models.Quadra;
import varzeando.BackEnd.services.QuadrasService;

import java.util.List;

@RestController
@RequestMapping("quadras")
@Log4j2
@RequiredArgsConstructor
public class QuadrasController {
    private final QuadrasService quadrasService;
    @GetMapping(path = "/quadras")

    public ResponseEntity<List<Quadra>> list(){
        return ResponseEntity.ok(quadrasService.listAll());
    }
    @GetMapping(path = "/quadrasproximas")

//    public ResponseEntity<List<ResponseQuadra>> listProximas(){
//        return ResponseEntity.ok(quadrasService.listarQuadras(-23.634879435532486,-46.75697623095011));
//    }

    @PostMapping(path = "/cadastro")
    public ResponseEntity<Quadra> cadastro(@RequestBody RequestQuadras requestQuadras) {
        return new ResponseEntity<>(quadrasService.salvar(requestQuadras), HttpStatus.CREATED);
    }
}