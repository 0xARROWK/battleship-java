BattleShip TP in Java
==

**Version 4.0.0**

## Date

- May 2020

## Language used

- Java

#### Libraries / Framework used

- JSON

## In which context did I this project?

I did this project as part of my studies in IT. This is a graded project for my second semester.

## The goal of the project

The aim of this project was to create a naval battle game in java. We had to be able to play against a computer or another player.Here the implemented computer is very simple, it is not an AI but a simple random choice.

## Installation

If you want to try this battlship, first you have to clone this repository :

```bash
git clone https://github.com/0xARK/battleship-java
```

Then, move in the right directory, and launch the jar file :

```bash
cd battleship-java
java -jar battle_ark.jar <path_to_config_file> <first_player_name> <second_player_name>
```
When you run the jar file, you need to specify a path to a configuration file. You have two example in the `data` directory, but this is what they should look like :

```text
8:8:            # grid size at format x:y:
mode:HA:        # mode of the game, it can be HH, HA or AA (H = human player, A = automated player)
porte avion:5:  # ship number 1 at format ship_name:ship_size:
fregate:4:      # ship number 2 at format ship_name:ship_size:
```

One configuration file is required per human player.
