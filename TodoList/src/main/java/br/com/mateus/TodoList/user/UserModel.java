package br.com.mateus.TodoList.user;

import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity(name = "tb_users")
public class UserModel {
    @Id // definindo a chave primaria
    @GeneratedValue(strategy = GenerationType.UUID) // definindo que o id sera gerado automaticamente
    private UUID id;

    private String name;

    @Column(unique = true) // definindo que o username sera unico no banco de dados
    private String username;
    private String password;

    // definindo que a data de criacao sera gerada automaticamente
    @CreationTimestamp
    private LocalDateTime createdAt;
}
