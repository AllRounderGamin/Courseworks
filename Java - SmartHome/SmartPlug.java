package MyFiles.Project;

public class SmartPlug {

    private boolean status;
    private final int ID;
    private SmartHome.Room room;
    private SmartHome.AttachedDevice device;

    public SmartPlug(boolean status, int ID, SmartHome.Room room, SmartHome.AttachedDevice device){
        this.status = status;
        this.ID = ID;
        this.room = room;
        this.device = device;
    }


    public SmartHome.Room getRoom(){
        return this.room;
    }

    public SmartHome.AttachedDevice getDevice(){
        return this.device;
    }

    public int getID(){
        return this.ID;
    }

    public boolean getStatus(){
        return this.status;
    }



    public void setStatus(boolean status){
        this.status = status;
    }

    public void setRoom(SmartHome.Room room){
        this.room = room;
    }

    public void setDevice(SmartHome.AttachedDevice device){
        this.device = device;
    }


}
