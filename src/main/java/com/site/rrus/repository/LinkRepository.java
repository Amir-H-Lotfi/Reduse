package com.site.rrus.repository;

import com.site.rrus.hash.Link;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends CrudRepository<Link , String> {
}
