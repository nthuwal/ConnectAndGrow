package com.connectAndGrow.post_service.service;


import com.connectAndGrow.post_service.dto.PostCreateRequestDto;
import com.connectAndGrow.post_service.dto.PostDto;
import com.connectAndGrow.post_service.entity.Post;
import com.connectAndGrow.post_service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostDto getPostById(Long id) {
        Optional<Post> postById = postRepository.findById(id);
        return modelMapper.map(postById, PostDto.class);
    }

    public List<PostDto> getAllPostsOfUser(Long userId) {
        List<Post> allPosts = postRepository.findByUserId(userId);
        return allPosts.stream()
                .map(item -> modelMapper.map(allPosts, PostDto.class))
                .toList();
    }

    public PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId) {
        Post post = modelMapper.map(postCreateRequestDto, Post.class);
        post.setUserId(userId);
        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }
}
