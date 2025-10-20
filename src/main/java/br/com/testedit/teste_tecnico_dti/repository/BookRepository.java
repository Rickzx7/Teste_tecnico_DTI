package br.com.testedit.teste_tecnico_dti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.testedit.teste_tecnico_dti.entities.Book;
@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

}
