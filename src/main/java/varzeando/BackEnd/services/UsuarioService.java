package varzeando.BackEnd.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import varzeando.BackEnd.dto.request.RequestCadastro;
import varzeando.BackEnd.dto.request.RequestLogin;
import varzeando.BackEnd.dto.request.RequestSegundoCadastro;
import varzeando.BackEnd.exception.BadRequestException;
import varzeando.BackEnd.models.Usuario;
import varzeando.BackEnd.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService{
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public Usuario autenticar(RequestLogin requestLogin) throws Exception {
        Usuario user =usuarioRepository.findByEmail(requestLogin.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ("usuario não encontrado")));
        if(passwordEncoder.matches(requestLogin.getPassword(),user.getPassword()))
            return user;
        else
            throw new Exception("Senha incorreta");
    }
    public List<Usuario> listAll(){
        return usuarioRepository.findAll();
    }
    public Usuario salvar(RequestCadastro requestCadastro){
        String passwordEncoded=passwordEncoder.encode(requestCadastro.getPassword());
        if(!(usuarioRepository.existsByEmail(requestCadastro.getEmail())))
       return usuarioRepository.save(Usuario.builder()
               .name(requestCadastro.getName())
               .email(requestCadastro.getEmail())
               .password(passwordEncoded)
               .dataNascimento(requestCadastro.getDataNascimento())
               .latitude((double) 0)
               .longitude((double) 0)
               .posicao(null).build());
        else throw new BadRequestException("Valores ivalidos");
    }
    public Usuario salvardois(RequestSegundoCadastro requestSegundoCadastro){
        Usuario user=usuarioRepository.findByEmail(requestSegundoCadastro.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, ("valores ivalidos")));
        user.setLatitude(requestSegundoCadastro.getLatitude());
        user.setLongitude(requestSegundoCadastro.getLongitude());
        user.setPosicao(requestSegundoCadastro.getPosicao());
        return user;
    }

    public Integer verificaridade(Date data) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String x= sdf.format(data);
        Date y=sdf.parse(x);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(y);
        int Anodata =calendar.get(calendar.YEAR)-1;
        int Mesdata =calendar.get(calendar.MONTH)+1;
        int Diadata =calendar.get(calendar.DATE);
      LocalDate localDate=LocalDate.of(Anodata,Mesdata,Diadata);
      LocalDate dataAtual=LocalDate.now();
        Period period=Period.between(localDate,dataAtual);
        if(period.getYears()>18){
            return period.getYears();
        }
        else
            throw new RuntimeException();
    }

    public String findUsuario(Long id){
        String primeiroNome=usuarioRepository.findById(id).orElseThrow(()->new BadRequestException("Usuario não encontrado")).getName();
        return primeiroNome.substring(0,primeiroNome.indexOf(" "));
    }

    }
