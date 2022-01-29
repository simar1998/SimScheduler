package vyo.technologies.living.optimizer.simscheduler.schedule;

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


}
