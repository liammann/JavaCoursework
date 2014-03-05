<?php

require 'CommandWords.class.php';
require 'Command.class.php';

class Parser
{
	private $commands;

	public function __construct()
	{
		$this->commands = new CommandWords();
	}

	public function getCommand()
	{
		$word1 = null;
		$word2 = null;

		echo '> ';

		$inputLine = trim(fgets(STDIN));
		$tokeniser = explode(' ', $inputLine);

		if(isset($tokeniser[0])) {
			$word1 = $tokeniser[0];

			if(isset($tokeniser[1])) {
				$word2 = $tokeniser[1];
			}
		}

		if($this->commands->isCommand($word1)) {
			return new Command($word1, $word2);
		}else{
			return new Command(null, $word2);
		}
	}

	public function showCommands()
	{
		$this->commands->showAll();
	}
}