package br.com.lifeos.user.domain.repository;

import br.com.lifeos.user.domain.entities.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped /* define um bean com escopo de aplicação, significando que apenas uma
// única instância é criada e
// compartilhada por todos os usuários e sessões durante todo o tempo de execução da aplicação */
public class UserReposity implements PanacheRepository<User> {
}
