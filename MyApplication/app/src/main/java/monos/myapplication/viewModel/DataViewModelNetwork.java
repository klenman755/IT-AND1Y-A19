package monos.myapplication.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import monos.myapplication.model.PLU;
import monos.myapplication.model.PluGroup;
import monos.myapplication.repository.network.NetworkDataRepository;

public class DataViewModelNetwork extends ViewModel {

    private NetworkDataRepository repository;

    public DataViewModelNetwork() {
        repository = NetworkDataRepository.getInstance();
    }
    //------------------BILL ITEMS----------------------//



    //------------------PLUS----------------------//
    public LiveData<List<PLU>> getAllPLUs() {
        return repository.getAllPLUs();
    }


    //------------------PLU GROUPS----------------------//
    public LiveData<List<PluGroup>> getAllPLUGroups() {
        return repository.getAllPLUGroups();
    }




}
