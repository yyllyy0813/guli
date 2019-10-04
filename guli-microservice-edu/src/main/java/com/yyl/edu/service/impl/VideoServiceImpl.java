package com.yyl.edu.service.impl;

import com.yyl.edu.entity.Video;
import com.yyl.edu.mapper.VideoMapper;
import com.yyl.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author yyl
 * @since 2019-09-16
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

}
