package com.connectAndGrow.post_service.service;

import com.connectAndGrow.post_service.entity.Post;
import com.connectAndGrow.post_service.entity.PostLike;
import com.connectAndGrow.post_service.exceptions.BadRequestException;
import com.connectAndGrow.post_service.exceptions.ResourceNotFoundException;
import com.connectAndGrow.post_service.repository.PostLikeRepository;
import com.connectAndGrow.post_service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public void likePost(Long postId, Long userId) {

        log.info("Attempting to like a post with id: {}",postId);

        boolean exist = postRepository.existsById(postId);
        if(!exist) throw new ResourceNotFoundException("Post nod found with id:" + postId);

        boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);
        if(alreadyLiked) throw new BadRequestException("cannot like the same post again");

        PostLike postLike = new PostLike();
        postLike.setUserId(userId);
        postLike.setPostId(postId);
        postLikeRepository.save(postLike);

        log.info("Post with id: {} liked successfully", postId);
    }

    public void unlikePost(Long postId, Long userId) {

        log.info("Attempting to unlike post with id: {}",postId);

        boolean exists = postLikeRepository.existsByUserIdAndPostId(userId, postId);
        if(!exists) throw new BadRequestException("Post is not liked");

        postLikeRepository.deleteByUserIdAndPostId(userId, postId);
        log.info("post with id: {} unliked successfully", postId);
    }
}
