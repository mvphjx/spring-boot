package cn.han.repository;

import cn.han.model.Team;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface  TeamPagingAndSortRespository extends PagingAndSortingRepository<Team,Integer>
{
}
