package varzeando.BackEnd.services;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import varzeando.BackEnd.dto.request.RequestQuadras;
import varzeando.BackEnd.dto.response.ResponseQuadra;
import varzeando.BackEnd.models.Quadra;
import varzeando.BackEnd.repository.QuadrasRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class QuadrasService {
    private final QuadrasRepository quadrasRepository;

    public Quadra salvar(RequestQuadras requestQuadras){
        verificarPalavras(requestQuadras.getDescricao());
        verificarPalavras(requestQuadras.getName());

        return quadrasRepository.save(Quadra.builder()
                .descricao(requestQuadras.getDescricao())
                .latitude(requestQuadras.getLatitude())
                .longitude(requestQuadras.getLongitude())
                .name(requestQuadras.getName())
                .endereco(requestQuadras.getEndereco())
                .url(requestQuadras.getUrl())
                .build());
    }

    public List<Quadra> listAll(){
        return quadrasRepository.findAll();
    }

//    public List<ResponseQuadra> listarQuadras(Double latitude,Double longitude) {
//        List<Quadra> quadras = listAll();
//        List<Double> menoresdistancias = new ArrayList<>();
//        List<ResponseQuadra> listaQuadraProximas = new ArrayList<>();
//        Double menor=0.0;
//        boolean z=false;
//        for (Quadra x : quadras) {
//            menoresdistancias.add(Math.sqrt(((x.getLatitude() - latitude) * (x.getLatitude() - latitude)) + ((x.getLongitude() - longitude) * (x.getLongitude() - longitude))));
//
//        }
//        while (listaQuadraProximas.size() < 10) {
//
//            menor =Collections.min(menoresdistancias);
//            int x=menoresdistancias.indexOf(menor);
//        /*    do  {
//                int x=menoresdistancias.indexOf(menor);
//                if(x!=-1){
//                val quadra = quadras.get(x);
//                listaQuadraProximas.add(new ResponseQuadra(quadra.getName(),
//                        quadra.getDescricao(),
//                        quadra.getEndereco(),
//                        quadra.getUrl()));
//                menoresdistancias.remove(menor);}
//                else
//                   z=true;
//
//            }while(z!=true);*/
//
//            val quadra = quadras.get(x);
//            listaQuadraProximas.add(new ResponseQuadra(quadra.getName(),
//                    quadra.getDescricao(),
//                    quadra.getEndereco(),
//                    quadra.getUrl()));
//            menoresdistancias.remove(menor);
//       }
//        return listaQuadraProximas;
//    }

    public void verificarPalavras(String frase){
        String[] palavrasRuins={"piroca","buceta","poha","porra","caralho","krl","fdp","filha da puta","vadia","arrombado","cuzao","cuzão","cu","foda","merda","pirocao","pinto","rola","bucetão"};
        String x=frase.toLowerCase().replaceAll("1", "i").replaceAll("!", "i").replaceAll("3", "e").replaceAll("4", "a")
                .replaceAll("@", "a").replaceAll("5", "s").replaceAll("7", "t").replaceAll("0", "o").replaceAll("9", "g");
        for (int i=0;i<palavrasRuins.length;i++){
            if (x.contains(palavrasRuins[i]))
                throw new RuntimeException();
        }
    }
}