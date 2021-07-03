package com.kmitl.web_programming_2_63_final.repository;

import com.kmitl.web_programming_2_63_final.dto.InformationUserDTO;
import com.kmitl.web_programming_2_63_final.entity.InformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformationUserRepository extends JpaRepository<InformationEntity, Integer> {

    @Query(value = "select new com.kmitl.web_programming_2_63_final.entity.InformationEntity(id,firstname,lastname , phone, email,date,objectfail,status)from InformationEntity\n" +
            "where firstname like %:keyword% OR  lastname like %:keyword% OR date like %:keyword%")
    List<InformationEntity> findAllKeyWord(String keyword);


    List<InformationEntity> findAllByDateAndStatusOrderByDateAsc(String date , String status);

    @Query(value = "select new com.kmitl.web_programming_2_63_final.entity.InformationEntity(id,firstname,lastname , phone, email,date,objectfail,status)from InformationEntity\n" +
            "where date like %:dateq% and status = :status order by date asc")
    List<InformationEntity> findAllByMounthly(String dateq,@Param("status") String status);




}
