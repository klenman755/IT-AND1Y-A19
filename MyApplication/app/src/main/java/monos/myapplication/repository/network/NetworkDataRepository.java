package monos.myapplication.repository.network;

import androidx.lifecycle.LiveData;

import java.util.List;

import monos.myapplication.model.PLU;
import monos.myapplication.model.PluGroup;

public class NetworkDataRepository {



    private PLUDao pluDao;
    private PLUGroupDao pluGroupDao;
    private static NetworkDataRepository instance;

    private NetworkDataRepository(){


        pluDao = PLUDao.getInstance();
        pluGroupDao = PLUGroupDao.getInstance();
    }

    public static NetworkDataRepository getInstance(){
        if(instance == null)
            instance = new NetworkDataRepository();

        return instance;
    }



    //------------------PLUS----------------------//
    public LiveData<List<PLU>> getAllPLUs(){
        return pluDao.getAllPLUs();
    }


    //------------------PLU GROUPS----------------------//
    public LiveData<List<PluGroup>> getAllPLUGroups(){
        return pluGroupDao.getAllPLUGroups();
    }




}
