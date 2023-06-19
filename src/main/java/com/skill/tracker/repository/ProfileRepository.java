package com.skill.tracker.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import com.skill.tracker.model.Profile;

public interface ProfileRepository extends CassandraRepository<Profile, UUID> {
  @AllowFiltering
  
  List<Profile> findByCriteria(String name);
}
