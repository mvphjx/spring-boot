package cn.han.repository;

import cn.han.model.Team;
import cn.han.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long>
{
}
