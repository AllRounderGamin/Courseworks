package MyFiles.Project;

public class Dashboard {

    public static void main(String[] args) {
        Console console = new Console();
        SmartHome home = console.initialise();
        while (true) {
            display_Dashboard(home, console);
            int option = console.getInt("> ");
            switch (option) {
                case 1 -> home_Options(home, console);
                case 2 -> room_Options(home, console);
                case 3 -> plug_Options(home, console);
                case 4 -> system_Options(home, console);
            }
        }
    }

    public static void display_Dashboard(SmartHome home, Console console){
        System.out.println("---------------DASHBOARD--------------");
        console.displayAllPlugInfo(home);
        System.out.println("-------------MENU OPTIONS------------- \n---------Please Select Option:--------");
        System.out.println("1 - House Level Options \n2 - Room Level Options \n3 - Plug Level Options \n" +
                "4 - System Options");
    }



    public static void home_Options(SmartHome home, Console console){
        System.out.println("HOUSE LEVEL OPTIONS");
        System.out.println("1 - Switch all plugs off \n2 - Switch all plugs on");
        int option = console.getInt("Select an option: ");
        console.updateAllStatus(home, option != 1);
    }

    public static void room_Options(SmartHome home, Console console){
        console.displayRooms(home);
        int roomID = console.getInt("Please select room ID: ");
        SmartHome.Room room = console.returnRoom(home, roomID);
        console.displayRoomPlugs(home, room);
        System.out.println("Room Level Options:");
        System.out.println("1 - Switch all plugs off in room \n2 - Switch all plugs on in room \n" +
                "3 - Select a plug and toggle status");
        int option = console.getInt("Select an option: ");
        if (option == 3){
            int plugID = console.getInt("Select plug ID: ");
            int status = console.getInt("Enter Status, 1 - Off, 2- On: ");
            console.updatePlugStatus(home, console.returnPlug(home, plugID), status != 1);
        }
        else{
            console.updateRoomStatus(home, room, option != 1);
        }
    }

    public static void plug_Options(SmartHome home, Console console){
        console.displayAllPlugs(home);
        int ID = console.getInt("Please select plug ID: ");
        System.out.println("Plug Level Options: ");
        System.out.println("1 - Switch plug off \n2 - Switch plug on \n3 - Change attached device" +
                " \n4 - Move plug to different room");
        int option = console.getInt("Select an option: ");
        switch (option) {
            case 1 -> console.updatePlugStatus(home, console.returnPlug(home, ID), false );
            case 2 -> console.updatePlugStatus(home, console.returnPlug(home, ID), true);
            case 3 -> console.updateDevice(home, console.returnPlug(home, ID));
            case 4 -> console.updateRoom(home, console.returnPlug(home, ID));
        }
    }

    public static void system_Options(SmartHome home, Console console){
        System.out.println("System Options: ");
        System.out.println("1 - Add SmartPlug \n2 - Add Device \n3 - Add Room");
        int option = console.getInt("Select an option: ");
        switch (option){
            case 1 -> console.addNewPlug(home);
            case 2 -> console.addNewDevice(home);
            case 3 -> console.addNewRoom(home);
        }
    }

}
