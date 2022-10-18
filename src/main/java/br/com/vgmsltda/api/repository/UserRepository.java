package br.com.vgmsltda.api.repository;

import br.com.vgmsltda.api.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


}
