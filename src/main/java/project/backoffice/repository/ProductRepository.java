package project.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.backoffice.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {}
