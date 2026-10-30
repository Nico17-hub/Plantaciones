package ar.com.plantaciones.servicesimp;

import ar.com.plantaciones.dao.IUsuariosDao;
import ar.com.plantaciones.entities.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUsuariosDao usuariosDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios u = usuariosDao.findOneByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        // "Administrador" -> ROLE_ADMINISTRADOR, "Invitado" -> ROLE_INVITADO, etc.
        String rol = u.getRol().getDescrip().toUpperCase().replace(" ", "_");

        return User.withUsername(u.getUsername())
                .password("{noop}" + u.getPassword())
                .roles(rol)
                .disabled(Boolean.TRUE.equals(u.getInactivo()))
                .build();
    }
}
