<?php

class CommandWords
{
	private $validCommands = array();
	private $commandWordsDocumentation = array();

	public function __construct()
	{
		$this->validCommands[0] = array('help', 'quit', 'back', 'manual', 'inventory');
		$this->validCommands[1] = array('go', 'back', 'pickup', 'manual');
		$this->validCommands[2] = array('jump');

		$this->commandWordsDocumentation['help'] = 'Ask for help (no parameters)';
		$this->commandWordsDocumentation['quit'] = 'Quit the game (no parameters)';
		$this->commandWordsDocumentation['inventory'] = 'See your inventory (no parameters)';
		$this->commandWordsDocumentation['back'] = 'Back track your location. Usage: "back {n}", where \'n\' is optional (defaults to 1)';
		$this->commandWordsDocumentation['manual'] = 'View all commands or a specific command. Usage: "manual {command}", where \'command\' is optional (defaults to view all)';
		$this->commandWordsDocumentation['go'] = 'Go to another location by passing the exit direction as a paramter. Usage: "go {direction}"';
		$this->commandWordsDocumentation['pickup'] = 'Pickup an object by passing the object name as a parameter. Usage: "pickup {object}"';
		$this->commandWordsDocumentation['jump'] = 'Testing tripple command word... Not functional yet.';
	}

	public function commandExists($command)
	{
		foreach($this->validCommands as $commands) {
			if(in_array($command, $commands, TRUE)) {
				return true;
			}
		}

		return false;
	}

	public function isValidCommand($commands)
	{
		$commandsCount = count($commands);

		if(isset($this->validCommands[$commandsCount-1]) && in_array($commands[0], $this->validCommands[$commandsCount-1], true)) {
			return true;
		}

		return false;
	}

	public function manual($command = 'all')
	{
		if($command !== 'all') {
			if(isset($this->commandWordsDocumentation[$command])) {
				return $this->commandWordsDocumentation[$command];
			}else{
				return 'Specified command word is invalid';
			}
		}

		$commandLength = count($this->validCommands);

		$commands = '';

		for($i = 0;$i < $commandLength;++$i) {
			$wordCount = $i + 1;
			$commands .= "{$wordCount} word commands:\r\n";

			foreach($this->validCommands[$i] as $command) {
				$commands .= " | {$command}";
			}
			$commands .= ' |'.(!($i < $commandLength-1) ? '' : "\r\n");
		}

		return $commands;
	}
}