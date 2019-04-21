package cn.han.repository.one2one;

import cn.han.model.one2one.User;
import cn.han.repository.BasicRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>
{


}
