package co.edu.ue.controller.client;

import co.edu.ue.model.User;
import co.edu.ue.service.IUserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("clientUserController")
@RequestMapping(value = "api/usr")
@CrossOrigin(origins = "*")
public class UserController {

  //KeyCloak

  @Autowired
  IUserService service;

  @GetMapping(value = "user/username={username}")
  public ResponseEntity<Optional<User>> getUserByUsername(
    @PathVariable("username") String username
  ) {
    if (service.existsByUsername(username)) {
      return new ResponseEntity<>(
        service.getByUsername(username),
        HttpStatus.ACCEPTED
      );
    } else {
      return new ResponseEntity<>(
        service.getByUsername(username),
        HttpStatus.NOT_FOUND
      );
    }
  }

  @PutMapping(
    value = "user/{usrnm}",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<User> updtUser(
    @PathVariable("usrnm") String username,
    @RequestBody User newUpdatedUser
  ) {
    User user = service.upUser(username, newUpdatedUser);
    return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
  }

  @PutMapping(
    value = "user/active/{usrnm}",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<User> inactiveUser(
    @PathVariable("usrnm") String username,
    @RequestBody User active
  ) {
    User user = service.statusUser(username, active);
    return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
  }

  @PutMapping(value = "user/password/{usrnm}")
  public ResponseEntity<User> passwordUser(
    @PathVariable("usrnm") String username,
    @RequestBody User password
  ) {
    User user = service.upUser(username, password);
    return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGeneralException(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
      "Error interno del servidor: " + ex.getMessage()
    );
  }
}
