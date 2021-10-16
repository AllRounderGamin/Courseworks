from graphics import *
from time import sleep


def Draw_Rectangle(x, y, colour, Patch_Area, window):
    # Creates a rectangle of a chosen colour and draws to the window, and appends the shape to the patch list
    rectangle = Rectangle(x, y)
    rectangle.setFill(colour)
    rectangle.setOutline(colour)
    Patch_Area.append(rectangle)
    rectangle.draw(window)


def Draw_Circle(colour, counter, row, window, xPos, yPos, Patch_Area):
    nextCircle = Circle(Point(xPos + 10 + 20 * counter, yPos + 10 + 20 * row), 10)
    if row % 2 == 0:  # changes colour depending on the row
        nextCircle.setFill(colour)
    else:
        nextCircle.setFill("white")
    nextCircle.setOutline(colour)
    Patch_Area.append(nextCircle)   # appends shape to Patch list
    nextCircle.draw(window)  # draws the circle


def Delete_Patch(Full_Patchwork, row, column):
    # Deletes all shapes in the patch area, then assigns the space a None type attribute
    for item in Full_Patchwork[row][column]:
        item.undraw()
    Full_Patchwork[row][column] = None


def Move_Patch(Full_Patchwork, row, column, dx, dy):
    # Moves the patch area in the desired direction in exactly 2 seconds, and adjusts Full_Patchwork to reflect this
    for item in Full_Patchwork[row][column]:
        item.move(dx, dy)
        sleep(2 / len(Full_Patchwork[row][column]))
    Full_Patchwork[row + (dy // 100)][column + (dx // 100)] = Full_Patchwork[row][column]
    Full_Patchwork[row][column] = None


def Draw_Patch_One(colour, position, window):
    # Separates position coordinates
    xPos = position.getX()
    yPos = position.getY()
    counter = 0
    Patch_Area = []
    for row in range(0, 4):
        for letter in range(0, 4):
            # Determines background / letter colour
            if 2 <= counter % 8 <= 5:
                backgroundColour = colour
                Draw_Rectangle(Point(0 + xPos + 25 * letter, 0 + yPos + 25 * row), Point(25 + xPos + 25 *
                               letter, 25 + yPos + 25 * row), "white", Patch_Area, window)
            else:
                backgroundColour = "white"
                Draw_Rectangle(Point(0 + xPos + 25 * letter, 0 + yPos + 25 * row), Point(25 + xPos + 25 *
                               letter, 25 + yPos + 25 * row), colour, Patch_Area, window)

                # Determines what letter needs to be created, then draws it
                # I
            if letter % 2 > 0:
                Draw_Rectangle(Point(0 + xPos + 25 * letter, 6 + yPos + 25 * row), Point(9 + xPos + 25 * letter, 19
                               + yPos + 25 * row), backgroundColour, Patch_Area, window)
                Draw_Rectangle(Point(16 + xPos + 25 * letter, 6 + yPos + 25 * row), Point(25 + xPos + 25 * letter, 19
                               + yPos + 25 * row), backgroundColour, Patch_Area, window)

                # H
            else:
                Draw_Rectangle(Point(6 + xPos + 25 * letter, 0 + yPos + 25 * row), Point(19 + xPos + 25 * letter, 9
                               + yPos + 25 * row), backgroundColour, Patch_Area, window)

                Draw_Rectangle(Point(6 + xPos + 25 * letter, 16 + yPos + 25 * row), Point(19 + xPos + 25 * letter, 25
                               + yPos + 25 * row), backgroundColour, Patch_Area, window)

            # Adds a border around the design
            border = Rectangle(Point(xPos + 25 * letter, yPos + 25 * row),
                               Point(25 + xPos + 25 * letter, 25 + yPos + 25 * row))
            border.draw(window)
            Patch_Area.append(border)
            counter += 1
    return Patch_Area


def Draw_Patch_Two(colour, position, window):
    # separates position into X and Y coordinates
    xPos = position.getX()
    yPos = position.getY()
    Patch_Area = []
    # Adds a white background for the patch
    Draw_Rectangle(position, Point(xPos + 100, yPos + 100), "white", Patch_Area, window)
    # draws 5 circles in a row for 5 rows
    for row in range(0, 5):
        for counter in range(0, 5):
            Draw_Circle(colour, counter, row, window, xPos, yPos, Patch_Area)
    border = Rectangle(position, Point(xPos + 100, yPos + 100))
    Patch_Area.append(border)
    border.draw(window)
    return Patch_Area


def Set_Options():
    # Gets size for patchwork, keeps trying until a valid size is entered
    patchworkSize = int(input("Enter the size of each individual patch, valid options are 5 or 7:"))
    while patchworkSize != 5 and patchworkSize != 7:
        patchworkSize = int(input("Invalid input, please try again \n"
                                  "Enter the size of each individual patch, valid options are 5 or 7:"))

    # Sets valid colour options and an empty list for user colours
    ValidColours = ["red", "green", "blue", "magenta", "orange", "cyan"]
    Colours = []

    # Asks user for colour until there are 3 choices
    while len(Colours) != 3:
        print("Enter colour", (len(Colours) + 1), "of the patchwork, \n"
                                                  "valid colours are: red, green, blue, magenta, orange or cyan, all 3"
                                                  " colours cannot be the same")
        nextColour = input("").lower()

        # Verifies user's option is valid
        if nextColour not in ValidColours:
            print("Invalid colour, please try again")
        else:
            Colours.append(nextColour)

        # Checks if all 3 colours are identical, if so the list is reset
        if len(Colours) == 3 and Colours[0] == Colours[1] == Colours[2]:
            print("All 3 colours are the same, please try again")
            Colours = []

    return patchworkSize, Colours


def Draw_Patchwork(patchworkSize, colours):
    # Creates window of custom size
    Patchwork = GraphWin("Patchwork", 100 * patchworkSize, 100 * patchworkSize)
    # Counter for how far in the blue square is, and sets a variable for the middle row
    blueCounter = 0
    middle = patchworkSize // 2
    Full_Patchwork = []

    for row in range(0, patchworkSize):
        Patchwork_Row = []
        for patch in range(0, patchworkSize):

            # Checks if the patch is before or following a blue patch - meaning it is the third chosen colour
            if blueCounter > patch or patchworkSize - blueCounter - 1 < patch:
                Patch_Area = Draw_Patch_Two(colours[2], Point(0 + 100 * patch, 0 + 100 * row), Patchwork)

            # Checks if the patch should be blue
            elif blueCounter == patch or patchworkSize - blueCounter - 1 == patch:
                Patch_Area = Draw_Patch_Two(colours[0], Point(0 + 100 * patch, 0 + 100 * row), Patchwork)

            # Any other patch is in the centre and thus is the second colour
            else:
                Patch_Area = Draw_Patch_One(colours[1], Point(0 + 100 * patch, 0 + 100 * row), Patchwork)

            Patchwork_Row.append(Patch_Area)
        # Increases or Decreases blueCounter as needed to make an X
        if row < middle:
            blueCounter += 1
        else:
            blueCounter -= 1
        Full_Patchwork.append(Patchwork_Row)
    return Patchwork, Full_Patchwork


def Editing_Mode(Patchwork, colours, Full_Patchwork, size):
    # Sets up infinite loop to allow Patchwork window to respond to actions
    while True:
        # Allows the user to select an area, and finds the position of that area in the 2d array
        print("Use the mouse to select a patch")

        patchSelect = Patchwork.getMouse()
        patchRow = int(patchSelect.getY() // 100)
        patchColumn = int(patchSelect.getX() // 100)
        # Creates a border around the selected area
        border = Rectangle(Point(patchColumn * 100, patchRow * 100),
                           Point(patchColumn * 100 + 100, patchRow * 100 + 100))
        border.setWidth(4)
        border.draw(Patchwork)

        print("""Options:
        D: Delete selected patch
        1 - 3 draws "HI" patch in an empty selected area with the colour chosen by number selected
        4 - 6 draws "Circles" patch in an empty selected area with the colour chosen by number selected
        Arrow keys: Moves selected patch into an empty adjacent space in the direction of the arrow key pressed
        Esc: Deselects current patch""")

        while True:
            # If an area is selected, waits for the user to select an option
            keyPress = Patchwork.getKey()

            # runs the function that deletes the selected patch
            if keyPress == "d" and Full_Patchwork[patchRow][patchColumn] is not None:
                Delete_Patch(Full_Patchwork, patchRow, patchColumn)

            # Runs the Draw_Patch_X function on an empty, selected place - and redraws the border above the new patch
            elif (keyPress == "1" or keyPress == "2" or keyPress == "3") and Full_Patchwork[patchRow][
                    patchColumn] is None:
                Patch_Area = Draw_Patch_One(colours[int(keyPress) - 1], Point(patchColumn * 100, patchRow * 100),
                                            Patchwork)
                border.undraw()
                border.draw(Patchwork)
                Full_Patchwork[patchRow][patchColumn] = Patch_Area
            elif (keyPress == "4" or keyPress == "5" or keyPress == "6") and Full_Patchwork[patchRow][
                    patchColumn] is None:
                Patch_Area = Draw_Patch_Two(colours[int(keyPress) - 4], Point(patchColumn * 100, patchRow * 100),
                                            Patchwork)
                border.undraw()
                border.draw(Patchwork)
                Full_Patchwork[patchRow][patchColumn] = Patch_Area

            # Runs Move_Patch adjusting the parameters based on the pressed direction
            elif keyPress == "Up" and patchRow > 0 and Full_Patchwork[patchRow][patchColumn] is not None \
                    and Full_Patchwork[patchRow - 1][patchColumn] is None:
                Move_Patch(Full_Patchwork, patchRow, patchColumn, 0, - 100)
            elif keyPress == "Down" and patchRow < size and Full_Patchwork[patchRow][patchColumn] is not None and \
                    Full_Patchwork[patchRow + 1][patchColumn] is None:
                Move_Patch(Full_Patchwork, patchRow, patchColumn, 0, 100)
            elif keyPress == "Left" and patchColumn > 0 and Full_Patchwork[patchRow][patchColumn] is not None and \
                    Full_Patchwork[patchRow][patchColumn - 1] is None:
                Move_Patch(Full_Patchwork, patchRow, patchColumn, - 100, 0)
            elif keyPress == "Right" and patchColumn < size and Full_Patchwork[patchRow][patchColumn] is not None and \
                    Full_Patchwork[patchRow][patchColumn + 1] is None:
                Move_Patch(Full_Patchwork, patchRow, patchColumn, 100, 0)

            # Removes the border around a patch and returns to the while loop waiting for a mouse click
            elif keyPress == "Escape":
                border.undraw()
                break


def Main():
    patchworkSize, Colours = Set_Options()
    Patchwork, Full_Patchwork = Draw_Patchwork(patchworkSize, Colours)
    Editing_Mode(Patchwork, Colours, Full_Patchwork, patchworkSize)


Main()
