package ru.is.testcase.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.is.testcase.model.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
}
