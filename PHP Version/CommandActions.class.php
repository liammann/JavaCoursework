<?php

class CommandActions
{
	private $commandWords;
	private $gameData;

	public function __construct()
	{
		$this->commandWords = new CommandWords();
		$this->gameData = new GameData();
	}

	public function invokeAction($keyWord, array $parameters = array())
	{
		$parameterCount = count($parameters);

		if($parameterCount === 0) {
			switch($keyWord) {
				case 'quit':
					$answer = $this->quit();
					break;
				case 'help':
					$answer = $this->help();
					break;
				case 'manual':
					$answer = $this->commandWords->manual();
					break;
				case 'back':
					$answer = $this->back();
					break;
				case 'inventory':
					// $answer = $this->inventory(); ???
					break;
			}
		}elseif($parameterCount === 1) {
			switch($keyWord) {
				case 'go':
					$answer = $this->go($parameters[0]);
					break;
				case 'back':
					$answer = $this->back($parameters[0]);
					break;
				case 'manual':
					$answer = $this->commandWords->manual($parameters[0]);
					break;
				case 'pickup':
					$answer = $this->pickup($parameters[0]);
					break;
			}
		}else{
			switch($keyWord) {
				case 'jump':
					$answer = $this->jump($parameters);
					break;
			}
		}

		return $answer;
	}

	private function quit()
	{
		return 'quit';
	}

	private function help()
	{
		return $this->getTask()."\r\nType 'manual' to view your list of commands, or manual {command} to learn about a specific command.";
	}

	private function back($retraceSteps = 1)
	{
		return "Going back {$retraceSteps} location(s): ".$this->gameData->setNewLocation(null, $retraceSteps); // can get rid of the (s) in Java version
	}

	private function pickup($object)
	{
		// put object in inventory and remove object from location
		return "Item '{$object}' has been picked up!";
	}

	private function jump($parameters)
	{
		switch($parameters[0]) {
			case 'to':
				$action = "Jumped to {$parameters[1]}";
				break;
			case 'over':
				$action = "Jumped over {$parameters[1]}";
				break;
			default:
				$action = "Invalid jump command, {$parameters[0]}";
		}

		return $action;
	}

	private function go($direction)
	{
		if(!$this->gameData->getCurrentLocation()->isValidExit($direction)) {
			return 'Invalid exit!';
		}

		return $this->updateLocation($direction);
	}

	private function updateLocation($direction)
	{
		$this->gameData->setNewLocation($this->gameData->getCurrentLocation()->getExit($direction));

		return $this->gameData->getCurrentLocation()->getLongDescription();
	}

	private function getTask()
	{
		return 'Your task is to...';
	}
}