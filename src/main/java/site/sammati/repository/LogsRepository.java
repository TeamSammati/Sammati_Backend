package site.sammati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.sammati.entity.Logs;

public interface LogsRepository extends JpaRepository<Logs,Integer> {
}
