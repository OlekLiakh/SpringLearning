package com.servingwebcontent.repo;


import com.servingwebcontent.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

}
