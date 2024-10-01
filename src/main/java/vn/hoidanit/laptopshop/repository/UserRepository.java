package vn.hoidanit.laptopshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> { // CRUD : create, read, update, delete
    User save(User newUser); // newUser đặt là gì cũng đc
}
