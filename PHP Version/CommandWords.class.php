<?php

class CommandWords
{
	private $validCommands = array('go', 'quit', 'help');

	public function __construct()
	{
		// meh
	}

	public function isCommand($command)
	{
		if(in_array($command, $this->validCommands, true)) {
			return true;
		}else{
			return false;
		}
	}

	public function showAll()
	{
		foreach($this->validCommands as $command) {
			echo "{$command} ";
		}
		echo PHP_EOL;
	}
}