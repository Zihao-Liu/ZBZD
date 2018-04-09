package cn.lzh.zbzd.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lzh.zbzd.dao.TagDao;
import cn.lzh.zbzd.model.Tag;
import cn.lzh.zbzd.service.TagService;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;

    @Override
    public List<Tag> listAllTopTag() {
        return listAllSubTag(1L);
    }

    @Override
    public List<Tag> listAllSubTag(long fatherId) {
        return tagDao.listSubTagByFatherId(fatherId);
    }

    @Override
    public Tag getFatherTag(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Tag> listAllTag() {
        return tagDao.listAllTag();
    }

    @Override
    public Tag getTagById(long id) {
        return tagDao.getTagbyId(id);
    }

}
