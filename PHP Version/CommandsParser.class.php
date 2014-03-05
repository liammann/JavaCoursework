<?php

class CommandsParser
{
	private $commandWords;
	private $commandActions;

	public function __construct()
	{
		$this->commandWords = new CommandWords();
		$this->commandActions = new CommandActions();
	}

	public function getCommand()
	{
		return $inputLine = trim(fgets(STDIN));
	}

	public function parseCommand($inputCommand)
	{
		$commands = explode(' ', $inputCommands);
		$keyword = $commands[0];

		if(!$this->commandWords->commandExists($keyword)) {
			return 'The command entered does not exist!';
		}

		if(!$this->commandWords->isValidCommand($commands)) {
			return 'The command entered has an incorrect syntax. Please refer to the commands manual.';
		}

		if(isset($commands[1])) {
			array_shift($commands);
			return $this->commandActions->invokeAction($keyword, $commands);
		}

		return $this->commandActions->invokeAction($keyword);
	}
}