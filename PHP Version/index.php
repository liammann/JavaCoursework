<?php

require 'Game.class.php';
require 'GameData.class.php';
require 'Object.class.php';
require 'FixedObject.class.php';
require 'MovableObject.class.php';
require 'Character.class.php';
require 'CommandActions.class.php';
require 'CommandWords.class.php';
require 'CommandsParser.class.php';
require 'Location.class.php';

$game = new Game();
$game->newGame();