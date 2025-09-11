package br.com.mateus.TodoList.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {
    @Id // definindo a chave primaria
    @GeneratedValue(strategy = GenerationType.UUID) // definindo que o id sera gerado automaticamente
    private UUID id;
    private String description;

    @Column(length = 50) // definindo o tamanho maximo do titulo
    @NotBlank(message = "O título é obrigatório") // validação para garantir que o título não seja nulo ou vazio
    private String title;

    @NotNull(message = "A data de início é obrigatória") // validação para garantir que a data de início não seja nula
    @Future(message = "A data de início deve ser futura") // validação para garantir que a data de início seja futura
    private LocalDateTime startAt;

    @NotNull(message = "A data de término é obrigatória") // validação para garantir que a data de término não seja nula
    private LocalDateTime endAt;

    private String priority;

    private UUID idUser; // Relacionamento com o usuário (id do usuário)

    @CreationTimestamp // definindo que a data de criacao sera gerada automaticamente
    private LocalDateTime createdAt;

    @AssertTrue(message = "A data de término deve ser posterior à data de início")
    private boolean isEndAtAfterStartAt() {
        if (startAt == null || endAt == null) {
            return true; // A validação de @NotNull cuidará desses casos
        }
        return endAt.isAfter(startAt);
    }

}
