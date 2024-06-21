package io.bootify.trollys.repos;

import io.bootify.trollys.entity.Equipment;
import io.bootify.trollys.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    List<Equipment> findByTransportVin(String transportVin);

  //  public List<Equipment> findByVin(String vin);

}
