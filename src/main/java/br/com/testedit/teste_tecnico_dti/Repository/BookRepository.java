package br.com.testedit.teste_tecnico_dti.repository;

import br.com.testedit.teste_tecnico_dti.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

}
