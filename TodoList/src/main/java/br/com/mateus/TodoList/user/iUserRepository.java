package br.com.mateus.TodoList.user;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByUsername(String username); // Método para encontrar um usuário pelo nome de usuário

    UserModel findByUsernameOremaiModel(String username, String email); // criado o metodo para buscar pelo username ou
                                                                        // email do usuario
}
