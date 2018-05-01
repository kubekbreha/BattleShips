# Battle Ship

This is console game which will be hosted on web.
You can find presentation video on [this](https://www.youtube.com/watch?v=A_AKI0MGDlw) link.

## About 

On start of this game you need to pick game mode.
Game modes consist of   
 * player vs computer
 * player vs player

If you pick player vs computer game mode you need to pick computer AI level
You can pick from these:     
1. Easy
2. Medium
3. Hard
4. Expert

Game is is  `NOTSETTEDUP` state.
Next step is to generate play board which can be generated randomly or by user.

1. Randomly: compputer get shop size from list of ships and put ship 
            randomly to playboard.
2. By user: user set position and orientation of ship which will be placed to playboard.            

If everything is setted up game can start. Game state will chande to `PLAYING`.
Game is all about shooting to playboard. User can choose where to shoot and compuer
shoot where it is supposed to.
During playing versus computer user can go some step back or can use hint
which will tell him where is best position to shoot.

If ship tile is hit tile state change form `SHIP` to `HIT`.
If water tile is hit tile state change form `WATER` to `MISSED`. 

Game is in `PLAYING` state util one of the players win.
Player will if he sunk every ship of other player. 
After some player sunk every ship of oposite player game state is changed form `PLAYING`
 to `WIN` or `LOSE`. 
Function which take care about checking if game is win is called `isGameVon()`.
This function check if all ship tiles are in `HIT` state.

### Class Diagram

![alt text](https://raw.githubusercontent.com/kubekbreha/BattleShips/master/res/ClassDiagram.png)

### State diagrams tile

![alt text](https://raw.githubusercontent.com/kubekbreha/BattleShips/master/res/StateDiagram.png)

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
