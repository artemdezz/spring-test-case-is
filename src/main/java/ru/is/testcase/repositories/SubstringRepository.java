package ru.is.testcase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.is.testcase.model.Substring;

@Repository
public interface SubstringRepository extends JpaRepository<Substring, Long> {
}
