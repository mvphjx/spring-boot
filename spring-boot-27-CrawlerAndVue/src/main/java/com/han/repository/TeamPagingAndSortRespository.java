package com.han.repository;

import com.han.model.Team;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface  TeamPagingAndSortRespository extends PagingAndSortingRepository<Team,Integer>
{
}
