package cn.lzh.zbzd.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lzh.zbzd.dao.FavouriteDao;
import cn.lzh.zbzd.model.Favourite;
import cn.lzh.zbzd.service.FavouriteService;

@Service
public class FavouriteServiceImpl implements FavouriteService {
    @Autowired
    private FavouriteDao favouriteDao;
    
    @Override
    public int insertFavourite(Favourite favourite) {
        return favouriteDao.insertFavourite(favourite);
    }

    @Override
    public Favourite getFavouriteById(long id) {
        return favouriteDao.getFavouriteById(id);
    }

    @Override
    public List<Favourite> listFavouriteByUserId(long userId) {
        return favouriteDao.listFavouriteByUserId(userId);
    }

    @Override
    public int deleteFavourite(long id) {
        return favouriteDao.deleteFavourite(id);
    }

}
