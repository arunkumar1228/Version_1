package com.web.Life.repository;


import com.web.Life.entity.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository extends JpaRepository<Text,Integer> {

}
