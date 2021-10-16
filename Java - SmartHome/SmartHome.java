package MyFiles.Project;

public class SmartHome {

    private SmartPlug[] plugs;
    private AttachedDevice[] devices;
    private Room[] rooms;

    public SmartHome(int roomAmount, int plugAmount) {
        this.plugs = new SmartPlug[plugAmount];
        this.rooms = new Room[roomAmount];
        this.devices = initialiseDevices();
    }


    private AttachedDevice[] initialiseDevices(){
        AttachedDevice lamp = new AttachedDevice(1, "Lamp");
        AttachedDevice TV = new AttachedDevice(2, "Television");
        AttachedDevice computer = new AttachedDevice(3, "Computer");
        AttachedDevice phoneCharger = new AttachedDevice(4, "Phone Charger");
        AttachedDevice heater = new AttachedDevice(5, "Heater");
        return new AttachedDevice[]{lamp, TV, computer, phoneCharger, heater};
    }

    public void initialiseRoom(int pos, int ID, String Name){
        Room room = new Room(ID, Name);
        this.rooms[pos] = room;
    }

    public void initialisePlug(boolean status, int pos, int ID,  SmartHome.Room room, SmartHome.AttachedDevice device){
        SmartPlug plug = new SmartPlug(status, ID, room, device);
        this.plugs[pos] = plug;
    }

    public void initialiseDevice(int pos, int ID, String name){
        AttachedDevice device = new AttachedDevice(ID, name);
        this.devices[pos] = device;
    }



    public Room getRoom(int ID){
        for (Room room : this.rooms){
            if (room.getID() == ID){
                return room;
            }
        }
        return null;
    }

    public SmartPlug getPlug(int ID){
        for (SmartPlug plug : this.plugs){
            if (plug.getID() == ID){
                return plug;
            }
        }
        return null;
    }

    public Room[] getRooms(){
        return this.rooms;
    }

    public AttachedDevice[] getDevices(){
        return this.devices;
    }

    public SmartPlug[] getPlugs(){
        return this.plugs;
    }

    public AttachedDevice getDevice(int ID){
        for (AttachedDevice device : this.devices){
            if (device.getID() == ID){
                return device;
            }
        }
        return null;
    }

    public Room getRoom(SmartPlug plug){
        return plug.getRoom();
    }

    public AttachedDevice getDevice(SmartPlug plug){
        return plug.getDevice();
    }

    public int getID(SmartPlug plug){
        return plug.getID();
    }

    public boolean getStatus(SmartPlug plug){
        return plug.getStatus();
    }



    public void expandPlugs(){
        SmartPlug[] newList = new SmartPlug[this.plugs.length + 1];
        for (int i = 0; i < this.plugs.length; i++){
            newList[i] = this.plugs[i];
        }
        this.plugs = newList;
    }

    public void expandDevices(){
        AttachedDevice[] newList = new AttachedDevice[this.devices.length + 1];
        for (int i = 0; i < this.devices.length; i++){
            newList[i] = this.devices[i];
        }
        this.devices = newList;
    }

    public void expandRooms(){
        Room[] newList = new Room[this.rooms.length + 1];
        for (int i = 0; i < this.rooms.length; i++){
            newList[i] = this.rooms[i];
        }
        this.rooms = newList;
    }



    public void updateRoom(SmartPlug plug, Room room){
        plug.setRoom(room);
    }

    public void updateDevice(SmartPlug plug, AttachedDevice device){
        plug.setDevice(device);
    }

    public void updateStatus(SmartPlug plug, boolean status){
        plug.setStatus(status);
    }



    static class Room {

        private final int roomID;
        private final String roomName;

        public Room(int ID, String name) {
            this.roomID = ID;
            this.roomName = name;
        }

        public int getID() {
            return this.roomID;
        }

        public String getName() {
            return this.roomName;
        }

    }


    static class AttachedDevice {

        private final int deviceID;
        private final String deviceName;

        public AttachedDevice(int ID, String name){
            this.deviceID = ID;
            this.deviceName = name;
        }

        public int getID(){
            return this.deviceID;
        }

        public String getName(){
            return this.deviceName;
        }

    }


}