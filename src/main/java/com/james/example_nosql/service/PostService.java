package com.james.example_nosql.service;

import com.james.example_nosql.model.Post;
import com.james.example_nosql.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    public Post editPost(String postId, Post post) {
        Post editedPost = getPostsById(postId);
        if (editedPost != null) {
            editedPost.setTitle(post.getTitle());
            editedPost.setBody(post.getBody());
            postRepository.save(editedPost);
            return editedPost;
        }
        return null;
    }

    private Post getPostsById(String postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        return optionalPost.orElse(null);
    }

    public boolean deletePost(String postId) {
        Post deletedPost = getPostsById(postId);
        if (deletedPost == null) {
            return false;
        }
        try {
            postRepository.delete(deletedPost);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
