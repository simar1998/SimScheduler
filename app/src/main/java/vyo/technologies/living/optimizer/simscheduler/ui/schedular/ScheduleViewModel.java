package vyo.technologies.living.optimizer.simscheduler.ui.schedular;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScheduleViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ScheduleViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Schedule fragment TODO");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
