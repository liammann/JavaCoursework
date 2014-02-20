<?php

require 'Parser.class.php';
require 'Room.class.php';

class Game
{
	private $parser;
	private $currentRoom;

	public function __construct()
	{
		$this->createRooms();
		$this->parser = new Parser();
	}

	private function createRooms()
	{
		$outside = new Room('outside the main entrance of the university');
		$theater = new Room('in a lecture theater');
		$pub = new Room('in a campus pub');
		$lab = new Room('in a computing lab');
		$office = new Room('in the computing admin office');

		$outside->setExit('east', $theater);
		$outside->setExit('south', $lab);
		$outside->setExit('west', $pub);

		$theater->setExit('west', $outside);

		$pub->setExit('east', $outside);

		$lab->setExit('north', $outside);
		$lab->setExit('east', $office);

		$office->setExit('west', $lab);

		$this->currentRoom = $outside;
	}

	public function play()
	{
		$this->printWelcome();

		$finished = false;

		while(!$finished) {
			$command = $this->parser->getCommand();
			$finished = $this->processCommand($command);
		}

		echo 'Thank you for playing. Good bye.', PHP_EOL;
	}

	public function printWelcome()
	{
		echo PHP_EOL, 'Welcome to the World of Zuul!', PHP_EOL, 
				'World of Zuul is a new, incredibly boring adventure game',
				PHP_EOL, 'Type "help" if you need help.', PHP_EOL, PHP_EOL,
				$this->currentRoom->getLongDescription();
	}

	private function processCommand($command)
	{
		$wantToQuit = false;

		if($command->isUnknown()) {
			echo 'I don\'t know what you mean...', PHP_EOL;
			return false;
		}

		$commandWord = $command->getCommandWord();

		if($commandWord === 'help') {
			$this->printHelp();
		}elseif($commandWord === 'go') {
			$this->goRoom($command);
		}elseif($commandWord === 'quit') {
			$wantToQuit = $this->quit($command);
		}

		return $wantToQuit;
	}

	private function printHelp()
	{
		echo PHP_EOL, 'You are lost. You are alone. You wander',
				PHP_EOL, 'around at the university', PHP_EOL,
				PHP_EOL, 'Your command words are:', PHP_EOL;

		$this->parser->showCommands();
	}

	private function goRoom($command)
	{
		if(!$command->hasSecondWord()) {
			echo 'Go where?';
			return;
		}

		$direction = $command->getSecondWord();

		$nextRoom = $this->currentRoom->getExit($direction);

		if($nextRoom === null) {
			echo 'There is no door!';
		}else{
			$this->currentRoom = $nextRoom;
			echo $this->currentRoom->getLongDescription();
		}
	}

	private function quit($command)
	{
		if($command->hasSecondWord()) {
			echo 'Quit what?';
			return false;
		}else{
			return true;
		}
	}
}