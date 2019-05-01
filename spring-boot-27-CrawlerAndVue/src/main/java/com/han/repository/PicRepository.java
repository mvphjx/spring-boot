package com.han.repository;

import com.han.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PicRepository extends JpaRepository<Picture, Long>
{
}
