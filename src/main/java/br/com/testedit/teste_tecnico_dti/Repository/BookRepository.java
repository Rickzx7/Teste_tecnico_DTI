package br.com.testedit.teste_tecnico_dti.Repository;

import br.com.testedit.teste_tecnico_dti.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

}
