package cn.han.repository;

import cn.han.model.Picture;
import cn.han.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PicRepository extends JpaRepository<Picture, Long>
{
}
