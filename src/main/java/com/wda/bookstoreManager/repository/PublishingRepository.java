package com.wda.bookstoreManager.repository;

import com.wda.bookstoreManager.model.PublishingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishingRepository extends JpaRepository<PublishingEntity, Integer> {

    //Page<PublishingEntity> findAll(Pageable pageable);

    PublishingEntity findPublishingById(Integer id);

    void deleteById(Integer id);

   // PublishingEntity verifyExist(Long id);
}
