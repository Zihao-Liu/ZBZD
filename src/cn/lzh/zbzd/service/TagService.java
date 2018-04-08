package cn.lzh.zbzd.service;

import java.util.List;

import cn.lzh.zbzd.model.Tag;

public interface TagService {
    public List<Tag> listAllTag();
    public List<Tag> listAllTopTag();
    public List<Tag> listAllSubTag(long fatherId);
    public Tag getFatherTag(long id);
    public Tag getTagById(long id);
}
