package br.com.mateus.TodoList.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.mateus.TodoList.user.iUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component // Anotação para registrar o filtro como um componente do Spring
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private iUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();

        // só intercepta as rotas /tasks
        if (servletPath.startsWith("/tasks")) {
            var authorization = request.getHeader("Authorization");

            if (authorization == null || !authorization.startsWith("Basic ")) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header ausente ou inválido");
                return;
            }

            var authEncoded = authorization.substring("Basic ".length()).trim();
            byte[] authDecode = Base64.getDecoder().decode(authEncoded);
            var authString = new String(authDecode);

            String[] credentials = authString.split(":", 2);
            if (credentials.length != 2) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de credenciais inválido");
                return;
            }

            String username = credentials[0];
            String password = credentials[1];

            // ----- VALIDAÇÃO DE USUARIO -----
            var user = this.userRepository.findByUsername(username);
            if (user == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuário não encontrado");
                return;
            }

            // ----- VALIDAÇÃO DE SENHA -----
            var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

            if (!passwordVerify.verified) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Senha inválida");
                return;
            }

            // Autenticado → segue a requisição
            request.setAttribute("idUser", user.getId());
        }

        filterChain.doFilter(request, response);
    }
}
