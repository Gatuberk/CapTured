package co.edu.ue.Auth;

import co.edu.ue.Jwt.JwtService;
import co.edu.ue.dto.LoginRequest;
import co.edu.ue.dto.RegisterRequest;
import co.edu.ue.model.User;
import co.edu.ue.model.User.Role;
import co.edu.ue.model.User.Status;
import co.edu.ue.service.IUserService;
import java.time.Instant;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

  private final IUserService userService;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authManager;

  @Override
  public AuthResponse login(LoginRequest request) {
    System.out.println(
      "Contraseña en la DB" + userService.getByUsername(request.username())
    );
    System.out.println("Contraseña que se escribio" + request.password());
    UsernamePasswordAuthenticationToken usrAnPswr =
      new UsernamePasswordAuthenticationToken(
        request.username(),
        request.password()
      );
    System.out.println(usrAnPswr.toString());
    try {
      authManager.authenticate(usrAnPswr);
    } catch (RuntimeException e) {
      throw new RuntimeException("Authentication failed", e);
    }
    UserDetails user = userService
      .getByUsername(request.username())
      .orElseThrow(() -> new UsernameNotFoundException(request.username()));
    String token = jwtService.getToken(user);
    return AuthResponse.builder().token(token).build();
  }

  @Override
  public AuthResponse register(RegisterRequest request) {
    if (!userService.existsByUsername(request.username())) {
      User user = User.builder()
        .usrUsername(request.username())
        .usrPassword(passwordEncoder.encode(request.password()))
        .usrEmail(request.email())
        .usrNombres(request.names())
        .usrApellidos(request.lastname())
        .usrTelefono(request.phone())
        .usrDireccion(request.address())
        .usrIdentificacion(request.document())
        .role(Role.USER)
        .usrActive(Status.activo)
        .usrFechaInicio(Date.from(Instant.now()))
        .build();

      AuthResponse token = AuthResponse.builder()
        .token(jwtService.getToken(user))
        .build();
      if (token == null) {
        throw new RuntimeException("Error creating token");
      } else {
        userService.addUser(user);
        return token;
      }
    } else {
      throw new RuntimeException("User already exists");
    }
  }
}
