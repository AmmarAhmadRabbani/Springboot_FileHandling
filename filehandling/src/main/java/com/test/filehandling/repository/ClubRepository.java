package com.test.filehandling.repository;

import com.test.filehandling.model.Club;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club,Long> {

   Optional<Club> findByEmail(String email);
}
