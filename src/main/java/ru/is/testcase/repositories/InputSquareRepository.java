package ru.is.testcase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.is.testcase.model.InputSquare;

@Repository
public interface InputSquareRepository extends JpaRepository<InputSquare, Long> {
}
