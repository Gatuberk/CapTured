package co.edu.ue.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements UserDetails {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_user", nullable = false)
  private int idUser;

  @Enumerated(EnumType.STRING)
  @Column(name = "usr_active", nullable = false)
  private Status usrActive;

  @Column(name = "usr_apellidos", nullable = false)
  private String usrApellidos;

  @Column(name = "usr_direccion", nullable = false)
  private String usrDireccion;

  @Column(name = "usr_email", nullable = false)
  private String usrEmail;

  @Column(name = "usr_fechaInicio")
  private Date usrFechaInicio;

  @Column(name = "usr_identificacion", nullable = false)
  private String usrIdentificacion;

  @Column(name = "usr_nombres", nullable = false)
  private String usrNombres;

  @Column(name = "usr_password", nullable = false)
  private String usrPassword;

  @Column(name = "usr_telefono", nullable = false)
  private String usrTelefono;

  @Column(name = "usr_username", nullable = false)
  private String usrUsername;

  @Column(name = "role", nullable = false)
  @Enumerated(EnumType.STRING)
  private Role role;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.toString()));
  }

  @Override
  public String getPassword() {
    return this.usrPassword;
  }

  @Override
  public String getUsername() {
    return this.usrUsername;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public enum Status {
    activo,
    suspendido,
    baneado,
  }

  public enum Role {
    ADMIN,
    USER,
  }
}
