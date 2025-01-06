package com.shintadev.nyano.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shintadev.nyano.entity.feed.Feed;

public interface FeedRepository extends JpaRepository<Feed, Long> {

}
