package vyo.technologies.living.optimizer.simscheduler.schedule;

import org.joda.time.DateTime;

import java.time.LocalDate;
import java.util.Calendar;

//Class will be used for the decoding of the protobuff message content
//Class will be used for the retriving of Schedular data from the decoded proto message
public class ScheduleManager {

    private ScheduleProto.Schedule _schedule;

    public ScheduleManager(String scheduleEncodedString){

    }

    public ScheduleProto.ScheduleSlot getScheduleID(int id){
        ScheduleProto.ScheduleSlot slot = null;
        for (ScheduleProto.ScheduleSlot index: _schedule.getSlotsList()) {
            if (index.getID() == id){
                slot = index;
                break;
            }
        }
        return slot;
    }

    public ScheduleProto.ScheduleSlot getScheduleCurrentSlot(){
        ScheduleProto.ScheduleSlot currentSlot = null;

        String day = LocalDate.now().getDayOfWeek().name();

        for (ScheduleProto.ScheduleSlot index : _schedule.getSlotsList()) {

            if (day.toUpperCase().substring(0, 3).equals(index.getDay().getValueDescriptor().getName())) {

                Calendar.Builder calBuilder = new Calendar.Builder();

                DateTime dt = new DateTime();

                calBuilder.setTimeOfDay(index.getStartTime().getHour(), index.getStartTime().getHour(), 0);
                Calendar startCal = calBuilder.build();

                calBuilder.setTimeOfDay(index.getEndTime().getHour(), index.getStartTime().getMin(), 0);
                Calendar endTime = calBuilder.build();

                calBuilder.setTimeOfDay(dt.getHourOfDay(),dt.getMinuteOfHour(),0);
                Calendar current = calBuilder.build();

                if (current.after(startCal.getTime()) && current.before(endTime.getTime())) {
                    currentSlot = index;
                    break;
                }
            }
        }
        return currentSlot;
    }


}
