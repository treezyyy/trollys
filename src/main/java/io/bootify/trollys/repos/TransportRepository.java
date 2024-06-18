package io.bootify.trollys.repos;

import io.bootify.trollys.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportRepository extends JpaRepository<Transport,String> {

}
