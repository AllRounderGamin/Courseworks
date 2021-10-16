package MyFiles.Project;

import java.util.Scanner;


public class Console {

    Scanner input = new Scanner(System.in);

    public SmartHome initialise() {
        int roomCount = getInt("Enter how many rooms are in the house: ");
        int plugCount = getInt("Enter how many plugs are in the house: ");
        SmartHome home = new SmartHome(roomCount, plugCount);
        createInitialRooms(home, roomCount);
        createInitialPlugs(home, plugCount);
        return home;
    }


    public void createInitialRooms(SmartHome home, int count) {
        for (int i = 0; i < count; i++) {
            createRoom(home, i, (i+1));
        }
    }

    public void createInitialPlugs(SmartHome home, int count) {
        for (int i = 0; i < count; i++) {
            createPlug(home, i, (i+1));
        }
    }

    public void createPlug(SmartHome home, int pos, int ID){
        System.out.println("Enter Plug information below:");
        displayRooms(home);
        int roomID = getInt("Select a room using it's ID: ");
        System.out.println("Available devices: \n Below are the devices that can connect to your SmartPlug");
        displayDevices(home);
        int deviceID = getInt("Select a device using it's ID: ");
        SmartHome.Room room = home.getRoom(roomID);
        SmartHome.AttachedDevice device = home.getDevice(deviceID);
        home.initialisePlug(false, pos, ID, room, device);
    }

    public void createRoom(SmartHome home, int pos, int ID){
        String name = getString("Enter room " + ID + " name: ");
        home.initialiseRoom(pos, ID, name);
    }

    public void createDevice(SmartHome home, int pos, int ID){
        String name = getString("Enter device " + ID + " name: ");
        home.initialiseDevice(pos, ID, name);
    }



    public void displayAllPlugInfo(SmartHome home){
        for (SmartHome.Room room : home.getRooms()){
            System.out.println("ROOM " + room.getID());
            displayRoomPlugs(home, room);
        }
    }

    public void displayRoomPlugs(SmartHome home, SmartHome.Room room){
        boolean roomCheck = false;
        for (SmartPlug plug : home.getPlugs()) {
            if (home.getRoom(plug) == room) {
                System.out.print("SmartPlug | Attached to " + home.getDevice(plug).getName() + "\t\t");
                System.out.println("| room: " + room.getName() + " | ID: " + home.getID(plug) + " | Status: " +
                        (home.getStatus(plug) ? "On" : "Off")  + " |");
                roomCheck = true;
            }
        }
        if (!roomCheck){
            System.out.println("There are no recorded SmartPlugs in " + room.getName());
        }
    }

    public void displayAllPlugs(SmartHome home){
        for (SmartPlug plug : home.getPlugs()){
            System.out.print("SmartPlug | Attached to " + home.getDevice(plug).getName() + "            ");
            System.out.println("| room: " + home.getRoom(plug).getName() + " | ID: " + home.getID(plug) + " | Status: " +
                    (home.getStatus(plug) ? "On" : "Off")  + " |");
        }
    }

    public void displayRooms(SmartHome home){
        System.out.print("Rooms available: ");
        for(SmartHome.Room room : home.getRooms()){
            System.out.print(room.getID() + " - " + room.getName() + " | ");
        }
        System.out.print("\n");
    }

    public void displayDevices(SmartHome home){
        System.out.print("Devices available: \n");
        for (SmartHome.AttachedDevice device : home.getDevices()){
            System.out.println(device.getID() + " - " + device.getName());
        }
    }



    public void updateAllStatus(SmartHome home, boolean status){
        for (SmartPlug plug : home.getPlugs()){
            home.updateStatus(plug, status);
        }
    }

    public void updateRoomStatus(SmartHome home, SmartHome.Room room, boolean status){
        for (SmartPlug plug : home.getPlugs()){
            if (home.getRoom(plug) == room){
                home.updateStatus(plug, status);
            }
        }
    }

    public void updatePlugStatus(SmartHome home, SmartPlug plug, boolean status){
        home.updateStatus(plug, status);
    }

    public void updateRoom(SmartHome home, SmartPlug plug){
        displayRooms(home);
        int roomID  = getInt("Select new room ID: ");
        home.updateRoom(plug, home.getRoom(roomID));
    }

    public void updateDevice(SmartHome home, SmartPlug plug){
        displayDevices(home);
        int deviceID = getInt("Select new device ID: ");
        home.updateDevice(plug, home.getDevice(deviceID));
    }



    public void addNewPlug(SmartHome home){
        home.expandPlugs();
        createPlug(home, (home.getPlugs().length - 1), home.getPlugs().length);
    }

    public void addNewDevice(SmartHome home){
        home.expandDevices();
        createDevice(home, (home.getDevices().length - 1), home.getDevices().length);
    }

    public void addNewRoom(SmartHome home){
        home.expandRooms();
        createRoom(home, (home.getRooms().length - 1), home.getRooms().length);
    }



    public SmartPlug returnPlug(SmartHome home, int ID){
        return home.getPlug(ID);
    }

    public SmartHome.Room returnRoom(SmartHome home, int ID) {
        return home.getRoom(ID);
    }



    public int getInt(String mes) {
        System.out.print(mes);
        int Int = input.nextInt();
        input.nextLine();
        // Remove trailing \n
        return Int;
    }

    public String getString(String mes) {
        System.out.print(mes);
        return input.nextLine();
    }
}