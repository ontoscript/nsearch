package com.nsearch.hr.repository;

import com.nsearch.hr.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User>, PagingAndSortingRepository<User,String> {
//    CrudRepository<User, String>

}
