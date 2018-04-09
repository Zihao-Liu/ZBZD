package cn.lzh.zbzd.dao;

import java.util.List;

import cn.lzh.zbzd.model.Tag;

public interface TagDao {
    public Tag getTagbyName(String name);

    public Tag getTagbyId(long id);

    public List<Tag> listSubTagByFatherId(long fatherId);

    public int deleteTagById(long id);

    public List<Tag> listAllTag();
}
