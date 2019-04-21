package cn.han.repository.one2one;

import cn.han.model.one2one.Address;
import cn.han.repository.BasicRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends BasicRepository<Address, Long>
{

}
