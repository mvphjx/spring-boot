package com.han.jpa.repository;

import com.han.jpa.model.Team;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface  TeamPagingAndSortRespository extends PagingAndSortingRepository<Team,Integer>
{
}
