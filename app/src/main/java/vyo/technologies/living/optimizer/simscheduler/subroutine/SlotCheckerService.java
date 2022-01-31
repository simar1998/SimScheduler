package vyo.technologies.living.optimizer.simscheduler.subroutine;

import static androidx.core.app.NotificationCompat.PRIORITY_HIGH;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import vyo.technologies.living.optimizer.simscheduler.R;
import vyo.technologies.living.optimizer.simscheduler.VALS;
import vyo.technologies.living.optimizer.simscheduler.schedule.ScheduleManager;
import vyo.technologies.living.optimizer.simscheduler.schedule.ScheduleProto;

public class SlotCheckerService extends Service {

    private static Timer timer = new Timer();

    private long delay;
    private ScheduleManager manager;

    public SlotCheckerService(long delay, ScheduleManager manager) {
        this.delay = delay;
        this.manager = manager;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        //ADD SLOT CHECKER METHOD INVOCKATION HERE
        timer.scheduleAtFixedRate(new SlotChecker(manager,this),0,delay);
        return super.onStartCommand(intent,flags,startId);
    }

    private class SlotChecker extends TimerTask{

        private ScheduleManager manager;
        private Context context;

        public SlotChecker(ScheduleManager manager, Context context){
          this.manager = manager;
          this.context = context;
        }

        @Override
        public void run() {
            try{
                ScheduleProto.ScheduleSlot slot = manager.getScheduleCurrentSlot();
                if (slot != null){
                    displayNotification(slot,context);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        private void displayNotification(ScheduleProto.ScheduleSlot slot, Context context){

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, VALS.NOTIF_CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_notification_ss)
                    .setContentTitle(slot.getSlotName() + getTimeSlotStr(slot))
                    .setContentText(slot.getSlotDesc())
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat nManager = NotificationManagerCompat.from(context);
            nManager.notify(1,mBuilder.build());
        }

        private String getTimeSlotStr(ScheduleProto.ScheduleSlot slot){
            StringBuilder builder = new StringBuilder();
            builder.append("From : H"+slot.getStartTime().getHour()+" | M"+slot.getStartTime().getMin() + " ");
            builder.append("To : H"+slot.getEndTime().getHour()+" | M"+slot.getEndTime().getMin());
            return builder.toString();
        }
    }
}
