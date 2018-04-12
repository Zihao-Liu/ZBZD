package cn.lzh.zbzd.service;

import java.util.List;

import cn.lzh.zbzd.model.Favourite;

public interface FavouriteService {
    public int insertFavourite(Favourite favourite);
    public Favourite getFavouriteById(long id);
    public List<Favourite> listFavouriteByUserId(long userId);
    public int deleteFavourite(long id);
}
