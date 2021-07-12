package varzeando.BackEnd.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import varzeando.BackEnd.dto.RequestCadastro;
import varzeando.BackEnd.dto.RequestLogin;
import varzeando.BackEnd.dto.RequestSegundoCadastro;
import varzeando.BackEnd.models.Usuario;
import varzeando.BackEnd.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService{
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public boolean autenticar(RequestLogin requestLogin){
        Usuario user =usuarioRepository.findByEmail(requestLogin.getEmail());
        if(passwordEncoder.matches(requestLogin.getPassword(),user.getPassword()))
            return true;
        else
            return false;
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
               .endereco(null)
               .posicao(null).build());
        else throw new RuntimeException();



    }
    public Usuario salvardois(RequestSegundoCadastro requestSegundoCadastro){
        Usuario user=usuarioRepository.findByEmail(requestSegundoCadastro.getEmail());
        Usuario userFim= usuarioRepository.save(Usuario.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .dataNascimento(user.getDataNascimento())
                .endereco(requestSegundoCadastro.getEndereco())
                .posicao(requestSegundoCadastro.getPosicao()).build());
        usuarioRepository.delete(user);
        return userFim;
    }
    public Integer verificaridade(Date data) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String x= sdf.format(data);
        Date y=sdf.parse(x);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(y);
        int Anodata =calendar.get(calendar.YEAR);
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

    }
