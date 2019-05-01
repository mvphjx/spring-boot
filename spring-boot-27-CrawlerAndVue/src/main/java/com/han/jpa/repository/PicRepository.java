package com.han.jpa.repository;

import com.han.jpa.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PicRepository extends JpaRepository<Picture, Long>
{
}
