package com.dmclaughlin.chat.app.user.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepo extends CrudRepository<UserDao, Integer> {

  @Transactional
  UserDao getById(Integer id);

  @Transactional
  void deleteById(Integer id);
}
