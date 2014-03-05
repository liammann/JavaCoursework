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

	public function parseCommand($commands)
	{
		$commands = explode(' ', $commands);
		$keyWord = $commands[0];

		if(!$this->commandWords->commandExists($keyWord)) {
			return 'The command entered does not exist!';
		}

		if(!$this->commandWords->isValidCommand($commands)) {
			return 'The command entered has an incorrect syntax. Please refer to the commands manual.';
		}

		if(isset($commands[1])) {
			array_shift($commands);
			$parameters = $commands;
		}else{
			$parameters = array();
		}

		return $this->commandActions->invokeAction($keyWord, $parameters);
	}
}