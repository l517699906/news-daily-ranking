package com.llf.dao.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llf.dao.entity.HotSearchDO;
import com.llf.dao.mapper.HotSearchMapper;
import com.llf.dao.repository.HotSearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public class HotSearchRepositoryImpl extends ServiceImpl<HotSearchMapper, HotSearchDO> implements HotSearchRepository {
}
