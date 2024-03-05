package project.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.backoffice.dto.ProductDTO;
import project.backoffice.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
            SELECT  p
            FROM Product p
            WHERE p.firmware.id = :id
      """)
    Product findByFirmwareId(Long id);
}
