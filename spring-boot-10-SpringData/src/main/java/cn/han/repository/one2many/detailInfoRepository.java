package cn.han.repository.one2many;

import cn.han.model.one2many.detailInfo;
import cn.han.repository.BasicRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface detailInfoRepository extends JpaRepository<detailInfo, Long>
{

}
