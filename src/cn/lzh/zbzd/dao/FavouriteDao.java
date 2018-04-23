package cn.lzh.zbzd.dao;

import java.util.List;

import cn.lzh.zbzd.model.Favourite;

public interface FavouriteDao {
    public int insertFavourite(Favourite favourite);

    public Favourite getFavouriteById(long id);

    public List<Favourite> listFavouriteByUserId(long userId);

    public int deleteFavourite(long id);
}
