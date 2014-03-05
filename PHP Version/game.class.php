<?php

class Game
{
	private $commandsParser;
	private $gameData;

	public function __construct()
	{
		$this->commandsParser = new CommandsParser;
		$this->gameData = new GameData;
	}

	public function newGame()
	{
		$this->buildLocations();
		$this->createCharacters();
		$this->play();
	}

	public function loadGame()
	{
		// load game save
	}

	private function buildLocations()
	{
		$outside = new Location('outside the main entrance of the university');
		$theater = new Location('in a lecture theater');
		$pub = new Location('in a campus pub');
		$lab = new Location('in a computing lab');
		$office = new Location('in the computing admin office');

		$outside->setExit('east', $theater);
		$outside->setExit('south', $lab);
		$outside->setExit('west', $pub);
		$theater->setExit('west', $outside);
		$pub->setExit('east', $outside);
		$lab->setExit('north', $outside);
		$lab->setExit('east', $office);
		$office->setExit('west', $lab);

		$theater->setLock();
		$pub->setLock();
		$lab->setLock();
		$office->setLock();

		$cider = new MovableObject('Bottle of cider', 'Half a bottle of Strongbow cider');
		$chair = new FixedObject('Chair', 'An old wooden chair');

		$outside->setItem($cider);
		$outside->setItem($chair);
		$theater->setItem($cider);

		$this->gameData->setNewLocation($outside);
	}

	private function createCharacters()
	{
		$player1 = new Character('Player1', 100, 100);
	}

	public function play()
	{
		echo $this->welcome(); // may need moving depending on new game or loaded game...

		$finished = false;

		while(!$finished) {
			echo "\r\n> ";

			$command = $this->commandsParser->getCommand();

			if(!empty($command)) {
				$output = $this->commandsParser->parseCommand($command);

				if($output === 'quit') {
					$finished = true;
				}else{
					echo $output;
				}
			}else{
				echo 'No command entered!';
			}
		}

		echo "Thank you for playing. Good bye.\r\n";
	}

	private function welcome()
	{
		return "Welcome to the Pub Crawl Game!\r\n"
				."Type 'help' if you are not sure what to do.\r\n"
				."\r\n{$this->gameData->getCurrentLocation()->getLongDescription()}";
	}
}