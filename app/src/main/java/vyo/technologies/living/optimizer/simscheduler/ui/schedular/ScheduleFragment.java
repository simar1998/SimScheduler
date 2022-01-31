package vyo.technologies.living.optimizer.simscheduler.ui.schedular;

import static androidx.core.app.NotificationCompat.PRIORITY_DEFAULT;
import static androidx.core.app.NotificationCompat.PRIORITY_HIGH;

import android.app.NotificationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import vyo.technologies.living.optimizer.simscheduler.R;
import vyo.technologies.living.optimizer.simscheduler.VALS;
import vyo.technologies.living.optimizer.simscheduler.databinding.FragmentHomeBinding;

public class ScheduleFragment extends Fragment {

    private ScheduleViewModel schedulerView;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

            final View view = inflater.inflate(R.layout.fragment_schedular,container,false);

            view.findViewById(R.id.button).setOnClickListener(view1 -> displayNotification());
            return view;
    }






    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void displayNotification(){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getActivity(), VALS.NOTIF_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_ss)
                .setContentTitle("NOTIFICATION TEST")
                .setContentText("TEST TEXT")
                .setPriority(PRIORITY_HIGH);
        NotificationManagerCompat nManager = NotificationManagerCompat.from(getActivity());
        nManager.notify(1,mBuilder.build());
    }
}

